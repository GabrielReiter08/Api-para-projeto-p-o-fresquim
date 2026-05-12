package com.pao_fresquim.api.Enums;

public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO("Cartão"),
    PIX("Pix");

    private String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}