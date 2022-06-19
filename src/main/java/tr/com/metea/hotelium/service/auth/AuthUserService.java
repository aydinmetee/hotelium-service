package tr.com.metea.hotelium.service.auth;


import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface AuthUserService {
    Boolean login(AuthUserLoginDTO authUserLoginDTO);

    AuthUser getSessionInfo();

    AuthUser getSessionInfo(String username);

    AuthUser save(AuthUserRegisterDTO authUserRegisterDTO);

}
