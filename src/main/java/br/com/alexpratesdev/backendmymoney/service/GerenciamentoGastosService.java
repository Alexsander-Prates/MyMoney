package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.repository.CategoriaRepository;
import br.com.alexpratesdev.backendmymoney.repository.GastoRepository;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenciamentoGastosService {

    @Autowired
    GastoRepository gastoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    AuthLoginService authLoginService;




    public BigDecimal somarGastos() throws Exception {
        UsuarioEntity usuarioEntity = (UsuarioEntity) authLoginService.getCurrentUserDetails();
        if(usuarioEntity!=null){
            Long userId = usuarioEntity.getId();
            List<GastoEntity> gastosDoUsuario = gastoRepository.findByUsuarioEntityId(userId);
            BigDecimal soma =BigDecimal.ZERO;
            for(GastoEntity gasto:gastosDoUsuario){
                soma=soma.add(gasto.getValor());
            }
            return soma;
        }else{
            throw new Exception("Erro");
        }
    }

    public List<GastoDTO> buscarGastoPorUsuario() throws Exception {
        UsuarioEntity usuarioEntity = (UsuarioEntity) authLoginService.getCurrentUserDetails();
        if (usuarioEntity != null) {
            Long userId = usuarioEntity.getId();
            List<GastoEntity> gastosDoUsuario = gastoRepository.findByUsuarioEntityId(userId);

            return gastosDoUsuario.stream().map(GastoDTO::new).collect(Collectors.toList());
        } else {
            throw new Exception("Token incorréto");
        }
    }
}
