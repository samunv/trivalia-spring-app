package com.trivalia.trivalia.model;

public class UsoDeIADTO {
    private String uidUsuario;
    private Integer usosRestantes;

    public UsoDeIADTO(){

    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }

    public Integer getUsosRestantes() {
        return usosRestantes;
    }

    public void setUsosRestantes(Integer usosRestantes) {
        this.usosRestantes = usosRestantes;
    }
}
