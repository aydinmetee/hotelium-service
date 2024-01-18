package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * @since 24.10.2021
 */
@Getter
@Setter
public class AccountTransactionBalanceDTO {
    private String monthName;
    private BigDecimal totalIncome;
    private BigDecimal cashIncome;
    private BigDecimal debitIncome;
    private BigDecimal bankIncome;
    private BigDecimal totalExpense;
    private BigDecimal caseBalance;
}
