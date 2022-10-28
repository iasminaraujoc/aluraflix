package br.com.aluratube.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cor;

    @OneToMany(mappedBy = "categoria")
    private List<Video> videos = new ArrayList<>();

    public Categoria(){}

    public Categoria(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
