package tr.com.metea.hotelium.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
