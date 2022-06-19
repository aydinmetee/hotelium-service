package tr.com.metea.hotelium.serviceview.auth.impl;

import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.service.auth.impl.AuthUserServiceImpl;
import tr.com.metea.hotelium.serviceview.auth.AuthUserServiceView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AuthUserServiceViewImpl implements AuthUserServiceView {
    private final AuthUserServiceImpl authUserService;

    @Override
    public Boolean login(AuthUserLoginDTO authUserLoginDTO) {
        return authUserService.login(authUserLoginDTO);
    }

    @Override
    public Boolean save(AuthUserRegisterDTO authUserRegisterDTO) {
        final var authUser = authUserService.save(authUserRegisterDTO);
        if (Objects.isNull(authUser)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return authUserService.loadUserByUsername(username);
    }

    @Override
    public AuthUser getSessionInfo(String username) {
        return authUserService.getSessionInfo(username);
    }
}
