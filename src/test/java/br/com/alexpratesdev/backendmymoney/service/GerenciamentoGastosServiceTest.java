package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.repository.GastoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
class GerenciamentoGastosServiceTest {

    /*1º Preparação do cenário para teste = 1º - buscarGastoPorUsuario = usuário logado?
    2º Execução =
    3º Resultado/Verificação
*/
    @Mock
    GastoRepository gastoRepository;

   @Mock
   UsuarioLogadoTest userarioLogado;


    @Autowired
    @InjectMocks
    GerenciamentoGastosService gerenciamentoGastosService;

    @BeforeEach //sempre executará antes dos testes unitários
    void setup(){
        MockitoAnnotations.initMocks(this);
    }




    @Test
    @DisplayName("Usuário sem token válido para BUSCAR gastos por seu usuário")
    public void buscarGastoPorUsuarioRetornar403 () throws NullPointerException {

        try{

            gerenciamentoGastosService = new GerenciamentoGastosService();

            UsuarioEntity esperado = null;
            List<GastoDTO> atual = gerenciamentoGastosService.buscarGastoPorUsuario();

            Assertions.assertEquals(esperado,atual);

        }catch (NullPointerException n){
            System.out.println(n.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @DisplayName("Usuário sem token válido para SOMAR gastos por seu usuário")
    public void somarGastosRetornar403() throws NullPointerException {
        try{

            gerenciamentoGastosService = new GerenciamentoGastosService();
            UsuarioEntity esperado = null;
            BigDecimal atual = gerenciamentoGastosService.somarGastos();

            Assertions.assertEquals(esperado,atual);

        }catch (NullPointerException n){
            System.out.println(n.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*@Test
    @DisplayName("Teste SOMA com TOKEN OK")
    public void somarGastosComTokenOk() throws Exception {


        *//*String usuarioEntity = userarioLogado.userarioLogado();
        if(usuarioEntity!=null){
            Long userId = usuarioEntity.getId();
            List<GastoEntity> gastosDoUsuario = gastoRepository.findByUsuarioEntityId(userId);
            BigDecimal soma =BigDecimal.ZERO;
            for(GastoEntity gasto:gastosDoUsuario){
                soma=soma.add(gasto.getValor());
            }

        }else{
            throw new Exception("Erro");
        }*//*
    }
*/

}