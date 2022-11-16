package br.com.aluratube.controller.dto;

import br.com.aluratube.modelo.Categoria;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDTOTest {

    @Test
    void deveriaConverterCategoriaEmDTO() {
        List<Categoria> categorias = new ArrayList<>();

        categorias.add(new Categoria("titulo 1", "cor 1"));
        categorias.add(new Categoria("titulo 2", "cor 2"));

        List<CategoriaDTO> categoriaDTOS = CategoriaDTO.converter(categorias);

        for(int i=0; i<2; i++){
            assertEquals(categorias.get(i).getTitulo(), categoriaDTOS.get(i).getTitulo());
            assertEquals(categorias.get(i).getCor(), categoriaDTOS.get(i).getCor());
        }
    }
}