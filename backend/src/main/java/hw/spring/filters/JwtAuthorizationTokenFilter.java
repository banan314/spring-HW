package hw.spring.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    private static final RequestMatchers requestMatchers = new RequestMatchers("/users/**", "/activities/**");
    private final Logger logger;
    private final FilterUtil filterUtil;

    public JwtAuthorizationTokenFilter(FilterUtil filterUtil, Logger logger) {
        this.logger = logger;
        this.filterUtil = filterUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!requestMatchers.matches(request)) {
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

    static class RequestMatchers {
        private final List<RequestMatcher> requestMatchers = new ArrayList<>();

        RequestMatchers(String... paths) {
            for (var path : paths) {
                requestMatchers.add(new AntPathRequestMatcher(path));
            }
        }

        boolean matches(HttpServletRequest request) {
            return requestMatchers.stream()
                    .anyMatch(requestMatcher -> requestMatcher.matches(request));
        }
    }
}
