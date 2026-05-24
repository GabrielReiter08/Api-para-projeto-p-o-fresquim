package com.pao_fresquim.api.DTOs;

public class RelatorioDTO {
    private Integer quantidadeVendas;
    private Double totalVendido;
    
    // add ticket médio
    private Double ticketMedio;

    public RelatorioDTO(){}

    public RelatorioDTO(Integer quantidadeVendas, Double totalVendido, Double ticketMedio) {
        this.quantidadeVendas = quantidadeVendas;
        this.totalVendido = totalVendido;
        this.ticketMedio = ticketMedio;
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

    public Double getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(Double ticketMedio) {
        this.ticketMedio = ticketMedio;
    }
}
