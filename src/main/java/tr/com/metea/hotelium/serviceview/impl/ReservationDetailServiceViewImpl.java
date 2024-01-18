package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.ReservationDetail;
import tr.com.metea.hotelium.dto.ReservationDetailReadDTO;
import tr.com.metea.hotelium.dto.ReservationDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ReservationDetailWriteDTO;
import tr.com.metea.hotelium.service.ReservationDetailService;
import tr.com.metea.hotelium.serviceview.ReservationDetailServiceView;

import java.util.Objects;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RequiredArgsConstructor
@Service
public class ReservationDetailServiceViewImpl extends
        BaseServiceViewImpl<ReservationDetail, ReservationDetailWriteDTO, ReservationDetailReadDTO, ReservationDetailSearchCriteriaDTO>
        implements ReservationDetailServiceView {
    private final ReservationDetailService reservationDetailService;

    @Override
    public ReservationDetailReadDTO convertToDTO(ReservationDetail reservationDetail) {
        final var readDto = modelMapper.map(reservationDetail, ReservationDetailReadDTO.class);
        readDto.setReservationMasterId(reservationDetail.getReservationMaster().getId());
        readDto.setCustomerId(reservationDetail.getCustomer().getId());
        readDto.setCustomerFullName(reservationDetail.getCustomer().getName() + " " + reservationDetail.getCustomer().getLastname());
        readDto.setCustomerLegalId(reservationDetail.getCustomer().getLegalId());
        readDto.setCustomerPhone(reservationDetail.getCustomer().getPhone());
        if (Objects.nonNull(reservationDetail.getCustomer().getCompany())) {
            readDto.setCompanyId(reservationDetail.getCustomer().getCompany().getId());
            readDto.setCompanyLegalNo(reservationDetail.getCustomer().getCompany().getLegalNo());
            readDto.setCompanyName(reservationDetail.getCustomer().getCompany().getNameTitle());
        }
        return readDto;
    }
}
