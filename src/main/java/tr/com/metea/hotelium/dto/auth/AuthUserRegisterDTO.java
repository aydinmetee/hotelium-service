package tr.com.metea.hotelium.dto.auth;

import lombok.*;
import tr.com.metea.hotelium.domain.auth.AuthUser;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRegisterDTO extends AuthUserLoginDTO {
    private AuthUser.AuthType type;
    private String orgId;
}
