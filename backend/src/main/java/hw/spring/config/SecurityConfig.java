package hw.spring.config;

import hw.spring.filters.LoginFilter;
import hw.spring.services.TokenAuthenticationService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig
    extends WebSecurityConfigurerAdapter {

    @Inject private UserDetailsService userService;
    @Inject private TokenAuthenticationService tokenAuthenticationService;

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
                    .antMatchers("/", "/docs", "/users/new").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/activities/**").hasRole("STUDENT")
                    .and()
                    .logout().permitAll()

                    //TODO: how about endpoints that gives access to users via activities
                    ;

            http.addFilterBefore(
                    new LoginFilter("/login", tokenAuthenticationService, userService, authenticationManager()),
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

        @GetMapping(value = "/login")
        public String login() {
            return "login";
        }
}
