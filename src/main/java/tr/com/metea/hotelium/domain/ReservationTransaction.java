package tr.com.metea.hotelium.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mete Aydin
 * @since 21.03.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "reservation_transaction")
public class ReservationTransaction extends BaseEntity {
    @Column(name = "reservation_date")
    private Date reservationDate;
    @JoinColumn(name = "mst_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationMaster reservationMaster;
}
