package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.CategoriaDTO;
import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.dto.UsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.repository.CategoriaRepository;
import br.com.alexpratesdev.backendmymoney.repository.GastoRepository;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class GastoService {


    @Autowired
    private GastoRepository gastoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GerenciamentoGastosService gerenciamentoGastosService;

    public List<GastoDTO> listarTodosGastos() throws Exception {

        List<GastoEntity> gastosEntity = gastoRepository.findAll();
        if(gastosEntity.isEmpty()){
            throw new Exception("Sem lançamentos");
        }
        return gastosEntity
                .stream()
                .map(gasto -> new GastoDTO(gasto.getId(), gasto.getValor(), gasto.getDescricao(), gasto.getDia(), gasto.getUsuarioEntity().getId(), gasto.getCategoriaEntity().getId()))
                .toList();
    }

    public void inserirGasto (GastoDTO gastoDTO) throws Exception {
        if(gastoDTO!=null){
            CategoriaEntity categoriaEntityId = categoriaRepository.findById(gastoDTO.getCategoria_id()).get();
            UsuarioEntity usuarioEntityId = usuarioRepository.findById(gastoDTO.getUsuario_id()).get();
            GastoEntity gastoEntity = new GastoEntity(gastoDTO);

            gastoEntity.setUsuarioEntity(usuarioEntityId);
            gastoEntity.setCategoriaEntity(categoriaEntityId);

            gastoRepository.save(gastoEntity);

        } else {
            throw new Exception("Falta de parâmetro");
        }
    }

    public GastoDTO alterarGasto(GastoDTO gastoDTO) throws Exception {
        if(gastoDTO!=null){
            GastoEntity gastoEntity = new GastoEntity(gastoDTO);
            return new GastoDTO(gastoRepository.save(gastoEntity));
        }else{
            throw new Exception("Digite novamente");
        }

    }

    public GastoDTO alterarGastoId(Long id) throws Exception {
        if(id!=null){
            GastoEntity gastoEntityId = gastoRepository.findById(id).get();
            return new GastoDTO(gastoRepository.save(gastoEntityId));
        }else{
            throw new Exception("Digite novamente");
        }
    }

    public void excluirGastoId(Long id) throws Exception {
        if(id!=null){
            GastoEntity gastoEntityId = gastoRepository.findById(id).get();
            gastoRepository.delete(gastoEntityId);
        }else{
            throw new Exception("Parâmetro incorreto");
        }
    }

    public GastoDTO buscarGastoPorId(Long id) throws Exception {
        if(id!=null){
            GastoEntity gastoEntityId = gastoRepository.findById(id).get();
            return new GastoDTO(gastoEntityId);
        }else {
            throw new Exception("Parâmetro incorreto");
        }

    }

}
