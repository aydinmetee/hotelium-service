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
@Table(name = "room")
public class Room extends BaseEntity {
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "capacity")
    private Long capacity;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    public enum RoomStatus {
        CLEAN,
        RESERVED,
        DIRTY,
        CLOSED,
        FILLED
    }
}
