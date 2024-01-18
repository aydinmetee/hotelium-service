package tr.com.metea.hotelium.domain;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "expenses_det")
public class ExpensesDetail extends BaseEntity {
    @Column(name = "price")
    private BigDecimal price;
    @JoinColumn(name = "sku_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sku sku;
    @JoinColumn(name = "mst_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ExpensesMaster expensesMaster;
    @Column(name = "quantity")
    private Long quantity;
}
