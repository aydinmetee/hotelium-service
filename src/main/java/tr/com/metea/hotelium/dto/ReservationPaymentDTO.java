package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.AccountTransaction;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @since 22.03.2022
 */
@Getter
@Setter
public class ReservationPaymentDTO {
    private String masterId;
    private BigDecimal amount;
    private AccountTransaction.TransactionSource source;
    private AccountTransaction.Drawee drawee;
    private String draweeId;
}
