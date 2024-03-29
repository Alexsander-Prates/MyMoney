package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.CategoriaDTO;
import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;

import br.com.alexpratesdev.backendmymoney.repository.CategoriaRepository;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;


import java.util.Optional;


import static org.mockito.Mockito.*;


public class CategoriaPreenchidaTest {
    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    CategoriaRepository categoriaRepository;

    @BeforeEach
        //sempre executará antes dos testes unitários
    void setup() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void inserirCategoriaPreenchidaTest() throws Exception {

        //preparando ambiente para testes mockando o recebimento do front end
        CategoriaDTO categoriaDTO = mock(CategoriaDTO.class);
        when(categoriaDTO.getNome()).thenReturn("Mercado");
        when(categoriaDTO.getDescricao()).thenReturn("Teste");
        when(categoriaDTO.getUsuario_id()).thenReturn(1L);
        when(categoriaDTO.getUsuario_id()).thenReturn(1L);

        CategoriaDTO categoriaDTO1 = new CategoriaDTO();
        categoriaDTO1.setNome(categoriaDTO.getNome());
        categoriaDTO1.setId(categoriaDTO.getId());
        categoriaDTO1.setUsuario_id(categoriaDTO.getUsuario_id());
        categoriaDTO1.setDescricao(categoriaDTO.getDescricao());

        //transformando em CategoriaEntity para persistência no banco de dados
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNome(categoriaDTO1.getNome());
        categoriaEntity.setDescricao(categoriaDTO1.getDescricao());
        categoriaEntity.setId(categoriaDTO1.getId());

        //configurando o método save para salvar no banco de dados quando for chamado.
        when(categoriaRepository.save(any(CategoriaEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CategoriaEntity salvarCategoriaEntity = categoriaRepository.save(categoriaEntity);

        //1º Teste - verificando quantas vezes o método save foi chamado
        verify(categoriaRepository,times(1)).save(any());

        //2º Teste - verificando que a instáncia não é nula
        Assertions.assertNotNull(salvarCategoriaEntity);

        String atual = categoriaEntity.getNome();
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(salvarCategoriaEntity));
        String esperado = categoriaRepository.findById(salvarCategoriaEntity.getId()).map(CategoriaEntity::getNome).get();

        //3º Teste - verificar se o atual valor é igual ao salvo no banco de dados.
        Assertions.assertEquals(esperado,atual);
    }
}
