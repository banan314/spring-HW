package hw.spring.config;

import hw.spring.filters.LoginFilter;
import hw.spring.services.CustomUserDetailsService;
import hw.spring.services.TokenAuthenticationService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.inject.Inject;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomUserDetailsService userDetailsService;

    @Inject
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: must be a better workaround...
        /** INFO:
         * with CSRF enabled, after posting login credentials the server responded 403:
         * Forbidden","message":"Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'.","path":"/login"
         * */
        http.csrf().disable();

//        AuthenticationSuccessHandler successHandler = new
//                http.formLogin().successHandler();
        http
            .authorizeRequests()
            .antMatchers("/", "/docs", "/users/new").permitAll()
            .antMatchers("/login", "/logout", "/register").permitAll()
            .antMatchers("/users/**", "/activities/**").authenticated()
            .and()
            .formLogin()

        //TODO: how about endpoints that gives access to users via activities
        ;

        http.addFilterBefore(
                new LoginFilter("/users/**", tokenAuthenticationService, userDetailsService, authenticationManager()),
                UsernamePasswordAuthenticationFilter.class
        );

        LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandlerImp();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler);

        //TODO: add it
        /*http.addFilterBefore(
                new AuthenticationFilter(tokenAuthenticationService),
                UsernamePasswordAuthenticationFilter.class
        );*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
