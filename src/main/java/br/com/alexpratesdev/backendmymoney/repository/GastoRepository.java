package br.com.alexpratesdev.backendmymoney.repository;

import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface GastoRepository extends JpaRepository<GastoEntity, Long> {
    List<GastoEntity> findByUsuarioEntityId(Long usuarioId);
    //extender superclass JpaRepo.. que pede o que será instanciado para o banco e qual tipo de ID
}
