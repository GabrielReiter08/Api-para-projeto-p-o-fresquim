package com.pao_fresquim.api.Controllers;


import com.pao_fresquim.api.DTOs.RelatorioDTO;
import com.pao_fresquim.api.Services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    // relatório diário:

    @GetMapping("/dia")
    public RelatorioDTO relatorioDoDia(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date){
        return  service.relatorioDiario(date);
    }

    // mensal

    @GetMapping("/mes")
    public RelatorioDTO relatorioDoMes(@RequestParam int ano, @RequestParam int mes){
        return service.relatorioMensal(ano, mes);
    }

    // anual

    @GetMapping("/ano")
    public RelatorioDTO relatorioDoAno(@RequestParam int ano){
        return service.relatorioAnual(ano);
    }

}
