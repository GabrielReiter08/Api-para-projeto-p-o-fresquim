package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.Repositories.VendaRepository;
import com.pao_fresquim.api.model.Cliente;
import com.pao_fresquim.api.model.Venda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository repository;


    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public List<Venda> ListarVendas(){
        return repository.findAll();
    }

    public Optional<Venda> encontrarVenda(Long id){
        return repository.findById(id);
    }

    public Venda finalizarVenda(Venda venda){
        return repository.save(venda);
    }

    // metodo gerar nota fiscal


}
