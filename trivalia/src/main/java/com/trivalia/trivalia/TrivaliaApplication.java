package com.trivalia.trivalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.trivalia.trivalia.entities") // asegurar que Spring escanee las entidades
public class TrivaliaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrivaliaApplication.class, args);
    }
}
