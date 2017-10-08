package hw.spring.config;

import hw.spring.filters.AuthenticationFilter;
import hw.spring.filters.LoginFilter;
import hw.spring.services.TokenAuthenticationService;
import hw.spring.services.user.UserDetailsServiceManager;
import hw.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig
    extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsServiceManager userService;

        @Autowired
        private TokenAuthenticationService tokenAuthenticationService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //TODO: must be a better workaround...
            /** INFO:
            * with CSRF enabled, after posting login credentials the server responded 403:
            * Forbidden","message":"Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'.","path":"/login"
            * */
            http.csrf().disable();

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

            //TODO: add it
            /*http.addFilterBefore(
                    new AuthenticationFilter(tokenAuthenticationService),
                    UsernamePasswordAuthenticationFilter.class
            );*/
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            //TODO: check the password against database
//           auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("STUDENT");
        }
}
