package hw.spring.filters;

import org.springframework.security.core.userdetails.UserDetails;

@FunctionalInterface
public interface FilterAction {
    void action(UserDetails userDetails);
}
