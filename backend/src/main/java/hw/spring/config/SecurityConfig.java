package hw.spring.config;

import hw.spring.filters.LoginFilter;
import hw.spring.services.TokenAuthenticationService;
import hw.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig
    extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserService userService;

        @Autowired
        private TokenAuthenticationService tokenAuthenticationService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .antMatchers("/activities/**").hasRole("STUDENT")
                    .antMatchers("/login").permitAll()
                    .antMatchers("/docs").permitAll()
                    //TODO: how about endpoints that gives access to users via activities
                    ;

            http.addFilterBefore(
                    new LoginFilter("/login", tokenAuthenticationService, userService, authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class
            );
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("STUDENT");
        }
}
