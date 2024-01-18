package tr.com.metea.hotelium.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import tr.com.metea.hotelium.service.auth.AuthUserService;

import java.util.Locale;

@Configuration
public class AppConfig {
    private final AuthUserService authUserService;
    private final PasswordEncoder passwordEncoder;

    public AppConfig(AuthUserService authUserService, PasswordEncoder passwordEncoder) {
        this.authUserService = authUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authUserService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(HttpSecurity security, PasswordEncoder passwordEncoder) {
        AuthenticationManagerBuilder builder = security.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(authUserService).passwordEncoder(passwordEncoder);
        return builder.build();
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag("tr"));
        return localeResolver;
    }
}