package com.pao_fresquim.api.Controllers;

import com.pao_fresquim.api.DTOs.VendaFiadoDTO;
import com.pao_fresquim.api.Services.FuncionarioService;
import com.pao_fresquim.api.Services.VendaService;
import com.pao_fresquim.api.model.Funcionario;
import com.pao_fresquim.api.model.Venda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final
    VendaService service;

    public VendaController(VendaService service){
        this.service = service;
    }

    @PostMapping
    public Venda cadastrarVenda(@RequestBody Venda venda){
        return service.finalizarVenda(venda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable Long id){
        return service.encontrarVenda(id)
                .map(ResponseEntity::ok) // ResponseEntity::ok irá criar uma resposta HTTP 200 (OK) contendo o objeto(creio que em JSON) no corpo desta resposta
                .orElse(ResponseEntity.notFound().build()); // não encontrou gerá uma resposta HTTP 404 (not  found).
    }

    // criar metodo listar vendas por cliente





    @GetMapping
    public List<Venda> ListaTodasAsVendas(){
        return service.ListarVendas();
    }



    @PutMapping("/{id}")
    public Venda atualizarVenda(@PathVariable Long id,
                                @RequestBody Venda venda){

        return service.atualizarVenda(id, venda);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVenda(@PathVariable Long id){

        service.deletarVenda(id);

        return ResponseEntity.ok("Venda deletada com sucesso");
    }


    // ver todas as vendas como fiado

    @GetMapping("/fiado")
    public ResponseEntity<List<VendaFiadoDTO>> listarFiados(){

        return ResponseEntity.ok(
                service.listarTodosFiados()
        );
    }


}
