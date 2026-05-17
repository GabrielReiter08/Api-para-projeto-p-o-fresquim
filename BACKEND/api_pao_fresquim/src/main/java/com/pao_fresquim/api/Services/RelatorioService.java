package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.DTOs.RelatorioDTO;
import com.pao_fresquim.api.Repositories.VendaRepository;
import com.pao_fresquim.api.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RelatorioService {

    @Autowired
    private VendaRepository repository;

    private RelatorioDTO gerarRelatorio(List<Venda> vendas){
        Double total = 0.0;

        for(Venda venda : vendas){
            total += venda.getValor_total();
        }

        return new RelatorioDTO(
                vendas.size(),
                total
        );
    }

    // relatório diário

    public RelatorioDTO relatorioDiario(LocalDate data){
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23,59,59);

        List<Venda> vendas = repository.findByData_vendaBetween(inicio, fim);

        return gerarRelatorio(vendas);

    }


    // mensal

    public RelatorioDTO relatorioMensal(int ano, int mes){

        LocalDateTime inicio = LocalDate.of(ano, mes, 1).atStartOfDay();

        LocalDateTime fim = LocalDate.of(
                ano,
                mes,
                LocalDate.of(ano, mes, 1).lengthOfMonth()
        ).atTime(23,59,59);

        List<Venda> vendas = repository.findByData_vendaBetween(inicio, fim);

        return gerarRelatorio(vendas);
    }

    // anual

    public RelatorioDTO relatorioAnual(int ano){

        LocalDateTime inicio = LocalDate.of(ano,1,1).atStartOfDay();

        LocalDateTime fim = LocalDate.of(ano,12,31)
                .atTime(23,59,59);

        List<Venda> vendas = repository.findByData_vendaBetween(inicio, fim);

        return gerarRelatorio(vendas);
    }



}
