package br.com.aluratube.controller.form;

import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.VideoRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class VideoForm {
    private String titulo;
    private String descricao;
    private String url;

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

    public Video converter() {
        return new Video(titulo, descricao, url);
    }

    public Video atualizar(Long id, VideoRepository videoRepository){
        Video video = videoRepository.getReferenceById(id);
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);

        return video;
    }
}


