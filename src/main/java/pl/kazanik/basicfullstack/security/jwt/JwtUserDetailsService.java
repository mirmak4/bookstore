/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security.jwt;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author miron.maksymiuk
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public JwtUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    // this is the function which should be refactored !!!
    // valid username: randomuser123
    // valid password: password
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("randomuser123".equals(username)) { 
            return new User("randomuser123", 
                passwordEncoder.encode("password"),
                new ArrayList<>()); 
        } else { 
            throw new UsernameNotFoundException("User not found with username: " + username); 
        } 
    }
    
}
