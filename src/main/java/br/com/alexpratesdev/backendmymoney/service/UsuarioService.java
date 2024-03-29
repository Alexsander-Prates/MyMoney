package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.CategoriaDTO;
import br.com.alexpratesdev.backendmymoney.dto.UsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class UsuarioService {

    //chama o repositoy

    @Autowired //Spring vai gerenciar
    private UsuarioRepository usuarioRepository;

    //busca no BD e retornar o DTO para o controller jogar ao front

    
    public List<UsuarioDTO> listarTodosUsuarios() throws Exception {
        //1º Buscar no banco de dados através do entity
        List<UsuarioEntity> usuarioEntity = this.usuarioRepository.findAll();
        if (usuarioEntity.isEmpty()){
            throw new Exception("Nenhuma usuário cadastrado");
        }
        return usuarioEntity.stream().map(UsuarioDTO::new).collect(Collectors.toList());

    }

    public UsuarioDTO buscarUsuarioPorId(Long id) throws Exception {
        if(id!=null && this.usuarioRepository.existsById(id)){
            UsuarioEntity usuarioEntity = (this.usuarioRepository.findById(id).get());
            return new UsuarioDTO(usuarioEntity);
        } else{
            throw new Exception("Usuário não encontrado");
        }

    }


    public void inserirUsuario(UsuarioDTO usuarioDTO) throws Exception {
        //controller vai mandar um DTO para camanda service
        //converter o usuario DTO para Entity
        if(usuarioDTO!=null){
            UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDTO);
            this.usuarioRepository.save(usuarioEntity);
        }else{
            throw new Exception("Digite novamente");
        }
    }

    public UsuarioDTO alterarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        //retornar um UsuarioDTO para apresentaçãothrows ChangeSetPersister.NotFoundException
        if(usuarioDTO!=null){
            UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDTO);
            return new UsuarioDTO(this.usuarioRepository.save(usuarioEntity));
        } else{
            throw new Exception("Digite novamente");
        }
    }

    public void excluirUsuarioId(Long id) throws Exception {
        if(id!=null){
            UsuarioEntity usuarioEntityId = this.usuarioRepository.findById(id).get();
            this.usuarioRepository.delete(usuarioEntityId);
        }else{
            throw new Exception("Parâmetro incorreto");
        }

    }

}