package br.com.alexpratesdev.backendmymoney.controller;

import br.com.alexpratesdev.backendmymoney.dto.UsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() throws Exception {
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable("id") Long id) throws Exception {
        UsuarioDTO usuarioDTO = this.usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping
    public void inserirUsuarios(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        usuarioService.inserirUsuario(usuarioDTO);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> alterarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception {
        usuarioService.alterarUsuario(usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleterUsuarios(@Valid @PathVariable("id") Long id) throws Exception {
        usuarioService.excluirUsuarioId(id);
        return ResponseEntity.ok().build();
    }




}
