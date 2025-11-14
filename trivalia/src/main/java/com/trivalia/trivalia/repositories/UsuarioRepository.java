package com.trivalia.trivalia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trivalia.trivalia.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    @Query("SELECT u FROM UsuarioEntity u LEFT JOIN FETCH u.preguntasGanadas WHERE u.uid = :uid")
    Optional<UsuarioEntity> findByIdWithPreguntas(@Param("uid") String uid);
}
