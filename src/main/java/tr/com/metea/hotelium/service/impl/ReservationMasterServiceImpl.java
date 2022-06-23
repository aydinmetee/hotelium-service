package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metea.hotelium.domain.AccountTransaction;
import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.dto.*;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.repository.ReservationMasterRepository;
import tr.com.metea.hotelium.service.*;
import tr.com.metea.hotelium.util.DateUtil;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Mete Aydin
 * date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class ReservationMasterServiceImpl extends BaseServiceImpl<ReservationMaster, ReservationMasterWriteDTO, ReservationMasterSearchCriteriaDTO>
        implements ReservationMasterService {
    @Lazy
    private final ReservationMasterRepository reservationMasterRepository;
    @Lazy
    private final ReservationDetailService reservationDetailService;
    private final ReservationTransactionService reservationTransactionService;
    private final RoomService roomService;
    @Lazy
    private final AccountTransactionService accountTransactionService;
    private final CustomerService customerService;
    private final CompanyService companyService;


    @Override
    @Transactional
    public ReservationMaster create(ReservationMasterWriteDTO reservationMasterWriteDTO) {
        final var room = roomService.getById(reservationMasterWriteDTO.getRoomId());
        if (Objects.isNull(room)) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.roomNotFound.exception"));
        }
        reservationTransactionService.checkRoomAvailability(reservationMasterWriteDTO);
        roomService.markAsReserved(room.getId());
        final var master = modelMapper.map(reservationMasterWriteDTO, ReservationMaster.class);
        master.setStatus(ReservationMaster.ReservationStatus.NEW);
        master.setRoom(room);
        master.setIsPayed(Boolean.FALSE);
        final var masterDB = reservationMasterRepository.save(master);
        reservationTransactionService.createAll(masterDB);
        return masterDB;
    }


    @Override
    public ReservationMaster markAsBooking(ReservationBookingDTO reservationBookingDTO) {
        final var reservationMaster = getById(reservationBookingDTO.getMasterId());
        if (DateUtil.isDateAfter(reservationMaster.getReservationDate(),
                reservationBookingDTO.getCheckInDate(), 0L)) {
            throw new ServiceExecutionException("Giriş tarihi reservasyon tarihinden önce");
        }
        reservationMaster.setCheckInDate(reservationBookingDTO.getCheckInDate());
        checkHasMasterGotDetail(reservationMaster);
        reservationMaster.setStatus(ReservationMaster.ReservationStatus.BOOKING);
        roomService.markAsFilled(reservationMaster.getRoom().getId());
        return reservationMasterRepository.save(reservationMaster);
    }

    @Override
    @Transactional
    public ReservationMaster markAsComplete(String id) {
        final var reservationMaster = getById(id);
        if (Boolean.FALSE.equals(accountTransactionService.checkPayment(reservationMaster.getId()))) {
            throw new ServiceExecutionException("Ödeme yapılmayan rezervasyon tamamlanamaz.");
        }
        reservationMaster.setStatus(ReservationMaster.ReservationStatus.COMPLETED);
        reservationMaster.setCheckOutDate(DateUtil.daysCalculator(reservationMaster.getReservationDate(),
                reservationMaster.getDuration()));
        roomService.markAsDirt(reservationMaster.getRoom().getId());
        final var resarvationDB = reservationMasterRepository.save(reservationMaster);
        reservationTransactionService.clearAll(resarvationDB);
        return resarvationDB;
    }

    @Override
    @Transactional
    public ReservationMaster markAsCancelled(String id) {
        final var reservationMaster = getById(id);
        checkReservationStatusForCancel(reservationMaster);
        if (Boolean.TRUE.equals(accountTransactionService.checkPayment(id))) {
            accountTransactionService.deleteForReservation(id);
        }
        reservationMaster.setStatus(ReservationMaster.ReservationStatus.CANCELLED);
        roomService.markAsClean(reservationMaster.getRoom().getId());
        final var resarvationDB = reservationMasterRepository.save(reservationMaster);
        reservationTransactionService.clearAll(resarvationDB);
        return resarvationDB;
    }

    @Override
    @Transactional
    public ReservationMaster getPayment(ReservationPaymentDTO reservationPaymentDTO) {
        final var reservationMaster = getById(reservationPaymentDTO.getMasterId());
        final var accountTransaction = new AccountTransactionWriteDTO();
        accountTransaction.setAmount(reservationPaymentDTO.getAmount().multiply(BigDecimal.valueOf(reservationMaster.getDuration())));
        accountTransaction.setDescription(reservationMaster.getRoom().getCode() + " KONAKLAMA BEDELİ");
        accountTransaction.setReservationMasterId(reservationPaymentDTO.getMasterId());
        accountTransaction.setSource(reservationPaymentDTO.getSource());
        accountTransaction.setDraweeId(reservationPaymentDTO.getDraweeId());
        if (AccountTransaction.Drawee.PERSON.equals(reservationPaymentDTO.getDrawee())) {
            final var customer = customerService.getById(reservationPaymentDTO.getDraweeId());
            accountTransaction.setDrawee(AccountTransaction.Drawee.PERSON);
            accountTransaction.setLegalId(customer.getLegalId());
            accountTransaction.setNameTitle(customer.getName() + " " + customer.getLastname());
        } else {
            final var company = companyService.getById(reservationPaymentDTO.getDraweeId());
            accountTransaction.setDrawee(AccountTransaction.Drawee.LEGAL);
            accountTransaction.setLegalId(company.getLegalNo());
            accountTransaction.setNameTitle(company.getNameTitle());
        }
        accountTransactionService.createIncome(accountTransaction);
        reservationMaster.setDailyAmount(reservationPaymentDTO.getAmount());
        reservationMaster.setIsPayed(Boolean.TRUE);
        return reservationMasterRepository.save(reservationMaster);
    }

    private void checkHasMasterGotDetail(ReservationMaster reservationMaster) {
        final var detailFilter = new ReservationDetailSearchCriteriaDTO();
        detailFilter.setReservationMasterId(reservationMaster.getId());
        final var details = reservationDetailService.search(detailFilter, PageRequest.of(0, 1));
        if (!details.hasContent()) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.detailNotFound.exception"));
        }
    }


    private void checkReservationStatusForCancel(ReservationMaster reservationMaster) {
        if (!ReservationMaster.ReservationStatus.NEW.equals(reservationMaster.getStatus())) {
            throw new ServiceExecutionException("Yapmak istediğiniz işlem için rezervasyonun durumu uygun değil.");
        }
    }

}
