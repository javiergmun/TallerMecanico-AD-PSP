package com.taller2dam.taller;

import com.taller2dam.taller.upload.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableJpaAuditing //Para las auditorías: fechas automáticas...
public class TallerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TallerApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(StorageService storageService) {
        return args -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }

}