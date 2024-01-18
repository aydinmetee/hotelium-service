package tr.com.metea.hotelium.dto.auth;

import lombok.*;
import tr.com.metea.hotelium.domain.auth.AuthUser;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRegisterDTO extends AuthUserLoginDTO {
    private String orgId;
    private String email;
    private String firstName;
    private String lastName;
}
