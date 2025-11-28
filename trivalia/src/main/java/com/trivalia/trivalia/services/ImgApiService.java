package com.trivalia.trivalia.services;

import com.trivalia.trivalia.services.interfaces.ImgAPIKeyServiceInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImgApiService implements ImgAPIKeyServiceInterface {

    @Value("${img.bb.api.key}")
    private String apiKey;

    public String obtenerImgApiKey() {
        return this.apiKey;
    }

}
