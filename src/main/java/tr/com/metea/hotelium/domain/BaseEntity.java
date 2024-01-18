package tr.com.metea.hotelium.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.com.metea.hotelium.util.IdGenerator;
import tr.com.metea.hotelium.util.SessionContext;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mete Aydin
 * @since 17.10.2021
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "ORG_ID", nullable = false, length = 36)
    private String orgId;
    @Column(name = "CRE_USER", length = 36)
    private String creUser;
    @Column(name = "CRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creDate;
    @Column(name = "UPD_USER", length = 36)
    private String updUser;
    @Column(name = "UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;
    @Column(name = "VALID")
    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    private Boolean valid;


    @PrePersist
    public void onPrePersist() {
        setId(IdGenerator.getUUID());
        setOrgId(SessionContext.getSessionData().getOrgId());
        setCreUser(SessionContext.getSessionData().getUserId());
        setValid(Boolean.TRUE);
        this.creDate = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdUser(SessionContext.getSessionData().getUserId());
        this.updDate = new Date();
    }

}
