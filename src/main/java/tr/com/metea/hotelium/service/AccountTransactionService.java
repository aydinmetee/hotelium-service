package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.AccountTransaction;
import tr.com.metea.hotelium.dto.AccountTransactionSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.AccountTransactionSourceUpdateDTO;
import tr.com.metea.hotelium.dto.AccountTransactionWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AccountTransactionService extends BaseService<AccountTransaction, AccountTransactionWriteDTO, AccountTransactionSearchCriteriaDTO>{
    AccountTransaction createIncome(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransaction createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO);

    AccountTransaction updateTransactionSource(AccountTransactionSourceUpdateDTO updateDTO);

    Boolean checkPayment(String reservationMasterId);

    void deleteForReservation(String resarvationMasterId);
}
