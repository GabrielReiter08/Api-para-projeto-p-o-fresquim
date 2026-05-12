package com.pao_fresquim.api.DTOs;

public class RelatorioDTO {
    private Integer quantidadeVendas;
    private Double totalVendido;

    public RelatorioDTO(){}

    public RelatorioDTO(Integer quantidadeVendas, Double totalVendido) {
        this.quantidadeVendas = quantidadeVendas;
        this.totalVendido = totalVendido;
    }

    public Integer getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(Integer quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }
}
