package com.pao_fresquim.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pao_fresquim.api.Enums.CategoriaProduto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    private Long codigo_barras;


    // relacionamentos:

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemVenda> itensDaVenda;

    // construtor

    public Produto(){}

    public Produto(String nome, Double preco, CategoriaProduto categoria, Long codigo_barras) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.codigo_barras = codigo_barras;
    }

    // getters


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Long getCodigo_barras() {
        return codigo_barras;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    // setters



    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setCodigo_barras(Long codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
}
