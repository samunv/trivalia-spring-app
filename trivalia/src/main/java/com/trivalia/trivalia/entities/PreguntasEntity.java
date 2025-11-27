package com.trivalia.trivalia.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "preguntas")
public class PreguntasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Long idPregunta;

    @Column(nullable = false)
    private String pregunta;

    @Column(nullable = false)
    private String imagenURL;

    @Column(nullable = true)
    private String opcion_a;

    @Column(nullable = true)
    private String opcion_b;

    @Column(nullable = true)
    private String opcion_c;

    @Column(nullable = false)
    private String respuesta_correcta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPregunta tipo_pregunta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificultad dificultad;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    @ManyToMany(mappedBy = "preguntasGanadas")
    private List<UsuarioEntity> usuariosQueGanaron; // Nuevo campo


    public PreguntasEntity() {
    }

    public PreguntasEntity(String pregunta, String imagenURL, String opcion_a, String opcion_b, String opcion_c, String respuesta_correcta, TipoPregunta tipo_pregunta, Dificultad dificultad) {
        this.pregunta = pregunta;
        this.imagenURL = imagenURL;
        this.opcion_a = opcion_a;
        this.opcion_b = opcion_b;
        this.opcion_c = opcion_c;
        this.respuesta_correcta = respuesta_correcta;
        this.tipo_pregunta = tipo_pregunta;
        this.dificultad = dificultad;

    }

    public PreguntasEntity(String pregunta, String imagenURL, String opcion_a, String opcion_b, String opcion_c, String respuesta_correcta, TipoPregunta tipo_pregunta, Dificultad dificultad, CategoriaEntity categoria) {
        this.pregunta = pregunta;
        this.imagenURL = imagenURL;
        this.opcion_a = opcion_a;
        this.opcion_b = opcion_b;
        this.opcion_c = opcion_c;
        this.respuesta_correcta = respuesta_correcta;
        this.tipo_pregunta = tipo_pregunta;
        this.dificultad = dificultad;
        this.categoria = categoria;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
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

    public List<UsuarioEntity> getUsuariosQueGanaron() {
        return usuariosQueGanaron;
    }

    public void setUsuariosQueGanaron(List<UsuarioEntity> usuariosQueGanaron) {
        this.usuariosQueGanaron = usuariosQueGanaron;
    }


    public enum TipoPregunta {
        OPCIONES,
        ESCRIBIR,
        VF
    }

    public enum Dificultad {
        FACIL,
        MEDIO,
        DIFICIL
    }


}
