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
public class ExpensesDetailWriteDTO {
    private BigDecimal price;
    private Long quantity;
    private String skuId;
    private String expensesMasterId;
}
