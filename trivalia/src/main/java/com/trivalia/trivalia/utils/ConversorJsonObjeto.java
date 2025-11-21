package com.trivalia.trivalia.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorJsonObjeto {
    public static <T> T convertirJSONenObjeto(String jsonString, Class<T> claseDestino) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T objetoT = mapper.readValue(jsonString, claseDestino);
            return objetoT;
        } catch (JsonProcessingException e) {
            System.err.println("Error de JSON Parsing: No se obtuvo el formato esperado.");
            e.printStackTrace();
            return null;
        }

    }
}
