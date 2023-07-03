/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kazanik.basicfullstack.security.jwt.dto.JwtAuthRequest;
import pl.kazanik.basicfullstack.security.jwt.dto.JwtAuthResponse;
import pl.kazanik.basicfullstack.security.jwt.JwtTokenUtils;
import pl.kazanik.basicfullstack.security.jwt.JwtUserDetailsService;

/**
 *
 * @author miron.maksymiuk
 */
//@ExceptionHandler(DisabledException.class)
@RestController
@RequestMapping(path = "login")
@CrossOrigin(origins = { "http://localhost:3000", 
    "https://bookstoreclientdev-mironmaksymiuk.b4a.run",
    "https://bookstoreclient-mironmaksymiuk.b4a.run"})
public class LoginController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    public LoginController(AuthenticationManager authenticationManager, 
            JwtUserDetailsService userDetailsService, JwtTokenUtils jwtTokenUtils) {
        
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = userDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }
    
    @PostMapping
    public ResponseEntity<JwtAuthResponse> authenticate(@RequestBody JwtAuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
        }
        catch (DisabledException | BadCredentialsException e) {
//            throw new RuntimeException("USER_DISABLED", e);
            JwtAuthResponse res = new JwtAuthResponse();
            res.setMessage("Unauthorized");
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(
                request.getUsername());
        String jwtToken = jwtTokenUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtAuthResponse(jwtToken, "Success"));
    }
}
