package tr.com.metea.hotelium.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import tr.com.metea.hotelium.util.JwtFilter;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 24.10.2021
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Lazy
    private final JwtFilter jwtFilter;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        return http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(a -> a
                        .requestMatchers(mvcMatcherBuilder.pattern("/auth/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/auth/register")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/org-def")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/combo/orgs")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/v3/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui.html")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/api-docs/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/api-docs")).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}