package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;

import com.trivalia.trivalia.services.interfaces.ImgAPIKeyServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imagenes")
public class ImgAPIKeyController {
    private final ImgAPIKeyServiceInterface imgApiService;

    public ImgAPIKeyController(ImgAPIKeyServiceInterface imgApiService) {
        this.imgApiService = imgApiService;
    }

    @GetMapping("/img-api-key")
    public Map<String, Object> obtenerApiKey() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("api_key", this.imgApiService.obtenerImgApiKey());
        return respuesta;
    }

}
