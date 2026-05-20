package com.pao_fresquim.api.DTOs;

import com.pao_fresquim.api.Enums.CargoFuncionario;

import java.time.LocalDate;

public class FuncionarioDTO {

    private Long id;

    private String nome;
    private String telefone;
    private String endereco;
    private CargoFuncionario cargo;
    private String contato_emergencia;
    private LocalDate data_admissao;
    private String licencas; // talvez sera necessário a retirada da parte de licenças


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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CargoFuncionario getCargo() {
        return cargo;
    }

    public void setCargo(CargoFuncionario cargo) {
        this.cargo = cargo;
    }

    public String getContato_emergencia() {
        return contato_emergencia;
    }

    public void setContato_emergencia(String contato_emergencia) {
        this.contato_emergencia = contato_emergencia;
    }

    public LocalDate getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(LocalDate data_admissao) {
        this.data_admissao = data_admissao;
    }

    public String getLicencas() {
        return licencas;
    }

    public void setLicencas(String licencas) {
        this.licencas = licencas;
    }
}
