package hw.spring.filters;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.FilterChain;
import java.io.IOException;

public interface FilterUtil {
    void processRequestAndUserDetails(HttpServletRequest request, HttpServletResponse response, FilterChain chain, FilterAction filterAction) throws ServletException, IOException;
}
