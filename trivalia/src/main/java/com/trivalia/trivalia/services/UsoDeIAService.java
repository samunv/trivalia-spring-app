package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsoDeIAEntity;
import com.trivalia.trivalia.model.UsoDeIADTO;
import com.trivalia.trivalia.repositories.UsoDeIARepository;
import com.trivalia.trivalia.repositories.UsuarioRepository;
import com.trivalia.trivalia.services.interfaces.UsoDeIAServiceInterface;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UsoDeIAService implements UsoDeIAServiceInterface {

    private UsoDeIARepository usoDeIARepository;

    public UsoDeIAService(UsoDeIARepository usoDeIARepository) {
        this.usoDeIARepository = usoDeIARepository;
    }

    @Override
    public void obtenerOGuardarUsoDeIA(String uid) {
        UsoDeIAEntity usoDeIAEntity = this.obtenerUsoDeIA(uid);

        if (usoDeIAEntity == null) {
            usoDeIAEntity = new UsoDeIAEntity();
            usoDeIAEntity.setIdUsoDeIA(UUID.randomUUID().toString());
            usoDeIAEntity.setUidUsuario(uid);
            usoDeIAEntity.setUsosRestantes(5);
            usoDeIAEntity.setRenovacion(86400L);
        }

        this.usoDeIARepository.save(usoDeIAEntity);

    }

    @Override
    public void eliminarUsoDeIA(String uid) {
        this.usoDeIARepository.deleteByUidUsuario(uid);
    }


    private UsoDeIAEntity obtenerUsoDeIA(String uid) {
        UsoDeIAEntity usoDeIAEntity = this.usoDeIARepository.findByUidUsuario(uid).orElse(null);
        if(usoDeIAEntity == null) {
            return null;
        }
        return usoDeIAEntity;
    }



    @Override
    public boolean verificarUsosRestantes(String uid) {
        // Si es mayor que cero devuelve true
        UsoDeIAEntity usoDeIAEntity = this.obtenerUsoDeIA(uid);
        if(usoDeIAEntity.getUsosRestantes()>0){
            usoDeIAEntity.setUsosRestantes(usoDeIAEntity.getUsosRestantes()-1);
            this.usoDeIARepository.save(usoDeIAEntity);
            return true;
        }
        return false;
    }
}
