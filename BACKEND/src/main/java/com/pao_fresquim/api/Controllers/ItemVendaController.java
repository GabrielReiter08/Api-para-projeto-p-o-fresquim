package com.pao_fresquim.api.Controllers;

import com.pao_fresquim.api.Services.ItemVendaService;
import com.pao_fresquim.api.model.ItemVenda;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/itens-venda")
public class ItemVendaController {

    private final ItemVendaService service;

    public ItemVendaController(ItemVendaService service) {
        this.service = service;
    }


    @GetMapping
    public List<ItemVenda> listarTodos() {
        return service.listarTodos();
    }


    @GetMapping("/{id}")
    public ItemVenda buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // listar por venda
    @GetMapping("/venda/{vendaId}")
    public Optional<ItemVenda> listarPorVenda(@PathVariable Long vendaId) {
        return service.listarPorVenda(vendaId);
    }


    @PostMapping("/venda/{vendaId}")
    public ItemVenda criarItem(@PathVariable Long vendaId,
                               @RequestBody ItemVenda item) {
        return service.criarItem(vendaId, item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletarItem(id);
    }
}