package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trivalia.trivalia.services.ImgApiService;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenesController {

    private final ImgApiService imgApiService;

    public ImagenesController(ImgApiService imgApiService) {
        this.imgApiService = imgApiService;
    }

    @GetMapping("/img-api-key")
    public Map<String, Object> obtenerApiKey() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("api_key", this.imgApiService.getImgApiKey());
        return respuesta;
    }

}
