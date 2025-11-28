package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.trivalia.trivalia.services.interfaces.RegaloServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivalia.trivalia.model.Regalo;

@RestController
@RequestMapping("/api/regalos")
public class RegaloController {

    private final RegaloServiceInterface regaloService;

    public RegaloController(RegaloServiceInterface regaloService) {
        this.regaloService = regaloService;
    }

    @GetMapping("/abrir/{uid}")
    public Regalo abrirRegalo(@PathVariable String uid) {
        try {
            return this.regaloService.obtenerRegalo(uid);
        } catch (IllegalStateException e) {
            System.err.println("Error al abrir el regalo: " + e.getMessage());
            return null;
        }

    }

}
