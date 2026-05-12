package com.pao_fresquim.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vendas")
public class Venda {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data_venda;
    private Double valor_total;

    //utilizar enum
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private Boolean nf_emitida;


    // relacionamentos:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;



    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemVenda> itens = new ArrayList<>();


    public Venda(){}

    public Venda(LocalDateTime data_venda, Double valor_total, FormaPagamento formaPagamento, Boolean nf_emitida, Cliente cliente, List<ItemVenda> itens) {
        this.data_venda = data_venda;
        this.valor_total = valor_total;
        this.formaPagamento = formaPagamento;
        this.nf_emitida = nf_emitida;
        this.cliente = cliente;
        this.itens = itens;
    }

    // getters


    public Long getId() {
        return id;
    }

    public LocalDateTime getData_venda() {
        return data_venda;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Boolean getNf_emitida() {
        return nf_emitida;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    // setters


    public void setData_venda(LocalDateTime data_venda) {
        this.data_venda = data_venda;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setNf_emitida(Boolean nf_emitida) {
        this.nf_emitida = nf_emitida;
    }
}
