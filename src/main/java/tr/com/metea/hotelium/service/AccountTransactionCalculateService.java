package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.dto.AccountTransactionBalanceDTO;

/**
 * @author Mete Aydin
 * @date 24.10.2021
 */
public interface AccountTransactionCalculateService {
    AccountTransactionBalanceDTO getMontly();

}
