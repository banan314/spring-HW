package hw.spring.filters;

import org.springframework.context.annotation.Bean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FilterUtil {
    void processRequestAndUserDetails(HttpServletRequest request, HttpServletResponse response, FilterChain chain, FilterAction filterAction) throws ServletException, IOException;
}
