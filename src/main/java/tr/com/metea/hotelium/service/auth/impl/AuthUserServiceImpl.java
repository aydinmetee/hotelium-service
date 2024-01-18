package tr.com.metea.hotelium.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.auth.AuthUser;
import tr.com.metea.hotelium.domain.auth.AuthUserStatus;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;
import tr.com.metea.hotelium.exception.LoginExecutionException;
import tr.com.metea.hotelium.repository.auth.AuthUserRepository;
import tr.com.metea.hotelium.service.auth.AuthUserService;
import tr.com.metea.hotelium.util.JwtUtil;
import tr.com.metea.hotelium.util.MessageUtil;

import java.util.ArrayList;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final MessageUtil messageUtil;
    @Lazy
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        final var authUser = getSessionInfo(username);
        if (AuthUserStatus.PASSIVE.equals(authUser.getAuthUserStatus())) {
            throw new LoginExecutionException(messageUtil.get("auth-user.passive-user.exception"));
        }
        return new User(authUser.getUsername(), authUser.getPassword(), new ArrayList<>());
    }

    @Override
    public TokenResponseDTO login(AuthUserLoginDTO authUserLoginDTO) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authUserLoginDTO.getUsername(),
                            authUserLoginDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new LoginExecutionException(messageUtil.get("auth-user.bad-credentials.exception"));
        }
        final var authUser = getSessionInfo(authUserLoginDTO.getUsername());

        return jwtUtil.generateToken(authUser.getUsername(), authUser.getId(), authUser.getOrgId());
    }

    @Override
    public AuthUser getSessionInfo(String username) {
        return authUserRepository.findAuthUserByUsername(username);
    }

    @Override
    public AuthUser register(AuthUser authUser) {
        return authUserRepository.save(authUser);
    }
}
