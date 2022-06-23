package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.AccountTransaction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class AccountTransactionReadDTO extends AccountTransactionWriteDTO {
    private String id;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
    private AccountTransaction.TransactionType type;
    private Date reservationDate;
    private Long duration;
    private BigDecimal dailyAmount;
}
