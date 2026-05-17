package com.pao_fresquim.api.DTOs;

public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;

    private String endereco;

    private Boolean status_serasa;
    private Double saldo_devedor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getStatus_serasa() {
        return status_serasa;
    }

    public void setStatus_serasa(Boolean status_serasa) {
        this.status_serasa = status_serasa;
    }

    public Double getSaldo_devedor() {
        return saldo_devedor;
    }

    public void setSaldo_devedor(Double saldo_devedor) {
        this.saldo_devedor = saldo_devedor;
    }
}
