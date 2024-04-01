package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.LoginDTO;
import br.com.alexpratesdev.backendmymoney.dto.RegistroUsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.infra.TokenGeneration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class UsuarioLogadoTest {
    @Mock
    AuthLoginService authLoginService;
    @Mock
    AuthRegisterService authRegisterService;
    @Mock
    TokenGeneration tokenGeneration;

    @BeforeEach
        //sempre executará antes dos testes unitários
    void setup(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userarioLogadoTest() throws Exception {
        //Registrar novo usuário com token

        RegistroUsuarioDTO registroUsuarioDTO = mock(RegistroUsuarioDTO.class);

        // setando o retorno para senha com o mock
        when(registroUsuarioDTO.getSenha()).thenReturn("Po12387oo");

        registroUsuarioDTO.setNome("Alexsander");
        registroUsuarioDTO.setDocumento("39929056084"); //cpf fake
        registroUsuarioDTO.setEmail("fulano@example.com");
        registroUsuarioDTO.setSenha(registroUsuarioDTO.getSenha()); //senha criptografada
        registroUsuarioDTO.setLogin("AlexLogin123");
        registroUsuarioDTO.setSaldo(BigDecimal.valueOf(1000.0));


        String senhaCriptografada = new BCryptPasswordEncoder().encode(registroUsuarioDTO.getSenha()); //sdhxbiisdS123@ucbibcbs4165
        authRegisterService.inserirUsuarioLogin(registroUsuarioDTO,senhaCriptografada);


        //1 - Verificando se o método getSenha foi chamado duas vezes
        verify(registroUsuarioDTO,times(2)).getSenha();


        // 2 - verificando se o método inserirUsuarioLogin foi chamado só uma vez
        verify(authRegisterService,times(1)).inserirUsuarioLogin(registroUsuarioDTO,senhaCriptografada);

        //gerando token do usuario
        UsuarioEntity usuarioEntity = new UsuarioEntity(registroUsuarioDTO,senhaCriptografada);

        String token = null;
       token = when(tokenGeneration.gerarToken(usuarioEntity)).thenReturn(token).toString();

        //logar usuario
        LoginDTO loginDTO = new LoginDTO(usuarioEntity.getLogin(),usuarioEntity.getSenha(),token);
        when(authLoginService.realizarLogin(loginDTO)).thenReturn(token);

        //3  - verificando token atual e esperado
        String esperadoToken = token; // Defina o token esperado aqui
        String atualToken = authLoginService.realizarLogin(loginDTO);
        Assertions.assertEquals(esperadoToken, atualToken);
    }
}
