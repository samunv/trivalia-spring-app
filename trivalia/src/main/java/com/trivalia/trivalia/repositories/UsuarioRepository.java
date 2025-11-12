package com.trivalia.trivalia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trivalia.trivalia.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

}
