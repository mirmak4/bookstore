/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author miron.maksymiuk
 */
@Configuration
public class ModelMapperConfiguration {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
