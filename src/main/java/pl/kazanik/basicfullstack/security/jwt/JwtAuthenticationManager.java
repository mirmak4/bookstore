/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security.jwt;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import pl.kazanik.basicfullstack.security.ApiAuthenticationManager;
import pl.kazanik.basicfullstack.security.exception.ApiAuthenticationException;

/**
 *
 * @author miron.maksymiuk
 */

@Component
public class JwtAuthenticationManager implements ApiAuthenticationManager {

    private final JwtTokenUtils jwtTokenUtils;
    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationManager(JwtTokenUtils jwtUtils, JwtUserDetailsService userDetailsService) {
        this.jwtTokenUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public boolean validateRequest(HttpServletRequest request) throws ApiAuthenticationException {
        String tokenHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;
        
        if (jwtTokenUtils.validateTokenHeader(token, tokenHeader)) {
            userName = jwtTokenUtils.extractUserNameFromToken(tokenHeader);
            token = jwtTokenUtils.extractTokenFromTokenHeader(tokenHeader);
        }
        else {
            throw new ApiAuthenticationException("bearer string in token not found");
        }
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (userName != null /* && auth != null */ ) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            
            if (jwtTokenUtils.validateJwtToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                return true;
            }
            else {
                throw new ApiAuthenticationException("jwt token expired");
            }
        }
        return false;
    }
    
}
