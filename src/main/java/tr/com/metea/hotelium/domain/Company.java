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
@Table(name = "company")
public class Company extends BaseEntity {
    @Column(name = "name_title")
    private String nameTitle;
    @Column(name = "address")
    private String address;
    @Column(name = "legal_no")
    private String legalNo;
    @Column(name = "tax_office")
    private String taxOffice;
    @JoinColumn(name = "country_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
    @JoinColumn(name = "city_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
    @JoinColumn(name = "town_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Town town;
}
