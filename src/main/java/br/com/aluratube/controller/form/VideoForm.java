package br.com.aluratube.controller.form;

import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VideoForm {
    private Long categoriaId;
    @NotNull @NotEmpty
    private String titulo;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull @NotEmpty
    private String url;

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video converter(CategoriaRepository categoriaRepository) {
        Categoria categoria;

        if(categoriaId != null){
            categoria = categoriaRepository.getReferenceById(categoriaId);
        } else {
            Long idCategoriaLivre = Long.valueOf(1);
            categoria = categoriaRepository.getReferenceById(idCategoriaLivre);
        }
        return new Video(titulo, descricao, url, categoria);
    }

    public Video atualizar(Video video){
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);

        return video;
    }
}


