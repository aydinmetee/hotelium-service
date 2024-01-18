package tr.com.metea.hotelium.domain;

import lombok.*;

import jakarta.persistence.*;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "reservation_det")
public class ReservationDetail extends BaseEntity {
    @JoinColumn(name = "mst_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationMaster reservationMaster;
    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
