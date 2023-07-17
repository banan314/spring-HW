package hw.spring.components;

import hw.spring.model.repositories.UserRepository;
import hw.spring.model.user.role.RoleName;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public JdbcAuthenticationManager(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("1000"));
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1001");
        }
        if(!user.getEnabled()) {
            throw new DisabledException("1002");
        }

        return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority(RoleName.ROLE_STUDENT.name())));
    }
}
