package com.trivalia.trivalia.dto;

import com.trivalia.trivalia.entities.PreguntasEntity.Dificultad;
import com.trivalia.trivalia.entities.PreguntasEntity.TipoPregunta;

public class PreguntaDTO {

    private Long idPregunta;
    private Long idCategoria;
    private String pregunta;
    private String opcion_a;
    private String opcion_b;
    private String opcion_c;
    private String respuesta_correcta;
    private TipoPregunta tipo_pregunta;
    private Dificultad dificultad;
    private String imagenURL;

    public PreguntaDTO() {

    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion_a() {
        return opcion_a;
    }

    public void setOpcion_a(String opcion_a) {
        this.opcion_a = opcion_a;
    }

    public String getOpcion_b() {
        return opcion_b;
    }

    public void setOpcion_b(String opcion_b) {
        this.opcion_b = opcion_b;
    }

    public String getOpcion_c() {
        return opcion_c;
    }

    public void setOpcion_c(String opcion_c) {
        this.opcion_c = opcion_c;
    }

    public String getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public void setRespuesta_correcta(String respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public TipoPregunta getTipo_pregunta() {
        return tipo_pregunta;
    }

    public void setTipo_pregunta(TipoPregunta tipo_pregunta) {
        this.tipo_pregunta = tipo_pregunta;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

}
