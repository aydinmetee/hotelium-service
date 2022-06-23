package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.AccountTransaction;
import tr.com.metea.hotelium.dto.*;
import tr.com.metea.hotelium.service.AccountTransactionCalculateService;
import tr.com.metea.hotelium.service.AccountTransactionService;
import tr.com.metea.hotelium.serviceview.AccountTransactionServiceView;

import java.util.Objects;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AccountTransactionServiceViewImpl extends
        BaseServiceViewImpl<AccountTransaction, AccountTransactionWriteDTO, AccountTransactionReadDTO, AccountTransactionSearchCriteriaDTO>
        implements AccountTransactionServiceView {
    private final AccountTransactionService accountTransactionService;
    private final AccountTransactionCalculateService accountTransactionCalculateService;

    @Override
    public AccountTransactionReadDTO createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        return convertToDTO(accountTransactionService.createExpense(accountTransactionWriteDTO));
    }

    @Override
    public AccountTransactionBalanceDTO getMontly() {
        return accountTransactionCalculateService.getMontly();
    }

    @Override
    public AccountTransactionReadDTO updateSource(AccountTransactionSourceUpdateDTO updateDTO) {
        return convertToDTO(accountTransactionService.updateTransactionSource(updateDTO));
    }

    @Override
    public AccountTransactionReadDTO convertToDTO(AccountTransaction accountTransaction) {
        final var readDTO = modelMapper.map(accountTransaction, AccountTransactionReadDTO.class);
        if (Objects.nonNull(accountTransaction.getReservationMaster())) {
            readDTO.setReservationMasterId(accountTransaction.getReservationMaster().getId());
            readDTO.setReservationDate(accountTransaction.getReservationMaster().getCheckInDate());
            readDTO.setDuration(accountTransaction.getReservationMaster().getDuration());
            readDTO.setDailyAmount(accountTransaction.getReservationMaster().getDailyAmount());
        }
        return readDTO;
    }
}
