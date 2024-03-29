package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.UsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@DataJpaTest
@ActiveProfiles("test")//Junit usar test properties
class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    UsuarioService usuarioService;

    @Test
    public void inserirUsuarioSucesso() {
        //crianodo mock para simular um usuário
        UsuarioRepository repositoryMock = Mockito.mock(UsuarioRepository.class);
        UsuarioDTO usuarioDTOMock = Mockito.mock(UsuarioDTO.class);

        //quando o save for chamado com o usuario atual ele vai retornar esse usuário salvo
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        when(repositoryMock.save(usuarioEntity)).thenReturn(usuarioEntity);

        repositoryMock.save(usuarioEntity);

        String atual = repositoryMock.save(usuarioEntity).getNome();



    }
}