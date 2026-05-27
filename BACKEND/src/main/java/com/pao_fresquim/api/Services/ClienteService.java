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


    // metodo editar cliente
    public Cliente editarCliente(Long id, Cliente cliente){
        Cliente clienteExiste = repository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));

        clienteExiste.setNome(cliente.getNome());
        clienteExiste.setTelefone(cliente.getTelefone());
        clienteExiste.setEndereco(cliente.getEndereco());

        return repository.save(clienteExiste);
    }


    // metodo que registra o pagamento de um saldo devedor

    public void registraPagamentoSaldoDevedor(
            Cliente cliente,
            Double valor
    ){

        if(cliente.getSaldo_devedor() - valor < 0){
            throw new RuntimeException(
                    "O valor torna o saldo devedor menor que 0!"
            );
        }

        cliente.setSaldo_devedor(
                cliente.getSaldo_devedor() - valor
        );

        if(cliente.getSaldo_devedor() <= 0){
            cliente.setStatus_serasa(false);
        }

        repository.save(cliente);
    }

}
