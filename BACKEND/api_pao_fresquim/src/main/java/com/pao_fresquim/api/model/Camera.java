package com.pao_fresquim.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localizacao;

    @ManyToMany(mappedBy = "cameras")
    @JsonIgnore
    private List<Funcionario> funcionarios = new ArrayList<>();

   /* @ManyToMany
    @JoinTable(
            name = "funcionario_camera",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "camera_id")
    )
    private List<Camera> cameras; */



    public Camera() {}



    // getters e setters


    public Long getId() {
        return id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    // funcionários q tem acesso a câmera
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}