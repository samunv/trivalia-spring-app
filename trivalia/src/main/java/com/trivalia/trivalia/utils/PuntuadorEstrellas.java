package com.trivalia.trivalia.utils;

import com.trivalia.trivalia.entities.PreguntasEntity.Dificultad;

public class PuntuadorEstrellas {

    public static int calcularPuntuacionEstrellas(Dificultad dificultadNivel) {
        return switch (dificultadNivel) {
            case FACIL ->
               numeroRandom(5, 14);
            case MEDIO ->
                numeroRandom(15, 29);
            case DIFICIL ->
                numeroRandom(30, 45);
        };
    }

    public static int numeroRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

}
