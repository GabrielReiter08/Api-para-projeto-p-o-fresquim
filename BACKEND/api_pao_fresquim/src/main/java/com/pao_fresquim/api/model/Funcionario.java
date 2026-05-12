package com.pao_fresquim.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String endereco;
    private String contato_emergencia;
    private LocalDate data_admissao;
    private String licensas;

    //testar este (converte para byte o arquivo para salvar no bd)
    // vai precisar de um bd com bom armazenamento.
    @Lob
    private byte[] atestadoMedico;

    private String nomeArquivo;
    private String tipoArquivo;

    // ---- //


    //relacionamentos:
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Venda> vendas;

    // cria tabela intermedíaria para relação manyTomany
    @ManyToMany
    @JoinTable(
            name = "funcionario_camera",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "camera_id")
    )
    private List<Camera> cameras = new ArrayList<>();


    public Funcionario(){}

    public Funcionario(String nome, String telefone, String endereco, String contato_emergencia, LocalDate data_admissao, String licensas, byte[] atestadoMedico) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.contato_emergencia = contato_emergencia;
        this.data_admissao = data_admissao;
        this.licensas = licensas;
        this.atestadoMedico = atestadoMedico;
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

    public String getContato_emergencia() {
        return contato_emergencia;
    }

    public LocalDate getData_admissao() {
        return data_admissao;
    }

    public String getLicensas() {
        return licensas;
    }

    // a sofrer alterações
    public byte[] getAtestadoMedico() {
        return atestadoMedico;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
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

    public void setContato_emergencia(String contato_emergencia) {
        this.contato_emergencia = contato_emergencia;
    }

    public void setData_admissao(LocalDate data_admissao) {
        this.data_admissao = data_admissao;
    }

    public void setLicensas(String licensas) {
        this.licensas = licensas;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    // a sofrer alterações
    // desenvolver forma backend para retorno dos bytes do arquivo para acesso no front

    public void setAtestadoMedico(byte[] atestadoMedico) {
        this.atestadoMedico = atestadoMedico;
    }


    // vendas e cameras


    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }




}
