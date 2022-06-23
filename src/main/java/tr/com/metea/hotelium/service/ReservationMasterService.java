package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.dto.ReservationBookingDTO;
import tr.com.metea.hotelium.dto.ReservationMasterSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ReservationMasterWriteDTO;
import tr.com.metea.hotelium.dto.ReservationPaymentDTO;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationMasterService extends
        BaseService<ReservationMaster, ReservationMasterWriteDTO, ReservationMasterSearchCriteriaDTO> {

    ReservationMaster markAsBooking(ReservationBookingDTO reservationBookingDTO);

    ReservationMaster markAsComplete(String id);

    ReservationMaster markAsCancelled(String id);

    ReservationMaster getPayment(ReservationPaymentDTO reservationPaymentDTO);

}
