package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.model.UsoDeIADTO;
import com.trivalia.trivalia.services.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PartidaService implements PartidaServiceInterface {

    private final UsuarioActualizarDatosServiceInterface usuarioActualizarDatosService;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;
    private final PreguntaServiceInterface preguntasService;
    private final UsuarioPreguntaServiceInterface usuarioPreguntaService;
    private final ContadorServiceInterface contadorService;
    private final CalculadorServiceInterface calculadorService;
    private UsoDeIAServiceInterface usoDeIAService;

    public PartidaService(UsuarioActualizarDatosServiceInterface usuarioActualizarDatosService,
                          UsuarioLecturaServiceInterface usuarioLecturaService,
                          PreguntaServiceInterface preguntasService,
                          UsuarioPreguntaServiceInterface usuarioPreguntaService,
                          ContadorServiceInterface contadorService,
                          CalculadorServiceInterface calculadorService,
                          UsoDeIAServiceInterface usoDeIAService
    ) {
        this.usuarioActualizarDatosService = usuarioActualizarDatosService;
        this.usuarioLecturaService = usuarioLecturaService;
        this.preguntasService = preguntasService;
        this.usuarioPreguntaService = usuarioPreguntaService;
        this.contadorService = contadorService;
        this.calculadorService = calculadorService;
        this.usoDeIAService = usoDeIAService;
    }

    public boolean jugarPartida(String uid) {
        return this.comprobarVidasUsuario(uid);
    }


    private boolean comprobarVidasUsuario(String uid) {
        com.trivalia.trivalia.entities.UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
        if (usuarioEntity.getVidas() >= 1) {
            return true;
        }
        return false;
    }

    public boolean jugarIA(String uid) {
        this.usoDeIAService.obtenerOGuardarUsoDeIA(uid);
        if (this.usoDeIAService.verificarUsosRestantes(uid)) {
            int monedasRequeridas = this.calculadorService.calcularCostoIASegunMonedasUsuario(uid);
            return this.usuarioActualizarDatosService.descontarMonedas(uid, monedasRequeridas);

        }
        return false;
    }


    public boolean ganarPartida(String uid, PreguntaDTO preguntaDTO) {
        if (this.preguntasService.esUltimaPreguntaDeCategoria(preguntaDTO.getIdPregunta())) {
            UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
            this.usuarioActualizarDatosService.anadirPartidaGanada(usuarioEntity);
            this.usuarioActualizarDatosService.actualizarRegaloDisponible(uid, true);
            this.usuarioActualizarDatosService.actualizarItem(Item.monedas, 100, uid, Operaciones.sumar);
            this.controlarContador("detener");
            return true;
        }
        return false;

    }


    public PreguntaDTO obtenerPrimeraPregunta(Long idCategoria) {
        this.controlarContador("iniciar");
        return this.preguntasService.obtenerPrimeraPregunta(idCategoria);
    }

    public ResultadoPreguntaRespondidaDTO responderPregunta(String uid, RespuestaUsuarioDTO respuestaUsuario) {
        if (this.contadorService.verificarContadorMayorQueCero()) {
            this.controlarContador("detener");
            return this.usuarioPreguntaService.responderPregunta(uid, respuestaUsuario);
        } else {
            this.usuarioActualizarDatosService.actualizarItem(Item.vidas
                    , 1, uid, Operaciones.restar);
        }
        return null;
    }

    @Override
    public boolean perderPorTiempo(String uid) {
        if (!this.contadorService.verificarContadorMayorQueCero()) {
            return this.usuarioActualizarDatosService.actualizarItem(Item.vidas, 1, uid, Operaciones.restar);
        } else {
            System.err.print("Tiempo mayor que cero");
            return false;
        }

    }

    public void controlarContador(String accion) {
        if (accion.equalsIgnoreCase("iniciar")) {
            this.contadorService.iniciarContador();
        } else if (accion.equalsIgnoreCase("detener")) {
            this.contadorService.detenerContador();
        } else {
            throw new UnsupportedOperationException("Operacion no encontrada.");
        }

    }

    public void reintentarPartida() {
        this.controlarContador("iniciar");
    }


}
