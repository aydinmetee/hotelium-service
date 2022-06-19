package tr.com.metea.hotelium.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.annotation.NoSession;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;
import tr.com.metea.hotelium.serviceview.auth.AuthUserServiceView;
import tr.com.metea.hotelium.util.JwtUtil;

import javax.validation.Valid;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserServiceView authUserServiceView;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @NoSession
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> creteToken(@RequestBody AuthUserLoginDTO authUserLoginDTO) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserLoginDTO.getUsername(),
                authUserLoginDTO.getPassword()));

        final var userDetails = authUserServiceView.loadUserByUsername(authUserLoginDTO.getUsername());
        final var authUser = authUserServiceView.getSessionInfo(userDetails.getUsername());


        return ResponseEntity.ok(jwtUtil.generateToken(userDetails.getUsername(), authUser.getId(), authUser.getOrgId()));
    }

    @NoSession
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@Valid @RequestBody AuthUserRegisterDTO authUserRegisterDTO) {
        return ResponseEntity.ok(authUserServiceView.save(authUserRegisterDTO));
    }
}
