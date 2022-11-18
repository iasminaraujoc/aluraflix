package br.com.aluratube.controller.dto;

import br.com.aluratube.modelo.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

public class VideoDTO {
    private Long id;
    private Long categoriaId;
    private String titulo;
    private String descricao;
    private String url;

    public VideoDTO(Video video) {
        this.id = video.getId();
        this.categoriaId = video.getCategoria().getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public static Page<VideoDTO> converter(Page<Video> videos) {
        return videos.map(VideoDTO::new);
    }

    public Long getId() {
        return id;
    }

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
}
