package tr.com.metea.hotelium.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "reservation_mst")
public class ReservationMaster extends BaseEntity {
    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Column(name = "description")
    private String description;
    @Column(name = "reservation_no")
    private String reservationNo;
    @Column(name = "check_in")
    private Date checkInDate;
    @Column(name = "check_out")
    private Date checkOutDate;
    @Column(name = "reservation_date")
    private Date reservationDate;
    @Column(name = "duration")
    private Long duration;
    @Column(name = "dailyAmount")
    private BigDecimal dailyAmount;
    @Column(name = "is_payed")
    @Type(type = "yes_no")
    private Boolean isPayed;

    public enum ReservationStatus {
        NEW,
        BOOKING,
        COMPLETED,
        CANCELLED
    }
}
