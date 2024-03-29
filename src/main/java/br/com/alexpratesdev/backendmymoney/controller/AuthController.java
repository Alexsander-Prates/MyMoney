package br.com.alexpratesdev.backendmymoney.controller;

import br.com.alexpratesdev.backendmymoney.dto.LoginDTO;
import br.com.alexpratesdev.backendmymoney.dto.LoginResponseDTO;
import br.com.alexpratesdev.backendmymoney.dto.RegistroUsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.infra.TokenGeneration;
import br.com.alexpratesdev.backendmymoney.service.AuthLoginService;
import br.com.alexpratesdev.backendmymoney.service.AuthRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthLoginService authLoginService;

    @Autowired
    private AuthRegisterService authRegisterService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

        return ResponseEntity.ok(new LoginResponseDTO(authLoginService.realizarLogin(loginDTO)));

    }

    @PostMapping("/registrar")
    public ResponseEntity<RegistroUsuarioDTO> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO) throws Exception {
        //verificar se existe alguem cadastrado com esse login

        String senhaCriptografada = new BCryptPasswordEncoder().encode(registroUsuarioDTO.getSenha());

        authRegisterService.inserirUsuarioLogin(registroUsuarioDTO,senhaCriptografada);

        return ResponseEntity.ok().build();

    }
}
