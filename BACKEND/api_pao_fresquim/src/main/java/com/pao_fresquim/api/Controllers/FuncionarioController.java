package com.pao_fresquim.api.Controllers;

import com.pao_fresquim.api.Services.ClienteService;
import com.pao_fresquim.api.Services.FuncionarioService;
import com.pao_fresquim.api.model.Cliente;
import com.pao_fresquim.api.model.Funcionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service){
        this.service = service;
    }

    @PostMapping
    public Funcionario cadastrarCliente(@RequestBody Funcionario funcionario){
        return service.cadastrarFuncionario(funcionario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok) // ResponseEntity::ok irá criar uma resposta HTTP 200 (OK) contendo o objeto(creio que em JSON) no corpo desta resposta
                .orElse(ResponseEntity.notFound().build()); // não encontrou gerá uma resposta HTTP 404 (not  found).
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return service.listarFuncionarios();
    }

    @DeleteMapping("{id}")
    public String excluir(@PathVariable Long id){
        boolean removido = service.excluirFuncionario(id);

        return removido ? "Funcionário removido com sucesso ✅" : "Funcionário não encontrado! ⚠️";
    }


}
