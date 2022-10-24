package br.com.aluratube.controller;

import br.com.aluratube.controller.dto.VideoDTO;
import br.com.aluratube.modelo.Video;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/videos")
public class VideosController {

    @GetMapping
    public List<VideoDTO> lista(){
        Video video = new Video("Aprendendo API REST em java com spring", "nesse vídeo, Iasmin ensina um pouso sobre spring", "www.alura.com.br/spring");
        return VideoDTO.converter(Arrays.asList(video, video, video));
    }

}
