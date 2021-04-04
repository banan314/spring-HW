package hw.spring.services.userdetails;

import hw.spring.model.user.User;
import hw.spring.model.repositories.UserRepository;
import hw.spring.factories.JwtUserFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@Qualifier("jwtUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username + " + username + " not found"));

        return JwtUserFactory.create(user);
    }
}
