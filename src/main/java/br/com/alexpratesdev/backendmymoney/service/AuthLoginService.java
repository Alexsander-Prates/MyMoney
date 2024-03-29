package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.LoginDTO;

import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.infra.TokenGeneration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class AuthLoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenGeneration tokenGeneration;

    public String realizarLogin(LoginDTO loginDTO) throws Exception {
        if(loginDTO!=null){
            //criptografando a senha
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha());
            //autenticando o usuario
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenGeneration.gerarToken((UsuarioEntity) auth.getPrincipal());

            return token;

        }else{
            throw new Exception("Senha incorreta");
        }
    }

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) principal;
            }
        }
        return null;
    }




}
