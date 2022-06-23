package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.domain.ReservationTransaction;
import tr.com.metea.hotelium.dto.ResarvationPeriod;
import tr.com.metea.hotelium.dto.ReservationMasterWriteDTO;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.repository.ReservationTransactionRepository;
import tr.com.metea.hotelium.service.ReservationTransactionService;
import tr.com.metea.hotelium.util.DateUtil;
import tr.com.metea.hotelium.util.SessionContext;

import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
@Service
@RequiredArgsConstructor
public class ReservationTransactionServiceImpl implements ReservationTransactionService {
    private final ReservationTransactionRepository reservationTransactionRepository;

    @Override
    @Transactional
    public void createAll(ReservationMaster reservationMaster) {
        Date lastDate = reservationMaster.getReservationDate();
        for (int index = 0; index < reservationMaster.getDuration().intValue(); index++) {
            final var reservationTransaction = new ReservationTransaction();
            reservationTransaction.setReservationDate(lastDate);
            reservationTransaction.setReservationMaster(reservationMaster);
            reservationTransactionRepository.save(reservationTransaction);
            lastDate = DateUtil.daysCalculator(lastDate, 1L);
        }
    }

    @Override
    public void checkRoomAvailability(ReservationMasterWriteDTO reservationMasterWriteDTO) {
        final var transactions = reservationTransactionRepository
                .findReservationTransactionsByReservationDateBetweenAndOrgIdAndReservationMasterRoomId(
                        reservationMasterWriteDTO.getReservationDate(),
                        DateUtil.daysCalculator(reservationMasterWriteDTO.getReservationDate(), reservationMasterWriteDTO.getDuration()),
                        SessionContext.getSessionData().getOrgId(),
                        reservationMasterWriteDTO.getRoomId()
                );
        if (!transactions.isEmpty()) {
            throw new ServiceExecutionException("Seçtiğiniz tarihte oda müsait değil.");
        }
    }

    @Override
    @Transactional
    public void clearAll(ReservationMaster reservationMaster) {
        final var transactions = reservationTransactionRepository
                .findReservationTransactionsByReservationMasterId(reservationMaster.getId());
        transactions.forEach(reservationTransaction -> {
            reservationTransactionRepository.deleteAll(transactions);
        });
    }

    @Override
    public List<ReservationTransaction> getResarvationTransaction(ResarvationPeriod resarvationPeriod) {
        Long period;
        if (ResarvationPeriod.MONTHLY.equals(resarvationPeriod)) {
            period = 29L;
        } else {
            period = 6L;
        }
        return reservationTransactionRepository.findReservationTransactionsByReservationDateBetweenAndOrgId(
                DateUtil.startOfDay(DateUtil.daysCalculator(new Date(), -1L)),
                DateUtil.endOfDay(DateUtil.daysCalculator(new Date(), period)),
                SessionContext.getSessionData().getOrgId());
    }
}
