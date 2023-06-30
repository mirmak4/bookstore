/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security.exception;

/**
 *
 * @author miron.maksymiuk
 */
public class ApiAuthenticationException extends Exception {
    
    private String message;
    private Throwable ex;

    public ApiAuthenticationException(String message) {
        super(message);
        this.message = message;
    }

    public ApiAuthenticationException(String message, Throwable ex) {
        super(message, ex);
        this.message = message;
        this.ex = ex;
    }
    
}
