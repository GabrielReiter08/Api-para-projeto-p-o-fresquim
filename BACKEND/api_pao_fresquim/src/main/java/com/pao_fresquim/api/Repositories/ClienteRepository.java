package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
