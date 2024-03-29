package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.CategoriaDTO;
import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.repository.CategoriaRepository;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<CategoriaDTO> buscarTodasCategorias() throws Exception {

        List<CategoriaEntity> categoriasEntity = this.categoriaRepository.findAll();
        if(categoriasEntity.isEmpty()){
            throw new Exception("Nenhuma categoria cadastrada");
        }else{
            return categoriasEntity
                    .stream()
                    .map(categoria -> new CategoriaDTO(categoria.getId()
                            ,categoria.getNome()
                            ,categoria.getDescricao()
                            , categoria.getUsuarioEntity().getId()))
                    .toList();
        }

    }

    public void inserirCategoria(CategoriaDTO categoriaDTO) throws Exception {
        if (categoriaDTO != null) {
            UsuarioEntity usuarioEntityId = this.usuarioRepository.findById(categoriaDTO.getUsuario_id()).get();
            CategoriaEntity categoriaEntity = new CategoriaEntity(categoriaDTO);
            categoriaEntity.setUsuarioEntity(usuarioEntityId);
            this.categoriaRepository.save(categoriaEntity);
        } else {
            throw new Exception("Falta de parâmetros");
        }
    }


    public CategoriaDTO alterarCategoriaId(Long id) throws Exception {
        if(id!=null){
            CategoriaEntity categoriaEntityId = this.categoriaRepository.findById(id).get();
            return new CategoriaDTO(categoriaEntityId);
        } else {
            throw new Exception("Digite novamente");
        }
    }

    public void excluirCategoria(Long id) throws Exception {
        if(id!=null){
            CategoriaEntity categoriaEntityId = this.categoriaRepository.findById(id).get();
            this.categoriaRepository.delete(categoriaEntityId);
        } else{
            throw new Exception("Parâmetro incorreto");
        }
    }

    public CategoriaDTO buscarCategoriaPorId(Long id) throws Exception {
        if(id!=null){
            CategoriaEntity categoriaEntityId = this.categoriaRepository.findById(id).get();
            return new CategoriaDTO(categoriaEntityId);
        } else {
            throw new Exception("Faltando parâmetro");
        }
    }
}
