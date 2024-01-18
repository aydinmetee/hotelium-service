package tr.com.metea.hotelium.service.auth;


import org.springframework.security.core.userdetails.UserDetailsService;
import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AuthUserService extends UserDetailsService {
    TokenResponseDTO login(AuthUserLoginDTO authUserLoginDTO);

    AuthUser getSessionInfo(String username);

    AuthUser save(AuthUserRegisterDTO authUserRegisterDTO);

}
