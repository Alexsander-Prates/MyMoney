package br.com.alexpratesdev.backendmymoney.repository;

import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    //extender superclass JpaRepo.. que pede o que será instanciado para o banco e qual tipo de ID

    UserDetails findByLogin(String login);
}
