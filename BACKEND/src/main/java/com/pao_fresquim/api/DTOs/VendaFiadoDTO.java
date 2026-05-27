package com.pao_fresquim.api.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class VendaFiadoDTO {

    private String nomeCliente;
    private Double valor;
    private LocalDateTime data;
    private List<String> produtos;

    public VendaFiadoDTO(
            String nomeCliente,
            Double valor,
            LocalDateTime data,
            List<String> produtos
    ) {
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.data = data;
        this.produtos = produtos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<String> getProdutos() {
        return produtos;
    }
}
