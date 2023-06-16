/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author miron.maksymiuk
 */
@Configuration
@ComponentScan(basePackages = "pl.kazanik.basicfullstack")
public class SwaggerConfiguration {
    
    @Bean
    public Docket bookStoreApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.kazanik.basicfullstack.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(apiMetaData());
    }
    
    private ApiInfo apiMetaData() {
        return new ApiInfo(
                "Book Store REST API",
                "All apis for book store application",
                "1.0",
                "term and condition url",
                new Contact(
                        "Book Store Admin", 
                        "https://bookstore-mironmaksymiuk.b4a.run/", 
                        "book_store@gmail.com"
                ),
                "book store license",
                "license url"
        );
    }
    
}
