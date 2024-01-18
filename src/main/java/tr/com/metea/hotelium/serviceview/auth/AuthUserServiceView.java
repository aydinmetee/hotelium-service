package tr.com.metea.hotelium.serviceview.auth;


import org.springframework.security.core.userdetails.UserDetails;
import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AuthUserServiceView {
    TokenResponseDTO login(AuthUserLoginDTO authUserLoginDTO);

    Boolean save(AuthUserRegisterDTO authUserRegisterDTO);

    UserDetails loadUserByUsername(String username);

    AuthUser getSessionInfo(String username);
}
