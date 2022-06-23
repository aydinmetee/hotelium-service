package tr.com.metea.hotelium.repository;

import tr.com.metea.hotelium.domain.AccountTransaction;

import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AccountTransactionRepository extends BaseRepository<AccountTransaction> {
    List<AccountTransaction> findAccountTransactionsByCreDateBetweenAndTypeAndSource(
            Date toDate, Date fromDate, AccountTransaction.TransactionType type, AccountTransaction.TransactionSource source);

}
