package com.miprojecto.esp32.api.conexionesp32.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://antonyospino.github.io", "192.168.1.10") // Cambia esto a tu URL de producción
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite métodos específicos
                .allowedHeaders("Authorization", "Content-Type") // Permite el header Authorization y Content-Type
                .exposedHeaders("Authorization"); // Expone el header Authorization en la respuesta
    }
}

