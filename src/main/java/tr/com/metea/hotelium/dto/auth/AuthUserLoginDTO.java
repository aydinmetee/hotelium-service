package tr.com.metea.hotelium.dto.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;

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
public class AuthUserLoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
