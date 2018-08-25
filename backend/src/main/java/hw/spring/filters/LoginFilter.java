package hw.spring.filters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw.spring.dto.UserDTO;
import hw.spring.services.TokenAuthenticationService;
import hw.spring.services.UserAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final TokenAuthenticationService tokenAuthenticationService;
    private final UserDetailsService userDetailsService;
    Logger logger = Logger.getLogger("AUTHENTICATION");

    public LoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService, UserDetailsService
            userService, AuthenticationManager authenticationManager) {
        super(urlMapping);
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userDetailsService = userService;
        assert null != authenticationManager : "authentication manager must not be null";
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            AuthenticationException, IOException, ServletException {
        final UserDTO user = toUser(request);
        final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken();
        final UserDetails databaseUser = userDetailsService.loadUserByUsername(user.getUsername());
        loginToken.setAuthenticated(databaseUser.getPassword().equals(user.getPassword()));
        return getAuthenticationManager().authenticate(loginToken);
    }

    private UserDTO toUser(HttpServletRequest request) throws IOException {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
        } catch (JsonMappingException e) {
            logger.severe("JSON Mapping: " + e.getMessage());
        } catch (JsonParseException e) {
            logger.severe("JSON Parsing Error: " + e.getMessage());
        }
        return new UserDTO("default@gmail.com",
                "user",
                "password",
                "password",
                "Josh",
                "Kowalsky",
                (short)30);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain
            chain, Authentication authResult) throws IOException, ServletException {
        final UserDetails authenticatedUser = userDetailsService.loadUserByUsername(authResult.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
        tokenAuthenticationService.addJwtTokenToHeader(response, userAuthentication);
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
