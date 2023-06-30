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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author miron.maksymiuk
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final JwtUserDetailsService userDetailsService;

    public JwtRequestFilter(JwtTokenUtils jwtUtils, JwtUserDetailsService userDetailsService) {
        this.jwtTokenUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        
        String tokenHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;
        
        if (jwtTokenUtils.validateTokenHeader(token, tokenHeader)) {
            userName = jwtTokenUtils.extractUserNameFromToken(tokenHeader);
            token = jwtTokenUtils.extractTokenFromTokenHeader(tokenHeader);
        }
        else {
            throw new RuntimeException("bearer string in token not found");
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (userName != null /* && auth != null */ ) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            
            if (jwtTokenUtils.validateJwtToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
}
