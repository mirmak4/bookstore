/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kazanik.basicfullstack.security.ApiAuthenticationManager;
import pl.kazanik.basicfullstack.security.exception.ApiAuthenticationException;

/**
 *
 * @author miron.maksymiuk
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final ApiAuthenticationManager apiAuthenticationManager;

    public JwtRequestFilter(ApiAuthenticationManager apiAuthenticationManager) {
        this.apiAuthenticationManager = apiAuthenticationManager;
    }
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        
        try {
            apiAuthenticationManager.validateRequest(request);
        } catch (ApiAuthenticationException ex) {
            final String errorMessage = errorMessageBuilder(request.getRequestURI(), ex.getMessage());
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(errorMessage);
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String errorMessageBuilder(String requestUrl, String exMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append("\"error\": \"Unauthorized\" ");
        sb.append("\"message\": \"" + exMessage + "\"");
        sb.append("\"path\": \"")
          .append(requestUrl)
          .append("\"");
        sb.append("} ");
        return sb.toString();
    }
    
}
