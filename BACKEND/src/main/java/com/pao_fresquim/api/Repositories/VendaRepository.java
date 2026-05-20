package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.ItemVenda;
import com.pao_fresquim.api.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByDataVendaBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );


}
