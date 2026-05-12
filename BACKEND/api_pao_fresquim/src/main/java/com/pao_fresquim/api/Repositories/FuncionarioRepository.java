package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.Funcionario;
import com.pao_fresquim.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
