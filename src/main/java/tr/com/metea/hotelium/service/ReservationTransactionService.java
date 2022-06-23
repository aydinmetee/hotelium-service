package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.domain.ReservationTransaction;
import tr.com.metea.hotelium.dto.ResarvationPeriod;
import tr.com.metea.hotelium.dto.ReservationMasterWriteDTO;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
public interface ReservationTransactionService {
    void createAll(ReservationMaster reservationMaster);

    void checkRoomAvailability(ReservationMasterWriteDTO reservationMasterWriteDTO);

    void clearAll(ReservationMaster reservationMaster);

    List<ReservationTransaction> getResarvationTransaction(ResarvationPeriod resarvationPeriod);
}
