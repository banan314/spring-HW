package hw.spring.config;

import hw.spring.filters.AdminAuthorizationFilter;
import hw.spring.filters.JwtAuthorizationTokenFilter;
import hw.spring.services.userdetails.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@Order(SecurityProperties.IGNORED_ORDER)
@EnableWebSecurity
public class SecurityConfig {

    @Inject
    private CustomUserDetailsService userDetailsService;

    @Inject
    private JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Inject
    AdminAuthorizationFilter adminAuthorizationFilter;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Value("${frontend.origin}")
    private String frontendOrigin;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "v2/api-docs",
            "/swagger-resources",
            "swagger-resources",
            "/swagger-resources/**",
            "swagger-resources/**",
            "/configuration/ui",
            "configuration/ui",
            "/configuration/security",
            "configuration/security",
            "/swagger-ui.html",
            "swagger-ui.html",
            "webjars/**",
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**",
            // CSA Controllers
            "/csa/api/token",
            // Actuators
            "/actuator/**",
            "/health/**"
    };

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint((request, response, authException) -> {
                    if (null != authException) {
                        // This is invoked when user tries to access a secured REST resource without supplying any credentials
                        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized ...");
                    }
                }))

                // don't create session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(this.authenticationPath + "/**").permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(adminAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(urlOrigins(frontendOrigin));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    private List<String> urlOrigins(String origin) {
        return List.of("http://", "https://")
                .stream()
                .map(s -> s + origin)
                .toList();
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }
}
