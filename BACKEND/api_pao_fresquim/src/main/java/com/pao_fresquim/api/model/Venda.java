package com.pao_fresquim.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pao_fresquim.api.Enums.FormaPagamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Vendas")
public class Venda {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("data_venda") // para deixar como está no bd
    private LocalDateTime dataVenda;
    private Double valor_total;

    //utilizar enum
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private Boolean nf_emitida;


    // relacionamentos:
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;



    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ItemVenda> itens = new ArrayList<>();


    public Venda(){}

    public Venda(LocalDateTime data_venda, Double valor_total, FormaPagamento formaPagamento, Boolean nf_emitida, Cliente cliente,
                 List<ItemVenda> itens) {
        this.dataVenda = data_venda;
        this.valor_total = valor_total;
        this.formaPagamento = formaPagamento;
        this.nf_emitida = nf_emitida;
        this.cliente = cliente;
        this.itens = itens;
    }

    // getters


    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
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


    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }


}
