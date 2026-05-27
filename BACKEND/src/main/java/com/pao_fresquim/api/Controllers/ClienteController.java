package com.pao_fresquim.api.Controllers;

import com.pao_fresquim.api.DTOs.VendaFiadoDTO;
import com.pao_fresquim.api.Services.ClienteService;
import com.pao_fresquim.api.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController (ClienteService service){
        this.service = service;
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente){
        return service.cadastrarCliente(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok) // ResponseEntity::ok irá criar uma resposta HTTP 200 (OK) contendo o objeto(creio que em JSON) no corpo desta resposta
                .orElse(ResponseEntity.notFound().build()); // não encontrou gerá uma resposta HTTP 404 (not  found).
    }

    @GetMapping
    public List<Cliente> listarClientes(){
        return service.listarClientes();
    }

    @DeleteMapping("{id}")
    public String excluir(@PathVariable Long id){
        boolean removido = service.excluirCliente(id);

        return removido ? "Cliente removido com sucesso ✅" : "Cliente não encontrado! ⚠️";
    }

    // Ver fiado do cliente

    @GetMapping("/{id}/fiado")
    public ResponseEntity<List<VendaFiadoDTO>> verVendasFiado(
            @PathVariable Long id
    ){

        return service.buscarPorId(id)
                .map(cliente ->
                        ResponseEntity.ok(
                                service.verFiado(cliente)
                        )
                )
                .orElse(ResponseEntity.notFound().build());
    }





    // registrar pagamento

    @GetMapping("/pagamento/{id}/{valor}")
    public ResponseEntity<Cliente> registrarPagamento(@PathVariable Long id, @PathVariable Double valor){
        return service.buscarPorId(id)
                .map(cliente -> {
                    service.registraPagamentoSaldoDevedor(cliente, valor);

                    return ResponseEntity.ok(cliente);
                }) .orElse(ResponseEntity.notFound().build());
    }

    // adicionando edição de cliente

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(
            @PathVariable Long id,
            @RequestBody Cliente cliente
    ){

        return ResponseEntity.ok(
                service.editarCliente(id, cliente)
        );
    }



}
