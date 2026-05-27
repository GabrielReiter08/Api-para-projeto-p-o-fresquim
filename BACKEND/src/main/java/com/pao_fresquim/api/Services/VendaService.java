package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.DTOs.*;
import com.pao_fresquim.api.Enums.FormaPagamento;
import com.pao_fresquim.api.Repositories.ClienteRepository;
import com.pao_fresquim.api.Repositories.FuncionarioRepository;
import com.pao_fresquim.api.Repositories.ProdutoRepository;
import com.pao_fresquim.api.Repositories.VendaRepository;
import com.pao_fresquim.api.model.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository repository;

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    private final FuncionarioRepository funcionarioRepository;


    public VendaService(VendaRepository repository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    // para retornar um relatório
    public List<Venda> ListarVendas(){
        return repository.findAll();
    }

    public Optional<Venda> encontrarVenda(Long id){
        return repository.findById(id);
    }


    public Venda finalizarVenda(Venda venda){


        Cliente cliente = clienteRepository.findById(
                venda.getCliente().getId()
        ).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        Funcionario funcionario = funcionarioRepository.findById(
                venda.getFuncionario().getId()
        ).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);

        double total = 0.0;


        for(ItemVenda item : venda.getItens()){

            Produto produto = produtoRepository.findById(
                    item.getProduto().getId()
            ).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            item.setProduto(produto);


            item.setVenda(venda);


            Double precoUnitario = produto.getPreco();

            item.setPreco_unitario(precoUnitario);

            // calcula subtotal
            Double subtotal = precoUnitario * item.getQuantidade();

            item.setSubtotal(subtotal);

            total += subtotal;
        }


        venda.setValor_total(total);


        if(venda.getFormaPagamento() == FormaPagamento.FIADO){

            Double saldoAtual = cliente.getSaldo_devedor() == null
                    ? 0.0
                    : cliente.getSaldo_devedor();

            Double novoSaldo = saldoAtual + total;

            cliente.setSaldo_devedor(novoSaldo);


            cliente.setStatus_serasa(novoSaldo > 0);

            clienteRepository.save(cliente);
        }

        return repository.save(venda);
    }





    // metodo gerar nota fiscal (Sujeito a correções, testar primeiro)

    public NotaFiscalDTO gerarNotaFiscal(Long vendaId){

        Venda venda = repository.findById(vendaId)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        NotaFiscalDTO dto = new NotaFiscalDTO();

        dto.setIdVenda(venda.getId());
        dto.setDataVenda(venda.getDataVenda());
        dto.setValorTotal(venda.getValor_total());
        dto.setFormaPagamento(venda.getFormaPagamento().name());
        dto.setNfEmitida(venda.getNf_emitida());

        // cliente
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(venda.getCliente().getId());
        clienteDTO.setNome(venda.getCliente().getNome());
        clienteDTO.setTelefone(venda.getCliente().getTelefone());
        clienteDTO.setSaldo_devedor(
                venda.getCliente().getSaldo_devedor()
        );

        dto.setCliente(clienteDTO);

        // funcionário
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        funcionarioDTO.setId(venda.getFuncionario().getId());
        funcionarioDTO.setNome(venda.getFuncionario().getNome());

        dto.setFuncionario(funcionarioDTO);

        // itens
        List<ItemNotaFiscalDTO> itensDTO = venda.getItens()
                .stream()
                .map(item -> {

                    ItemNotaFiscalDTO itemDTO =
                            new ItemNotaFiscalDTO();

                    itemDTO.setProduto(
                            item.getProduto().getNome()
                    );

                    itemDTO.setQuantidade(
                            item.getQuantidade()
                    );

                    itemDTO.setValorUnitario(
                            item.getPreco_unitario()
                    );

                    itemDTO.setSubtotal(
                            item.getSubtotal()
                    );

                    return itemDTO;

                }).toList();

        dto.setItens(itensDTO);

        return dto;
    }


    // metodo p atualizar venda

    public Venda atualizarVenda(Long id, Venda vendaAtualizada){

        Venda vendaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));


        vendaExistente.setDataVenda(vendaAtualizada.getDataVenda());
        vendaExistente.setFormaPagamento(vendaAtualizada.getFormaPagamento());
        vendaExistente.setNf_emitida(vendaAtualizada.getNf_emitida());


        Cliente cliente = clienteRepository.findById(
                vendaAtualizada.getCliente().getId()
        ).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        vendaExistente.setCliente(cliente);


        Funcionario funcionario = funcionarioRepository.findById(
                vendaAtualizada.getFuncionario().getId()
        ).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        vendaExistente.setFuncionario(funcionario);


        vendaExistente.getItens().clear();

        double total = 0.0;


        for(ItemVenda item : vendaAtualizada.getItens()){

            Produto produto = produtoRepository.findById(
                    item.getProduto().getId()
            ).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            item.setProduto(produto);

            item.setVenda(vendaExistente);

            Double preco = produto.getPreco();

            item.setPreco_unitario(preco);

            Double subtotal = preco * item.getQuantidade();

            item.setSubtotal(subtotal);

            total += subtotal;

            vendaExistente.getItens().add(item);
        }

        vendaExistente.setValor_total(total);

        return repository.save(vendaExistente);
    }


    // deletar venda

    public void deletarVenda(Long id){

        Venda venda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));


        if(venda.getFormaPagamento() == FormaPagamento.FIADO){

            Cliente cliente = venda.getCliente();

            Double saldoAtual = cliente.getSaldo_devedor() == null
                    ? 0.0
                    : cliente.getSaldo_devedor();

            Double novoSaldo = saldoAtual - venda.getValor_total();


            if(novoSaldo < 0){
                novoSaldo = 0.0;
            }

            cliente.setSaldo_devedor(novoSaldo);

            cliente.setStatus_serasa(novoSaldo > 0);

            clienteRepository.save(cliente);
        }

        repository.delete(venda);
    }


    // retornar vendas fiado service

    public List<VendaFiadoDTO> listarTodosFiados(){

        return repository.findAll()
                .stream()

                .filter(venda ->
                        venda.getFormaPagamento()
                                == FormaPagamento.FIADO
                )

                .map(venda -> {

                    List<String> nomesProdutos =
                            venda.getItens()
                                    .stream()
                                    .map(item ->
                                            item.getProduto().getNome()
                                    )
                                    .toList();

                    return new VendaFiadoDTO(

                            venda.getCliente().getNome(),

                            venda.getValor_total(),

                            venda.getDataVenda(),

                            nomesProdutos
                    );

                })

                .toList();
    }


}
