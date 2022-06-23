package tr.com.metea.hotelium.repository;

import tr.com.metea.hotelium.domain.ReservationTransaction;

import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
public interface ReservationTransactionRepository extends BaseRepository<ReservationTransaction> {
    List<ReservationTransaction> findReservationTransactionsByReservationDateBetweenAndOrgIdAndReservationMasterRoomId(Date toDate, Date fromDate,
                                                                                                                       String orgId, String roomId);

    List<ReservationTransaction> findReservationTransactionsByReservationMasterId(String id);

    List<ReservationTransaction> findReservationTransactionsByReservationDateBetweenAndOrgId(Date toDate, Date fromDate,
                                                                                             String orgId);
}
