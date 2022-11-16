package br.com.aluratube.controller.dto;

import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.modelo.Video;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoDTOTest {

    @Test
    void deveriaConverterVideoEmDTO() {
        List<Video> videos = new ArrayList<>();

        Categoria categoria = new Categoria("titulo 1", "cor 1");

        videos.add(new Video("titulo 3", "descrição 3", "url 3", categoria));
        videos.add(new Video("titulo 4", "descrição 4", "url 3", categoria));

        List<VideoDTO> videoDTOS = VideoDTO.converter(videos);

        for(int i=0; i<2; i++){
            assertEquals(videos.get(i).getTitulo(), videoDTOS.get(i).getTitulo());
            assertEquals(videos.get(i).getDescricao(), videoDTOS.get(i).getDescricao());
            assertEquals(videos.get(i).getUrl(), videoDTOS.get(i).getUrl());
            assertEquals(videos.get(i).getCategoria().getId(), videoDTOS.get(i).getId());
        }
    }
}