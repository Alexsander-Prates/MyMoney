package br.com.alexpratesdev.backendmymoney.controller;

import br.com.alexpratesdev.backendmymoney.dto.CategoriaDTO;
import br.com.alexpratesdev.backendmymoney.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public List<CategoriaDTO> listarTodasCategorias() throws Exception {
        return categoriaService.buscarTodasCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable("id") Long id) throws Exception {
        CategoriaDTO categoriaDTO = this.categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PostMapping
    public ResponseEntity<Void> inserirCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception {
        if (categoriaDTO.getUsuario_id() == null) {
            return ResponseEntity.badRequest().build();
        }
        categoriaService.inserirCategoria(categoriaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarCateria(@Valid @PathVariable("id") Long id) throws Exception {
        categoriaService.alterarCategoriaId(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@Valid @PathVariable ("id") Long id) throws Exception {
        categoriaService.excluirCategoria(id);
        return ResponseEntity.ok().build();
    }


}
