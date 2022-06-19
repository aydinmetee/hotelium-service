package tr.com.metea.hotelium.serviceview.auth;


import org.springframework.security.core.userdetails.UserDetails;
import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AuthUserServiceView {
    Boolean login(AuthUserLoginDTO authUserLoginDTO);

    Boolean save(AuthUserRegisterDTO authUserRegisterDTO);

    UserDetails loadUserByUsername(String username);

    AuthUser getSessionInfo(String username);
}
