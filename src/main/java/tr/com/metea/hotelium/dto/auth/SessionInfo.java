package tr.com.metea.hotelium.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tr.com.metea.hotelium.domain.auth.AuthUser;

import java.io.Serializable;

/**
 * @author Mete Aydin
 * @date 24.10.2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SessionInfo implements Cloneable, Serializable {
    private String username;
    private AuthUser.AuthType type;
    private String userId;

    @Override
    public SessionInfo clone() {
        var cloneSessionInfo = new SessionInfo();
        cloneSessionInfo.setUserId(this.userId);
        cloneSessionInfo.setUsername(this.username);
        cloneSessionInfo.setType(this.type);

        return cloneSessionInfo;
    }

}