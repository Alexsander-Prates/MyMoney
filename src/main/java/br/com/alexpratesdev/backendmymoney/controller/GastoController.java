package br.com.alexpratesdev.backendmymoney.controller;


import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.service.GastoService;
import br.com.alexpratesdev.backendmymoney.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gasto")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @GetMapping
    public List<GastoDTO> listarGastos() throws Exception {
        return gastoService.listarTodosGastos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoDTO> buscarGastoPorId(@PathVariable("id") Long id) throws Exception {
        GastoDTO gastoDTO = this.gastoService.buscarGastoPorId(id);
        return ResponseEntity.ok(gastoDTO);
    }

    @PostMapping
    public ResponseEntity<Void> inserirGasto(@Valid @RequestBody GastoDTO gastoDTO) throws Exception {
        gastoService.inserirGasto(gastoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> alterarGasto(@Valid @RequestBody GastoDTO gastoDTO) throws Exception {
        gastoService.alterarGasto(gastoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarGastoPorId(@Valid @PathVariable("id") Long id) throws Exception {
        gastoService.alterarGastoId(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGasto(@Valid @PathVariable("id") Long id) throws Exception {
        gastoService.excluirGastoId(id);
        return ResponseEntity.ok().build();
    }

}
