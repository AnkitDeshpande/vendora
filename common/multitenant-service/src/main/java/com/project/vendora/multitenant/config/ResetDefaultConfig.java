package com.project.vendora.multitenant.config;

import com.project.vendora.multitenant.core.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ResetDefaultConfig extends OncePerRequestFilter {

    /**
     * Performs the filtering logic to check for the JWT token.
     *
     * @param request     the HttpServletRequest object containing the request data
     * @param response    the HttpServletResponse object for sending the response
     * @param filterChain the filter chain for further processing of the request
     * @throws ServletException if there is a servlet-related exception
     * @throws IOException      if an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(final @NonNull HttpServletRequest request,
                                    final @NonNull HttpServletResponse response,
                                    final @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        TenantContext.clear();
        filterChain.doFilter(request, response);
    }
}
