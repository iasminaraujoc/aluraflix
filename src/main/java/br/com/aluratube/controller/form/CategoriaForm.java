package br.com.aluratube.controller.form;


import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {
    private Long id;
    @NotNull
    private String titulo;
    @NotNull
    private String cor;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }

    public Categoria converter() {
        return new Categoria(titulo, cor);
    }

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setTitulo(titulo);
        categoria.setCor(cor);

        return categoria;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
