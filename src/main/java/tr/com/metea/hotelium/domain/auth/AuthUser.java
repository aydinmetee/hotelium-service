package tr.com.metea.hotelium.domain.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tr.com.metea.hotelium.util.IdGenerator;
import tr.com.metea.hotelium.util.SessionContext;

import java.util.Date;
import java.util.Objects;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "ORG_ID", nullable = false, length = 36)
    private String orgId;
    @Column(name = "username", length = 100, unique = true)
    private String username;
    @Column(name = "password", length = 200)
    private String password;
    @Column(name = "email", length = 100, unique = true)
    private String email;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AuthUserStatus authUserStatus;
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


    @PrePersist
    public void onPrePersist() {
        setId(IdGenerator.getUUID());
        setCreUser("SYSTEM");
        if (Objects.isNull(getAuthUserStatus())) {
            setAuthUserStatus(AuthUserStatus.ACTIVE);
        }
        this.creDate = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdUser(Objects.isNull(SessionContext.getSessionData()) ? "SYSTEM" :
                SessionContext.getSessionData().getUserId());
        this.updDate = new Date();
    }

}
