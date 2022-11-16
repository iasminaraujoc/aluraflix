package br.com.aluratube.controller.form;

import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VideoFormTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    VideoForm form = new VideoForm();

    @Test
    void deveriaConverterFormEmVideoCategoriaLivre() {
        form.setTitulo("Linux - comandos basicos");
        form.setDescricao("Uma rápida explicação sobre os comandos do Linux");
        form.setUrl("url");
        form.setCategoriaId(null);

        Categoria categoriaLivre = new Categoria("Livre", "Rosa");

        Video video = new Video("Linux - comandos basicos",
                "Uma rápida explicação sobre os comandos do Linux", "url", categoriaLivre);

        Video convertido = form.converter(categoriaRepository);

        assertEquals(video.getCategoria().getTitulo(), convertido.getCategoria().getTitulo());
        assertEquals(video.getTitulo(), convertido.getTitulo());
        assertEquals(video.getDescricao(), convertido.getDescricao());
        assertEquals(video.getUrl(), convertido.getUrl());
    }

    @Test
    void deveriaConverterFormEmVideoCategoriaId() {
        form.setTitulo("Linux - comandos basicos");
        form.setDescricao("Uma rápida explicação sobre os comandos do Linux");
        form.setUrl("url");
        form.setCategoriaId((long)3);

        Categoria categoria = new Categoria("Front-end", "Azul");

        Video video = new Video("Linux - comandos basicos",
                "Uma rápida explicação sobre os comandos do Linux", "url", categoria);

        Video convertido = form.converter(categoriaRepository);

        assertEquals(video.getCategoria().getTitulo(), convertido.getCategoria().getTitulo());
        assertEquals(video.getTitulo(), convertido.getTitulo());
        assertEquals(video.getDescricao(), convertido.getDescricao());
        assertEquals(video.getUrl(), convertido.getUrl());
    }

    @Test
    void deveriaMudarTituloEDescricao() {
        Categoria categoria = new Categoria("Categoria 1", "rosa");
        Video video = new Video("Titulo 1", "descrição 1", "url1", categoria );

        VideoForm form = new VideoForm();
        form.setTitulo("Titulo 2");
        form.setDescricao("Descrição 2");

        form.atualizar(video);

        assertEquals("Titulo 2", video.getTitulo());
        assertEquals("Descrição 2", video.getDescricao());
    }
}