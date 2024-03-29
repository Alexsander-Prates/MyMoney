package br.com.alexpratesdev.backendmymoney.service;

import br.com.alexpratesdev.backendmymoney.dto.RegistroUsuarioDTO;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import br.com.alexpratesdev.backendmymoney.infra.TokenGeneration;
import br.com.alexpratesdev.backendmymoney.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthRegisterService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenGeneration tokenGeneration;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public void inserirUsuarioLogin(RegistroUsuarioDTO registroUsuarioDTO, String senhaCriptografada) throws Exception {
        //controller vai mandar um DTO para camanda service
        //converter o usuario DTO para Entity
        if(this.usuarioRepository.findByLogin(registroUsuarioDTO.getLogin())!=null)
            ResponseEntity.badRequest().build();
        else if (registroUsuarioDTO!=null) {
            UsuarioEntity usuarioEntity = new UsuarioEntity(registroUsuarioDTO
                    ,senhaCriptografada);
            this.usuarioRepository.save(usuarioEntity);
        }
        else{
            throw new Exception("Erro criação de novo usuário");
        }
    }


}
