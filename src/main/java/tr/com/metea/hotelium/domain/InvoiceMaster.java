package tr.com.metea.hotelium.domain;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "invoice_mst")
public class InvoiceMaster extends BaseEntity {
    private Currency currency;
    private Customer customer;
    private String description;
    private DiscType gnrDiscType;
    private BigDecimal gnrDiscValue;
    private Date invoiceDate;
    private InvoiceStatus status;
    private BigDecimal totalAmount;
    @JoinColumn(name = "customer_country")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country customerCountry;
    @JoinColumn(name = "customer_city")
    @ManyToOne(fetch = FetchType.LAZY)
    private City customerCity;
    @JoinColumn(name = "customer_town")
    @ManyToOne(fetch = FetchType.LAZY)
    private Town customerTown;
    private String customerLegalId;
    private String customerNameTitle;
    private String customerAddress;
    private String invoiceSourceId;
    private String serialNo;


    public enum InvoiceStatus {
        NEW,
        CANCELLED,
        APPROVED,
        PREPARED
    }

}
