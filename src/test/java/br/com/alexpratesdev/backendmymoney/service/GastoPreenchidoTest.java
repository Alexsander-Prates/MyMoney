package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.repository.CategoriaRepository;
import br.com.alexpratesdev.backendmymoney.repository.GastoRepository;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import java.util.Optional;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class GastoPreenchidoTest {
    @Mock
    GastoRepository gastoRepository;

    @BeforeEach
        //sempre executará antes dos testes unitários
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Verificação valores passados com banco de dados salvo")
    public void inserirGastoPreenchidoTest() throws Exception {
        // Preparando o mock
        GastoDTO gastoDTO = mock(GastoDTO.class);
        when(gastoDTO.getDescricao()).thenReturn("Gastos do mes");
        when(gastoDTO.getValor()).thenReturn(BigDecimal.valueOf(100.00));
        when(gastoDTO.getId()).thenReturn(1L);


        GastoDTO gastoDTO1 = new GastoDTO();
        gastoDTO1.setDescricao(gastoDTO.getDescricao());
        gastoDTO1.setValor(gastoDTO.getValor());
        gastoDTO1.setId(gastoDTO.getId());


        GastoEntity gastoEntity = new GastoEntity();
        gastoEntity.setValor(gastoDTO1.getValor());
        gastoEntity.setId(gastoDTO1.getId());
        gastoEntity.setDescricao(gastoDTO1.getDescricao());

        // preparando o método findById para retornar gastoEntity
        when(gastoRepository.findById(anyLong())).thenReturn(Optional.of(gastoEntity));

        // preparando o save para retornar o primeiro salvo
        when(gastoRepository.save(any(GastoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // salvando
        GastoEntity salvarGastoEntity = gastoRepository.save(gastoEntity);

        // 1º Teste - verificar se o save foi chamado 1 e somente uma vez
        verify(gastoRepository, times(1)).save(any());

        // 2º Teste - verificando se o salvarGastoEntity não é nulo
        Assertions.assertNotNull(salvarGastoEntity);

        // 3º Teste - verificando se o atual é igual ao salvo
        BigDecimal atual = salvarGastoEntity.getValor();
        BigDecimal esperado = gastoRepository.findById(salvarGastoEntity.getId())
                .map(GastoEntity::getValor)
                .orElse(null);
        Assertions.assertEquals(esperado, atual);
    }

    @Test
    @DisplayName("Verificação valores passados com banco de dados salvo error")
    public void inserirGastoPreenchidoTestError() throws Exception {
        // Preparando o mock
        GastoDTO gastoDTO = mock(GastoDTO.class);
        when(gastoDTO.getDescricao()).thenReturn("Gastos do mes");
        when(gastoDTO.getValor()).thenReturn(BigDecimal.valueOf(100.00));
        when(gastoDTO.getId()).thenReturn(1L);


        GastoDTO gastoDTO1 = new GastoDTO();
        gastoDTO1.setDescricao(gastoDTO.getDescricao());
        gastoDTO1.setValor(gastoDTO.getValor());
        gastoDTO1.setId(gastoDTO.getId());


        GastoEntity gastoEntity = new GastoEntity();
        gastoEntity.setValor(gastoDTO1.getValor());
        gastoEntity.setId(gastoDTO1.getId());
        gastoEntity.setDescricao(gastoDTO1.getDescricao());

        // preparando o método findById para retornar gastoEntity
        when(gastoRepository.findById(anyLong())).thenReturn(Optional.of(gastoEntity));

        // preparando o save para retornar o primeiro salvo
        when(gastoRepository.save(any(GastoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // salvando
        GastoEntity salvarGastoEntity = gastoRepository.save(gastoEntity);

        // 1º Teste - verificando se o atual é igual ao salvo error
        BigDecimal atualError = BigDecimal.valueOf(100.1);
        BigDecimal esperado = gastoRepository.findById(salvarGastoEntity.getId())
                .map(GastoEntity::getValor)
                .orElse(null);
        Assertions.assertNotEquals(esperado, atualError);
        if (!esperado.equals(atualError)) {
            String mensagem = "assertNotEquals - Os valores gastos são diferentes";
            LOGGER.info(mensagem);
            //Assertions.fail(mensagem);
        }

    }
}
