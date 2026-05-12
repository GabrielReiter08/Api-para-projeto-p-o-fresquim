package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.Cliente;
import com.pao_fresquim.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
