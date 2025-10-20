package com.trivalia.trivalia.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trivalia.trivalia.entities.PreguntasEntity;

@Repository
public interface PreguntasRepository extends JpaRepository<PreguntasEntity, Long> {

    @Query("SELECT p FROM PreguntasEntity p WHERE p.categoria.idCategoria = :idCategoria")
    List<PreguntasEntity> findPreguntasPorCategoria(@Param("idCategoria") Long idCategoria);

}
