package br.com.aluratube.controller.dto;

import br.com.aluratube.modelo.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDTO {
    private Long id;
    private String titulo;
    private String cor;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public static Page<CategoriaDTO> converter(Page<Categoria> categorias) {
        return categorias.map(CategoriaDTO::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }
}
