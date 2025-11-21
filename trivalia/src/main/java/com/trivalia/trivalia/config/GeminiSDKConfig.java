package com.trivalia.trivalia.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiSDKConfig {

    private Client clienteGemini;
    @Value("${ia.api.key}")
    private String IA_API_KEY;

    public GeminiSDKConfig() {}

    @Bean
    public Client geminiClient() {
        if (this.IA_API_KEY == null || this.IA_API_KEY.isEmpty()) {
            throw new IllegalArgumentException("La clave 'ia.api.key' no está configurada o está vacía.");
        }
        System.out.println("Inicializando el cliente Gemini con la API Key...");

        return Client.builder().apiKey(IA_API_KEY).build();
    }

    @Bean
    public GenerateContentConfig obtenerContentConfig() {
        return GenerateContentConfig.builder().temperature(0.8f)
                .responseMimeType("application/json")
                .build();
    }

}
