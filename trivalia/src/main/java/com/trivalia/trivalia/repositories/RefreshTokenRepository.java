package com.trivalia.trivalia.repositories;

import com.trivalia.trivalia.entities.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByUidUsuario(String uidUsuario);
    void deleteByUidUsuario(String uidUsuario);
}
