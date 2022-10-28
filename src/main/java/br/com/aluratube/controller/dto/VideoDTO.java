package br.com.aluratube.controller.dto;

import br.com.aluratube.modelo.Video;

import java.util.List;
import java.util.stream.Collectors;

public class VideoDTO {
    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    private Long categoriaId;

    public VideoDTO(Video video) {
        this.id = video.getId();
        this.categoriaId = video.getCategoria().getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public static List<VideoDTO> converter(List<Video> videos) {
        return videos.stream().map(VideoDTO::new).collect(Collectors.toList());
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
}
