package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.AccountTransaction;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class AccountTransactionWriteDTO {
    private BigDecimal amount;
    private AccountTransaction.TransactionSource source;
    private String description;
    private String reservationMasterId;
    private AccountTransaction.Drawee drawee;
    private String legalId;
    private String nameTitle;
    private String draweeId;

}
