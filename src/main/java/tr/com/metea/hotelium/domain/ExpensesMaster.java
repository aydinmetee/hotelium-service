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
@Table(name = "expenses_mst")
public class ExpensesMaster extends BaseEntity {
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ExpensesStatus status;
    @JoinColumn(name = "reservation_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ReservationMaster reservationMaster;

    public enum ExpensesStatus {
        UNPAID,
        PAID,
        CANCELLED
    }
}
