package tr.com.metea.hotelium.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "sku")
public class Sku extends BaseEntity{
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "stock")
    private Long stock;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
}
