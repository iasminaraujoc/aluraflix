package br.com.aluratube.controller.form;

import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriaFormTest {

    @Autowired
    CategoriaRepository categoriaRepository;
    CategoriaForm form = new CategoriaForm();

    @Test
    void deveriaConverterFormEmCategoria(){
        form.setTitulo("Back-end");
        form.setCor("Azul");
        Categoria categoria = form.converter();
        Categoria nova = new Categoria("Back-end", "Azul");

        assertEquals(nova.getTitulo(), categoria.getTitulo());
        assertEquals(nova.getCor(), categoria.getCor());
    }

    @Test
    void deveriaAtualizarTituloECor(){
        form.setTitulo("Back-end");
        form.setCor("Azul");

        Categoria atualizada = form.atualizar((long) 1, categoriaRepository);
        assertEquals("Back-end", atualizada.getTitulo());
        assertEquals("Azul", atualizada.getCor());
    }
}