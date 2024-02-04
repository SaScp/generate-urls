package ru.alex.generateurls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GenerateUrlsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateUrlsApplication.class, args);
    }
}
