package hw.spring.filters;

import hw.spring.model.user.role.Role;
import hw.spring.model.user.role.RoleName;
import org.slf4j.Logger;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminAuthorizationFilter extends OncePerRequestFilter {
    private final Logger logger;
    private final FilterUtil filterUtil;
    private RequestMatcher requestMatcher = new AntPathRequestMatcher("/users/**");

    public AdminAuthorizationFilter(FilterUtil filterUtil, Logger logger) {
        this.logger = logger;
        this.filterUtil = filterUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.debug("processing authentication for '{}'", request.getRequestURL());

        if(!requestMatcher.matches(request)) {
            chain.doFilter(request, response);
            return;
        }

        filterUtil.processRequestAndUserDetails(request, response, chain, userDetails -> {
            if(!userDetails.getAuthorities().stream().anyMatch(role -> {
                return ((Role)role).getName() == RoleName.ROLE_ADMIN;
            })) {
                try {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not an admin");
                } catch (IOException e) {
                    logger.debug(e.getMessage());
                }
            }
        });
    }
}
