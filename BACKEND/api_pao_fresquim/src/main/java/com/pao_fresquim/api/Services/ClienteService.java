package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.Repositories.ClienteRepository;
import com.pao_fresquim.api.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public List<Cliente> listarClientes(){
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return repository.findById(id);
    }

    // cadastrar cliente (base)

    public Cliente cadastrarCliente( Cliente cliente){
        return repository.save(cliente);
    }

    // metodo consultar serasa



    public boolean excluirCliente(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }

        return false;
    }

}
