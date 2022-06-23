package tr.com.metea.hotelium.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "CRE_USER", length = 36)
    private String creUser;
    @Column(name = "cre_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creDate;
    @Column(name = "UPD_USER", length = 36)
    private String updUser;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;
    @Column(name = "valid")
    @Type(type = "yes_no")
    private Boolean valid;
    private String code;
    private String name;
}
