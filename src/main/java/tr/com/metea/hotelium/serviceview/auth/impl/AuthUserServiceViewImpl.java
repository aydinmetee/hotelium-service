package tr.com.metea.hotelium.serviceview.auth.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;
import tr.com.metea.hotelium.service.auth.AuthUserService;
import tr.com.metea.hotelium.serviceview.auth.AuthUserServiceView;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AuthUserServiceViewImpl implements AuthUserServiceView {
    private final AuthUserService authUserService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDTO login(AuthUserLoginDTO authUserLoginDTO) {
        return authUserService.login(authUserLoginDTO);
    }

    @Override
    public void register(AuthUserRegisterDTO authUserRegisterDTO) {
        final var user = modelMapper.map(authUserRegisterDTO, AuthUser.class);
        user.setPassword(passwordEncoder.encode(authUserRegisterDTO.getPassword()));
        authUserService.register(user);
    }
}
