package hw.spring.filters;

import hw.spring.model.user.role.Role;
import hw.spring.model.user.role.RoleName;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminAuthorizationFilter extends OncePerRequestFilter {
    private final Logger logger;
    private final FilterUtil filterUtil;

    public AdminAuthorizationFilter(FilterUtil filterUtil, Logger logger) {
        this.logger = logger;
        this.filterUtil = filterUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.debug("processing authentication for '{}'", request.getRequestURL());

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
