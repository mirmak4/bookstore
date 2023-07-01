/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security;

import java.util.Arrays;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author miron.maksymiuk
 */

@Configuration
public class CorsSecurityConfig implements CorsConfigurationSource {

    @Bean
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000", "https://bookstoreclientdev-mironmaksymiuk.b4a.run",
                "https://bookstoreclient-mironmaksymiuk.b4a.run" ));
        corsConfig.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
        //the below three lines will add the relevant CORS response headers
//        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedOrigin("http://localhost:3000");
        corsConfig.addAllowedOrigin("https://bookstoreclientdev-mironmaksymiuk.b4a.run");
        corsConfig.addAllowedOrigin("https://bookstoreclient-mironmaksymiuk.b4a.run");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return corsConfig;
    }
    
}
