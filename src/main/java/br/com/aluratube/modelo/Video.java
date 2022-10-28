package br.com.aluratube.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private String url;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    public Video(){
    }

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
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
//
//    public void setIdCategoria(Long idCategoria) {
//        this.idCategoria = idCategoria;
//    }
//
//    public Long getIdCategoria() {
//        return idCategoria;
//    }
}
