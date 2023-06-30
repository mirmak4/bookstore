/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security.jwt.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
//import lombok.NoArgsConstructor;

/**
 *
 * @author miron.maksymiuk
 */

//@Data
@AllArgsConstructor
//@NoArgsConstructor
@Getter
public class JwtAuthResponse implements Serializable {
    
    private String token;
}
