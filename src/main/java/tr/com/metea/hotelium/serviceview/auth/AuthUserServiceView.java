package tr.com.metea.hotelium.serviceview.auth;



import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface AuthUserServiceView {
    TokenResponseDTO login(AuthUserLoginDTO authUserLoginDTO);

    void register(AuthUserRegisterDTO authUserRegisterDTO);
}
