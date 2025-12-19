package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.services.interfaces.RegaloServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioActualizarDatosServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.model.Regalo;

@Service
public class RegaloService implements RegaloServiceInterface {

    private static final Random random = new Random();
    private final UsuarioActualizarDatosServiceInterface usuarioActualizarDatosService;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;

    public RegaloService(UsuarioActualizarDatosServiceInterface usuarioActualizarDatosService,  UsuarioLecturaServiceInterface usuarioLecturaService) {
        this.usuarioActualizarDatosService = usuarioActualizarDatosService;
        this.usuarioLecturaService = usuarioLecturaService;
    }

    public Regalo obtenerRegalo(String uid) {
        if (this.verificarRegaloDisponible(uid) == false) {
            throw new IllegalStateException("El usuario no tiene un regalo disponible en este momento.");
        } else {
            Item itemAleatorio = this.generarItemAleatorio(uid);
            Integer cantidadAleatoria = this.generarCantidadAleatoriaDeItem(itemAleatorio);
            this.actualizarItemUsuario(itemAleatorio, cantidadAleatoria, uid, Operaciones.sumar);
            this.actualizarDisponibilidadRegaloUsuario(uid);
            return new Regalo(itemAleatorio, cantidadAleatoria);
        }

    }

    private Item generarItemAleatorio(String uid) {
        List<Item> itemsList = new ArrayList<Item>();
        // Excluir vidas si son iguales que el máximo posible (5)
        if(!this.usuarioLecturaService.esMaximoDeVidasUsuario(uid)) {
            itemsList.add(Item.vidas);
        }
        itemsList.add(Item.monedas);
        int indice = random.nextInt(itemsList.size());
        return itemsList.get(indice);
    }

    private Integer generarCantidadAleatoriaDeItem(Item item) {
        switch (item) {
            case monedas:
                return random.nextInt(100, 200);
            case vidas:
                return random.nextInt(1, 2);
            default:
                return 0;
        }
    }

    public boolean verificarRegaloDisponible(String uid) {
        UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
        return usuarioEntity.getRegaloDisponible();
    }


    public void actualizarItemUsuario(Item item, Integer cantidad, String uid, Operaciones operacion) {
        this.usuarioActualizarDatosService.actualizarItem(item, cantidad, uid, operacion);
    }

    public void actualizarDisponibilidadRegaloUsuario(String uid){
        this.usuarioActualizarDatosService.actualizarRegaloDisponible(uid, false);
    }

}
