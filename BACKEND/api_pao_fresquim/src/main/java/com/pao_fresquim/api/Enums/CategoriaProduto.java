package com.pao_fresquim.api.Enums;

public enum CategoriaProduto {
    PADARIA("Padaria"),
    SALGADOS("Salgados"),
    DOCES("Doces"),
    BEBIDAS("Bebidas");

    private String descricao;

    CategoriaProduto(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
