package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.Funcionario;
import com.pao_fresquim.api.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
