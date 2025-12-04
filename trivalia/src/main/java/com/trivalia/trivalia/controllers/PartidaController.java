package com.trivalia.trivalia.controllers;

import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.services.interfaces.PartidaServiceInterface;
import com.trivalia.trivalia.services.interfaces.PreguntasIAServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {

    private final PartidaServiceInterface partidaService;
    private final PreguntasIAServiceInterface preguntasIAService;

    public PartidaController(PartidaServiceInterface partidaService, PreguntasIAServiceInterface preguntasIAService) {
        this.partidaService = partidaService;
        this.preguntasIAService = preguntasIAService;
    }

    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/jugar/{uid}")
    public Map<String, Boolean> jugarPartida(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.jugarPartida(uid));
        return resultado;
    }

    @GetMapping("obtener-primera/{idCategoria}")
    public PreguntaDTO obtenerPrimeraPregunta(@PathVariable Long idCategoria) {
        return this.partidaService.obtenerPrimeraPregunta(idCategoria);
    }

    @PreAuthorize("#uid == authentication.name")
    @PostMapping("/ganar/{uid}")
    public Map<String, Boolean> ganar(@PathVariable String uid, @RequestBody PreguntaDTO preguntaDTO) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.ganarPartida(uid, preguntaDTO));
        return resultado;
    }

//    @PreAuthorize("#uid == authentication.name")
//    @GetMapping("/continuar-con-monedas/{uid}")
//    public Map<String, Boolean> continuarConMonedas(@PathVariable String uid) {
//        Map<String, Boolean> resultado = new HashMap<>();
//        resultado.put("resultado", this.partidaService.continuarConMonedas(uid));
//        return resultado;
//    }


    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/jugar-ia/{uid}")
    public Map<String, Boolean> jugarIa(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.continuarConMonedas(uid));
        return resultado;
    }

    @PreAuthorize("#uid == authentication.name")
    @PostMapping("/responder-pregunta/{uid}")
    public ResultadoPreguntaRespondidaDTO responderPregunta(@PathVariable String uid, @RequestBody RespuestaUsuarioDTO respuestaUsuario) {
        return this.partidaService.responderPregunta(uid, respuestaUsuario);
    }

//    @PreAuthorize("#uid == authentication.name")
//    @PostMapping("/ganar-ia/{uid}")
//    public Map<String, Boolean> ganarIa(@PathVariable String uid) {
//        Map<String, Boolean> resultado = new HashMap<>();
//        resultado.put("resultado", this.preguntasIAService.ganarPreguntaIA(uid));
//        return resultado;
//    }

    @PreAuthorize("#uid == authentication.name")
    @PostMapping("/perder-por-tiempo/{uid}")
    public Map<String, Boolean> perderPorTiempo(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.partidaService.perderPorTiempo(uid));
        return resultado;
    }

    @PreAuthorize("#uid == authentication.name")
    @PostMapping("/reintentar-partida/{uid}")
    public Map<String, Boolean> reintentarPartida(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        this.partidaService.reintentarPartida();
        resultado.put("resultado", true);
        return resultado;
    }

    @PreAuthorize("#uid == authentication.name")
    @PostMapping("/responder-ia/{uid}")
    public Map<String, Boolean> reintentarPartida(@PathVariable String uid, @RequestBody RespuestaUsuarioDTO respuestaUsuario) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.preguntasIAService.responderPreguntaIA(uid, respuestaUsuario));
        return resultado;
    }
}
