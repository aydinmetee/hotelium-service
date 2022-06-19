package tr.com.metea.hotelium.aspect;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import tr.com.metea.hotelium.service.auth.AuthUserService;
import tr.com.metea.hotelium.util.JwtUtil;
import tr.com.metea.hotelium.util.SessionContext;
import tr.com.metea.hotelium.util.SessionData;

/**
 * @author Mete Aydın
 * @since 18.03.2022
 */
@Component
@Aspect
@Slf4j
@Order(0)
@RequiredArgsConstructor
public class SessionAspect {

    private final JwtUtil jwtUtil;
    private final AuthUserService authUserService;

    @Before("endPoint()")
    public void before() {
        var authentication = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var authUser = authUserService.getSessionInfo(authentication.getUsername());
        var sessionData = SessionData.builder()
                .userName(authUser.getUsername())
                .userId(authUser.getId())
                .orgId(authUser.getOrgId())
                .build();
        SessionContext.setSessionData(sessionData);
        log.info("Session data: {}", sessionData.toString());
    }

    @Pointcut("execution(* tr.com.metea.hotelium.endpoint..*(..)) " +
            "&& !@annotation(tr.com.metea.hotelium.annotation.NoSession)")
    public void endPoint() {
    }

    @After("endPoint()")
    public void after() {
        log.info("Removing session data.");
    }
}
