package tr.com.metea.hotelium.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.ReservationDetail;
import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.dto.ReservationDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ReservationDetailWriteDTO;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.repository.ReservationDetailRepository;
import tr.com.metea.hotelium.service.CustomerService;
import tr.com.metea.hotelium.service.ReservationDetailService;
import tr.com.metea.hotelium.service.ReservationMasterService;
import tr.com.metea.hotelium.util.MessageUtil;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
public class ReservationDetailServiceImpl extends BaseServiceImpl<ReservationDetail, ReservationDetailWriteDTO, ReservationDetailSearchCriteriaDTO>
        implements ReservationDetailService {
    private ReservationDetailRepository reservationDetailRepository;
    private ReservationMasterService reservationMasterService;
    private CustomerService customerService;

    @Autowired
    public ReservationDetailServiceImpl(ReservationDetailRepository reservationDetailRepository,
                                        @Lazy ReservationMasterService reservationMasterService,
                                        @Lazy CustomerService customerService, MessageUtil messageUtil) {
        this.reservationDetailRepository = reservationDetailRepository;
        this.reservationMasterService = reservationMasterService;
        this.customerService = customerService;
        this.messageUtil = messageUtil;
    }


    @Override
    public ReservationDetail create(ReservationDetailWriteDTO reservationDetailWriteDTO) {
        checkCustomerAlreadyExistInReservation(reservationDetailWriteDTO);
        checkCustomerAlreadyExistInAnotherReservation(reservationDetailWriteDTO);
        final var reservationDetail = new ReservationDetail();
        reservationDetail.setReservationMaster(reservationMasterService
                .getById(reservationDetailWriteDTO.getReservationMasterId()));
        reservationDetail.setCustomer(customerService.getById(reservationDetailWriteDTO.getCustomerId()));
        return reservationDetailRepository.save(reservationDetail);
    }

    @Override
    public ReservationDetail convertToEntity(ReservationDetailWriteDTO dto) {
        return modelMapper.map(dto, ReservationDetail.class);
    }

    @Override
    public ReservationDetail mapDtoToEntity(ReservationDetail reservationDetail, ReservationDetailWriteDTO dto) {
        reservationDetail.setCustomer(customerService.getById(dto.getCustomerId()));
        reservationDetail.setReservationMaster(reservationMasterService.getById(dto.getReservationMasterId()));
        return reservationDetail;
    }

    private void checkCustomerAlreadyExistInReservation(ReservationDetailWriteDTO reservationDetailWriteDTO) {
        final var filter = new ReservationDetailSearchCriteriaDTO();
        filter.setReservationMasterId(reservationDetailWriteDTO.getReservationMasterId());
        filter.setCustomerId(reservationDetailWriteDTO.getCustomerId());
        final var details = search(filter, PageRequest.of(0, 10));
        if (details.hasContent()) {
            throw new ServiceExecutionException(messageUtil.get("reservationDetail.customerAlreadyExist.exception"));
        }
    }

    private void checkCustomerAlreadyExistInAnotherReservation(ReservationDetailWriteDTO reservationDetailWriteDTO) {
        final var filter = new ReservationDetailSearchCriteriaDTO();
        filter.setCustomerId(reservationDetailWriteDTO.getCustomerId());
        final var details = search(filter, PageRequest.of(0, 10));
        // TODO : Rsql ile database e new ve booking durumunda sorgu atılıp sonuç dönerse hata atılabilir.
        details.forEach(reservationDetail -> {
            if (!ReservationMaster.ReservationStatus.COMPLETED.equals(reservationDetail.getReservationMaster().getStatus())) {
                throw new ServiceExecutionException(messageUtil.get("reservationDetail.customerAlreadyExistAnotherReservation.exception"));
            }
        });
    }
}
