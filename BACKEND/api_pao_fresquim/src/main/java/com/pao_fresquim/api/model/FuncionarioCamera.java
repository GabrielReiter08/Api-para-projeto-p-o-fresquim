package com.pao_fresquim.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario_camera")
public class FuncionarioCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Camera camera;
}