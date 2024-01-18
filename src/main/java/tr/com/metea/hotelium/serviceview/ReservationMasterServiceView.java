package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.dto.*;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface ReservationMasterServiceView extends
        BaseServiceView<ReservationMaster, ReservationMasterWriteDTO, ReservationMasterReadDTO, ReservationMasterSearchCriteriaDTO> {

    ReservationMasterReadDTO markAsBooking(ReservationBookingDTO reservationBookingDTO);

    ReservationMasterReadDTO markAsComplete(String id);

    List<ResarvationTransactionReadDTO> getWeeklyReservations(ResarvationPeriod resarvationPeriod);

    ReservationMasterReadDTO markAsCancelled(String id);

    ReservationMasterReadDTO getPayment(ReservationPaymentDTO reservationPaymentDTO);

}
