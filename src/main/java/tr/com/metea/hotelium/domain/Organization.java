package tr.com.metea.hotelium.domain;

import lombok.*;
import tr.com.metea.hotelium.util.IdGenerator;

import jakarta.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "name", unique = true)
    private String name;

    @PrePersist
    public void onPrePersist() {
        setId(IdGenerator.getUUID());
    }
}
