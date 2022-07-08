package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Getter
@Setter
public class ExpensesMasterWriteDTO {
    private BigDecimal amount;
    private String reservationMasterId;
}
