package br.com.alexpratesdev.backendmymoney.controller;

import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.service.GerenciamentoGastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/lista")
public class GerenciamentoGastosController {

    @Autowired
    GerenciamentoGastosService gerenciamentoGastosService;

    @GetMapping
    public List<GastoDTO> listarGastosUsuario() throws Exception {
        return gerenciamentoGastosService.buscarGastoPorUsuario();
    }

    @GetMapping("/soma")
    public BigDecimal somarGastos() throws Exception {
        return gerenciamentoGastosService.somarGastos();
    }
}
