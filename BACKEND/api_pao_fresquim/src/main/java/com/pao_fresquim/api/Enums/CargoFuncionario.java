package com.pao_fresquim.api.Enums;

public enum CargoFuncionario {
    PADEIRO("Padeiro"),
    ATENDENTE("Atendente"),
    GERENTE("Gerente"),
    AUXILIAR("Auxiliar");

    private String descricao;

    CargoFuncionario (String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
