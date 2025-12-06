package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioDetallesAuthServicio implements UserDetailsService {

    private final UsuarioLecturaServiceInterface usuarioLecturaService;

    public UsuarioDetallesAuthServicio(UsuarioLecturaServiceInterface usuarioLecturaService) {
        this.usuarioLecturaService = usuarioLecturaService;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        UsuarioEntity usuarioEntity = usuarioLecturaService.obtenerUsuarioEntity(uid);
        if (usuarioEntity == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con UID: '" + uid + "'");
        }
        User user = new User(uid, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
    }
}