package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.DTOs.ClienteDTO;
import com.pao_fresquim.api.DTOs.FuncionarioDTO;
import com.pao_fresquim.api.DTOs.ItemNotaFiscalDTO;
import com.pao_fresquim.api.DTOs.NotaFiscalDTO;
import com.pao_fresquim.api.Enums.FormaPagamento;
import com.pao_fresquim.api.Repositories.ClienteRepository;
import com.pao_fresquim.api.Repositories.VendaRepository;
import com.pao_fresquim.api.model.Cliente;

import com.pao_fresquim.api.model.Venda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository repository;

    private  ClienteRepository clienteRepository;


    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    // para retornar um relatório
    public List<Venda> ListarVendas(){
        return repository.findAll();
    }

    public Optional<Venda> encontrarVenda(Long id){
        return repository.findById(id);
    }




    public Venda finalizarVenda(Venda venda){

        if(venda.getFormaPagamento() == FormaPagamento.FIADO){

            Cliente cliente = venda.getCliente();

            Double saldoAtual = cliente.getSaldo_devedor() == null
                    ? 0.0
                    : cliente.getSaldo_devedor();

            cliente.setSaldo_devedor(
                    saldoAtual + venda.getValor_total()
            );

            cliente.setStatus_serasa(
                    cliente.getSaldo_devedor() > 0
            );

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
        dto.setDataVenda(venda.getData_venda());
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


}
