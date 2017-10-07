package hw.spring.services.user;

import hw.spring.model.user.JavadevUserDetails;
import hw.spring.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;

@Service
public class UserDetailsServiceManager implements UserDetailsService {

    private final UserService service;

    @Inject
    UserDetailsServiceManager(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails ud = service.loadUserByUsername(s);
        if(null == ud)
            //TODO: think of sth real...
            return new JavadevUserDetails("user", "password", Collections.singleton(User.Role.STUDENT::toString));
//            throw new UsernameNotFoundException("User " + s + " not found!");
        return ud;
    }
}
