/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.kazanik.basicfullstack.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import pl.kazanik.basicfullstack.security.exception.ApiAuthenticationException;

/**
 *
 * @author miron.maksymiuk
 */

@Component
public interface ApiAuthenticationManager {
    
    public boolean validateRequest(HttpServletRequest request) throws ApiAuthenticationException;
}
