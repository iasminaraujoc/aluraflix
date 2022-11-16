package br.com.aluratube.repository;

import br.com.aluratube.controller.exception.ItemNotFoundException;
import br.com.aluratube.modelo.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VideoRepositoryTest {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByTituloExistente() {
        String titulo = "Um+novo+guia+de+Carreira+em+Tech+e+o+Dia+da+Programação";

        Video portfolio = new Video();
        portfolio.setTitulo(titulo);
        portfolio.setDescricao("Como fazer um Portfólio para seus projetos no GitHub e usar no LinkedIn," +
                                " os principais passos para sua carreira na programação.");

        entityManager.persist(portfolio);

        Video video = repository.getReferenceByTitulo(titulo);
        Assertions.assertNotNull(video);
        Assertions.assertEquals(titulo, video.getTitulo());

    }

    @Test
    void findByTituloNaoExistente() {
        String titulo = "Introdução+a+Ruby+on+Rails";
        Video video = repository.getReferenceByTitulo(titulo);

        Assertions.assertNull(video);
    }

    @Test
    void findAllTest() {
        List<Video> videos = repository.findAll();
        int tamanhoEsperado = 4;

        Assertions.assertEquals(tamanhoEsperado, videos.size());
    }
}