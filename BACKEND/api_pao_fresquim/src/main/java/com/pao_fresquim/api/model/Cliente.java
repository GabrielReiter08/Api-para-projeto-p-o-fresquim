package com.pao_fresquim.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;

    private String endereco;

    private Boolean status_serasa;
    private Double saldo_devedor;

    // teste

    //relacionamentos:
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Venda> vendas;


    public Cliente(){}

    public Cliente(String nome, String telefone, String endereco, Boolean status_serasa, Double saldo_devedor) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.status_serasa = status_serasa;
        this.saldo_devedor = saldo_devedor;
    }




    // getters


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }



    public String getEndereco() {
        return endereco;
    }

    public boolean isStatus_serasa() {
        return status_serasa;
    }

    public Boolean getStatus_serasa() {
        return status_serasa;
    }

    public Double getSaldo_devedor() {
        return saldo_devedor;
    }

    // get e set das vendas

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    // setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setStatus_serasa(Boolean status_serasa) {
        this.status_serasa = status_serasa;
    }

    public void setSaldo_devedor(Double saldo_devedor) {
        this.saldo_devedor = saldo_devedor;
    }
}
