package com.pao_fresquim.api.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class NotaFiscalDTO {

    private Long idVenda;
    private LocalDateTime dataVenda;
    private Double valorTotal;
    private String formaPagamento;
    private Boolean nfEmitida;

    private ClienteDTO cliente;
    private FuncionarioDTO funcionario;

    private List<ItemNotaFiscalDTO> itens;


    public Long getIdVenda() {
        return idVenda;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Boolean getNfEmitida() {
        return nfEmitida;
    }

    public void setNfEmitida(Boolean nfEmitida) {
        this.nfEmitida = nfEmitida;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemNotaFiscalDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemNotaFiscalDTO> itens) {
        this.itens = itens;
    }

    public void setIdVenda(Long id) {
        this.idVenda = id;
    }
}
