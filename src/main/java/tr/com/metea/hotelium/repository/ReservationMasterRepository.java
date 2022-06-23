package tr.com.metea.hotelium.repository;

import tr.com.metea.hotelium.domain.ReservationMaster;

import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface ReservationMasterRepository extends BaseRepository<ReservationMaster> {

    List<ReservationMaster> findReservationMastersByReservationDateBetweenAndOrgId(Date toDate, Date fromDate,
                                                                                   String orgId);
}
