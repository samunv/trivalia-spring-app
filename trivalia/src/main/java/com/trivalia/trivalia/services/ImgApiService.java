package com.trivalia.trivalia.services;

import org.springframework.stereotype.Service;

@Service
public class ImgApiService {

    private final String apiKey = "073e2140389f28522575e128cc60ab5b";

    public String getImgApiKey() {
        return this.apiKey;
    }

}
