package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.Repositories.ItemVendaRepository;
import com.pao_fresquim.api.model.ItemVenda;
import com.pao_fresquim.api.model.Venda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    private final ItemVendaRepository repository;

    public ItemVendaService (ItemVendaRepository repository){
        this.repository = repository;
    }

    public List<ItemVenda> listarItens(){
        return repository.findAll();
    }


        public List<ItemVenda> listarTodos() {
            return repository.findAll();
        }


        public ItemVenda buscarPorId(Long id) {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        }

        // Buscar itens de uma venda
        public Optional<ItemVenda> listarPorVenda(Long vendaId) {
            return repository.findById(vendaId);
        }

        // Criar item vinculado a uma venda
        public ItemVenda criarItem(Long vendaId, ItemVenda item) {
            Venda venda = repository.findById(vendaId)
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada")).getVenda();

            item.setVenda(venda);
            return repository.save(item);
        }


        public void deletarItem(Long id) {
            ItemVenda item = buscarPorId(id);
            repository.delete(item);
        }
    }

