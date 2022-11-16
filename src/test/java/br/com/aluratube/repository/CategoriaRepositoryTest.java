package br.com.aluratube.repository;

import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.modelo.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Test
    void findAllTest() {
        List<Categoria> categorias = repository.findAll();
        int tamanhoEsperado = 4;

        Assertions.assertEquals(tamanhoEsperado, categorias.size());
    }
}