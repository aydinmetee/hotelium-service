package tr.com.metea.hotelium.domain;

import lombok.*;

import javax.persistence.*;

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
@Table(name = "customer")
public class Customer extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @Column(name = "legal_id", length = 11)
    private String legalId;
    @Column(name = "phone", length = 10)
    private String phone;
}
