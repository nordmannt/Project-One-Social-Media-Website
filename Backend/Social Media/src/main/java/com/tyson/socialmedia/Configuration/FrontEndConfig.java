package com.tyson.socialmedia.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontEndConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply CORS settings to all endpoints
                    .allowedOrigins("http://localhost:5173") // Frontend origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // HTTP methods
                    .allowedHeaders("*") // Allow all headers
                    .allowCredentials(true); // Enable credentials (optional, for cookies or auth headers)
            }
        };
    }
}
