package hw.spring.filters;

import hw.spring.model.user.role.Role;
import hw.spring.model.user.role.RoleName;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Log4j2
public class AdminAuthorizationFilter extends OncePerRequestFilter {
    private static final RequestMatcher requestMatcher = new AntPathRequestMatcher("/users/**");
    private final FilterUtil filterUtil;

    public AdminAuthorizationFilter(FilterUtil filterUtil) {
        this.filterUtil = filterUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.debug("processing authentication for '%s'".formatted(request.getRequestURL()));

        if(!requestMatcher.matches(request)) {
            chain.doFilter(request, response);
            return;
        }

        filterUtil.processRequestAndUserDetails(request, response, chain, userDetails -> {
            if (userDetails.getAuthorities().stream()
                    .noneMatch(role -> ((Role) role).getName() == RoleName.ROLE_ADMIN)) {
                try {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not an admin");
                } catch (IOException e) {
                    logger.debug(e.getMessage());
                }
            }
        });
    }
}
