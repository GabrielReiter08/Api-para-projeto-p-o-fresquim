package com.pao_fresquim.api.Controllers;

import com.pao_fresquim.api.Services.FuncionarioService;
import com.pao_fresquim.api.Services.ProdutoService;
import com.pao_fresquim.api.model.Funcionario;
import com.pao_fresquim.api.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto){
        return service.cadastrarProduto(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok) // ResponseEntity::ok irá criar uma resposta HTTP 200 (OK) contendo o objeto(creio que em JSON) no corpo desta resposta
                .orElse(ResponseEntity.notFound().build()); // não encontrou gerá uma resposta HTTP 404 (not  found).
    }

    @GetMapping
    public List<Produto> listarProdutos(){
        return service.listarProdutos();
    }

    @DeleteMapping("{id}")
    public String excluirProdutoCadastrado(@PathVariable Long id){
        boolean removido = service.excluirProduto(id);

        return removido ? "Produto removido com sucesso ✅" : "Produto não encontrado! ⚠️";
    }


    // criar metodo de atualizar produto venda


}
