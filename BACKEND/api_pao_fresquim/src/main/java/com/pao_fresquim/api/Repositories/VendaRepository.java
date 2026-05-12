package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.ItemVenda;
import com.pao_fresquim.api.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
