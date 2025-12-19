package com.trivalia.trivalia.repositories;

import com.trivalia.trivalia.entities.UsoDeIAEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsoDeIARepository extends CrudRepository<UsoDeIAEntity, String> {
    public Optional<UsoDeIAEntity> findByUidUsuario(String uidUsuario);
    public void deleteByUidUsuario(String uid);
}
