package com.trivalia.trivalia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImgApiService {

    @Value("${img.bb.api.key}")
    private String apiKey;

    public String getImgApiKey() {
        return this.apiKey;
    }

}
