package tr.com.metea.hotelium.endpoint;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.annotation.NoSession;
import tr.com.metea.hotelium.dto.auth.AuthUserLoginDTO;
import tr.com.metea.hotelium.dto.auth.AuthUserRegisterDTO;
import tr.com.metea.hotelium.dto.auth.TokenResponseDTO;
import tr.com.metea.hotelium.serviceview.auth.AuthUserServiceView;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserServiceView authUserServiceView;


    @NoSession
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthUserLoginDTO authUserLoginDTO) {
        return ResponseEntity.ok(authUserServiceView.login(authUserLoginDTO));
    }

    @NoSession
    @PostMapping("/register")
    public void register(@Valid @RequestBody AuthUserRegisterDTO authUserRegisterDTO) {
        authUserServiceView.register(authUserRegisterDTO);
    }
}
