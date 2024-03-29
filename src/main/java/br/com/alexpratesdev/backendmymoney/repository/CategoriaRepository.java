package br.com.alexpratesdev.backendmymoney.repository;

import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    //extender superclass JpaRepo.. que pede o que será instanciado para o banco e qual tipo de ID

}
