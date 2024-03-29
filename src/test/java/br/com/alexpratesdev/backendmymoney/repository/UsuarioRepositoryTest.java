package br.com.alexpratesdev.backendmymoney.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")//Junit usar test properties
class UsuarioRepositoryTest {

    @Test
    void findByLogin() {
    }
}