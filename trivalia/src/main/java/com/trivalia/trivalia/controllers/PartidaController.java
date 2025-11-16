package com.trivalia.trivalia.controllers;

import com.trivalia.trivalia.services.PartidaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/jugar/{uid}")
    public Map<String, Boolean> jugarPartida(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.jugarPartida(uid));
        return resultado;
    }

    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/ganar/{uid}")
    public Map<String, Boolean> ganar(@PathVariable String uid) {
        // TODO: Añadir en servicio, lógica para finalizar la partida de una categoria o x partida
        return Map.of();
    }

    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/continuar-con-monedas/{uid}")
    public Map<String, Boolean> continuarConMonedas(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.continuarConMonedas(uid, 100));
        return resultado;
    }

    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/jugar-ia/{uid}")
    public Map<String, Boolean> jugarIa(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.continuarConMonedas(uid, 300));
        return resultado;
    }
}
