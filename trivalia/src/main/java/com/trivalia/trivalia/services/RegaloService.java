package com.trivalia.trivalia.services;

import java.util.Random;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioServiceInterface;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.model.Regalo;

@Service
public class RegaloService {

    private static final Random random = new Random();
    private final UsuarioServiceInterface usuarioService;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;

    public RegaloService(UsuarioServiceInterface usuarioService,  UsuarioLecturaServiceInterface usuarioLecturaService) {
        this.usuarioService = usuarioService;
        this.usuarioLecturaService = usuarioLecturaService;
    }

    public Regalo obtenerRegalo(String uid) {
        if (this.verificarRegaloDisponible(uid) == false) {
            throw new IllegalStateException("El usuario no tiene un regalo disponible en este momento.");
        } else {
            Item itemAleatorio = this.generarItemAleatorio();
            Integer cantidadAleatoria = this.generarCantidadAleatoriaDeItem(itemAleatorio);
            this.actualizarItemUsuario(itemAleatorio, cantidadAleatoria, uid, Operaciones.sumar);
            this.actualizarDisponibilidadRegaloUsuario(uid);
            return new Regalo(itemAleatorio, cantidadAleatoria);
        }

    }

    private Item generarItemAleatorio() {
        Item[] items = Item.values();
        int indice = random.nextInt(items.length);
        return items[indice];
    }

    private Integer generarCantidadAleatoriaDeItem(Item item) {
        switch (item) {
            case monedas:
                return random.nextInt(100, 350);
            case vidas:
                return random.nextInt(1, 3);
            case estrellas:
                return random.nextInt(15, 30);
            default:
                return 0;
        }
    }

    public boolean verificarRegaloDisponible(String uid) {
        UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
        return usuarioEntity.getRegaloDisponible();
    }


    public void actualizarItemUsuario(Item item, Integer cantidad, String uid, Operaciones operacion) {
        this.usuarioService.actualizarItem(item, cantidad, uid, operacion);
    }

    public void actualizarDisponibilidadRegaloUsuario(String uid){
        this.usuarioService.actualizarRegaloDisponible(uid, false);
    }

//    public UsuarioDTO obtenerUsuarioDTO(String uid) {
//        return this.usuarioService.obtenerUsuario(uid);
//    }

}
