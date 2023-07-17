package hw.spring.filters;

import org.slf4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    private final Logger logger;
    private final FilterUtil filterUtil;
    private RequestMatcher requestMatcher = new AntPathRequestMatcher("/users/**", "/activities/**");

    public JwtAuthorizationTokenFilter(FilterUtil filterUtil, Logger logger) {
        this.logger = logger;
        this.filterUtil = filterUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!requestMatcher.matches(request)) {
            chain.doFilter(request, response);
            return;
        }

        logger.debug("processing authentication for '{}'", request.getRequestURL());

        filterUtil.processRequestAndUserDetails(request, response, chain, userDetails -> {
            String username = userDetails.getUsername();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            logger.info("authorized user '{}', setting security context", username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }
}
