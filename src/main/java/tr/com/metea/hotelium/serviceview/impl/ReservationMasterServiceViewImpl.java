package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.domain.ReservationTransaction;
import tr.com.metea.hotelium.dto.*;
import tr.com.metea.hotelium.service.ReservationMasterService;
import tr.com.metea.hotelium.service.ReservationTransactionService;
import tr.com.metea.hotelium.serviceview.ReservationMasterServiceView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RequiredArgsConstructor
@Service
public class ReservationMasterServiceViewImpl extends
        BaseServiceViewImpl<ReservationMaster, ReservationMasterWriteDTO, ReservationMasterReadDTO, ReservationMasterSearchCriteriaDTO>
        implements ReservationMasterServiceView {
    private final ReservationMasterService reservationMasterService;
    private final ReservationTransactionService reservationTransactionService;


    @Override
    public ReservationMasterReadDTO markAsBooking(ReservationBookingDTO reservationBookingDTO) {
        return convertToDTO(reservationMasterService.markAsBooking(reservationBookingDTO));
    }

    @Override
    public ReservationMasterReadDTO markAsComplete(String id) {
        return convertToDTO(reservationMasterService.markAsComplete(id));
    }

    @Override
    public List<ResarvationTransactionReadDTO> getWeeklyReservations(ResarvationPeriod resarvationPeriod) {
        return reservationTransactionService.getResarvationTransaction(resarvationPeriod).stream().map(this::convertTransactionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationMasterReadDTO markAsCancelled(String id) {
        return convertToDTO(reservationMasterService.markAsCancelled(id));
    }

    @Override
    public ReservationMasterReadDTO getPayment(ReservationPaymentDTO reservationPaymentDTO) {
        return convertToDTO(reservationMasterService.getPayment(reservationPaymentDTO));
    }

    @Override
    public ReservationMasterReadDTO convertToDTO(ReservationMaster reservationMaster) {
        final var readDTO = modelMapper.map(reservationMaster, ReservationMasterReadDTO.class);
        if (Objects.nonNull(reservationMaster.getRoom())) {
            readDTO.setRoomCode(reservationMaster.getRoom().getCode());
            readDTO.setRoomId(reservationMaster.getRoom().getId());
        }
        return readDTO;
    }

    private ResarvationTransactionReadDTO convertTransactionDTO(ReservationTransaction reservationTransaction) {
        final var readDTO = new ResarvationTransactionReadDTO();
        readDTO.setId(reservationTransaction.getId());
        readDTO.setRoomCode(reservationTransaction.getReservationMaster().getRoom().getCode());
        readDTO.setResarvationDate(reservationTransaction.getReservationDate());
        return readDTO;
    }
}
