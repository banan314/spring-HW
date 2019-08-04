package hw.spring.config;

import hw.spring.security.JwtAuthorizationTokenFilter;
import hw.spring.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(SecurityProperties.IGNORED_ORDER)
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomUserDetailsService userDetailsService;

    @Inject
    private JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
            csrf().disable()

            .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                @Override
                public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
                    if (null != authException) {
                        // This is invoked when user tries to access a secured REST resource without supplying any credentials
                        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized ...");
                    }
                }
            }).and()

            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()

            .antMatchers("/auth/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
                .cacheControl();

//        AuthenticationSuccessHandler successHandler = (request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK);
//        AuthenticationFailureHandler failureHandler = (request, response, exception) -> {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().print("Bad credentials");
//        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(
                    HttpMethod.POST,
                    authenticationPath
            )

            // allow anonymous resource requests
            .and()
            .ignoring()
            .antMatchers(
                    HttpMethod.GET,
                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            );
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    public class PlainTextPasswordEncoder implements PasswordEncoder {
//
//        @Override
//        public String encode(CharSequence rawPassword) {
//            return rawPassword.toString();
//        }
//
//        @Override
//        public boolean matches(CharSequence rawPassword, String encodedPassword) {
//            return rawPassword.toString().equals(encodedPassword);
//        }
//    }
}
