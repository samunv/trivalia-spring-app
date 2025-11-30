package com.trivalia.trivalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.trivalia.trivalia.entities") // asegurar que Spring escanee las entidades
@EnableScheduling
public class TrivaliaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrivaliaApplication.class, args);
    }
}
