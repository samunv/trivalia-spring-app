package com.trivalia.trivalia.services;

import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.services.interfaces.*;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.model.PreguntaDTO;

//import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PreguntaIAService implements PreguntasIAServiceInterface {

    private final InteligenciaArtificialServiceInterface inteligenciaArtificialService;
    private final UsuarioActualizarDatosServiceInterface usuarioActualizarDatosService;
    private final CalculadorServiceInterface calculadorService;
    private final PreguntaMemoriaServiceInterface preguntaMemoriaService;


    public PreguntaIAService(InteligenciaArtificialServiceInterface inteligenciaArtificialService, UsuarioActualizarDatosServiceInterface usuarioActualizarDatosService, CalculadorServiceInterface calculadorService, PreguntaMemoriaServiceInterface preguntaMemoriaService) {
        this.inteligenciaArtificialService = inteligenciaArtificialService;
        this.usuarioActualizarDatosService = usuarioActualizarDatosService;
        this.calculadorService = calculadorService;
        this.preguntaMemoriaService = preguntaMemoriaService;
    }

    public PreguntaDTO generarYObtenerPreguntaIA() {
        PreguntaDTO preguntaDTO = this.inteligenciaArtificialService.obtenerRespuestaIA(this.obtenerPromptPregunta(), PreguntaDTO.class);
        this.preguntaMemoriaService.guardarPreguntaEnMemoria(preguntaDTO);
        return this.preguntaMemoriaService.obtenerPreguntaDeMemoria(preguntaDTO.getIdPregunta());
    }


    private void ganarPreguntaIA(String uid) {
        int monedasRecompensa = this.calculadorService.calcularRecompensaIASegunMonedasUsuario(uid);
        this.usuarioActualizarDatosService.actualizarItem(Item.monedas, monedasRecompensa, uid, Operaciones.sumar);
        this.usuarioActualizarDatosService.actualizarItem(Item.estrellas, 30, uid, Operaciones.sumar);
        this.usuarioActualizarDatosService.actualizarRegaloDisponible(uid, true);
    }

    @Override
    public boolean responderPreguntaIA(String uid, RespuestaUsuarioDTO respuestaUsuarioDTO) {
        PreguntaDTO dto = this.preguntaMemoriaService.obtenerPreguntaDeMemoria(respuestaUsuarioDTO.getIdPregunta());
        Boolean respuestaEsCorrecta = respuestaUsuarioDTO.getRespuestaSeleccionada().equalsIgnoreCase(dto.getRespuesta_correcta());

        if(respuestaEsCorrecta){
            this.ganarPreguntaIA(uid);
        }

        this.preguntaMemoriaService.eliminarPreguntaDeMemoria(respuestaUsuarioDTO.getIdPregunta());
        return respuestaEsCorrecta;
    }

    private String obtenerPromptPregunta() {
        return "Genera una pregunta de trivia totalmente aleatoria. La pregunta debe ser de dificultad 'MEDIO' (que se traduce en dificultad media), de cultura general, pero no puede ser tampoco muy sencilla ni fácil de acertar. \n"
                + "Puede ser de cualquier tema (Por ejemplo equipos de fútbol, música general, ciencia, naturaleza, etc.). Es necesario que el enunciado o pregunta no sea muy larga. Está prohibido que en las opciones se incluyan pistas. Debe estar en formato JSON con las siguientes claves: "
                + "{ \"idPregunta\":\"" + this.obtenerLongAleatorioParaID() + "\", \"pregunta\": \"\", \"opcion_a\": \"\", \"opcion_b\": \"\", \"opcion_c\": \"\", "
                + "\"respuesta_correcta\": \"\", \"tipo_pregunta\": \"OPCIONES\", \"dificultad\": \"MEDIO\", \"imagenURL\": \"\" } "
                + "Llena cada campo correctamente. Cada caracter de la respuesta correcta debe coincidir exactamente con los de una de las opciones (opcion_a, opcion_b o opcion_c). No se debe incluir ningun texto más en la respuesta, solamente el JSON exacto. No debe tener saltos de línea. Considera consultar información verídica para generar una pregunta correcta, cuya respuesta correcta sea verdadera y totalmente comprobada";
    }


    private Long obtenerLongAleatorioParaID() {
        return ThreadLocalRandom.current().nextLong(1000L, 5000000L) + System.currentTimeMillis();
    }


}