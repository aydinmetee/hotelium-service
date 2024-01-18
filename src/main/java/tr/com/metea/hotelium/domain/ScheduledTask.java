package tr.com.metea.hotelium.domain;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Mete Aydin
 * <p>
 * 19.08.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "scheduled_task")
public class ScheduledTask extends BaseEntity{
    @Column(name = "code",unique = true, nullable = false)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cron_exp", nullable = false)
    private String cronExp;
    @Column(name = "description", nullable = false)
    private String description;
}
