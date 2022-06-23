package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.AccountTransaction;
import tr.com.metea.hotelium.dto.*;

import java.text.ParseException;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AccountTransactionServiceView extends
        BaseServiceView<AccountTransaction, AccountTransactionWriteDTO, AccountTransactionReadDTO, AccountTransactionSearchCriteriaDTO> {
    AccountTransactionReadDTO createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO);


    AccountTransactionBalanceDTO getMontly() throws ParseException;

    AccountTransactionReadDTO updateSource(AccountTransactionSourceUpdateDTO updateDTO);
}
