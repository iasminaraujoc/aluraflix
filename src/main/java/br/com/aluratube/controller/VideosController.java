package br.com.aluratube.controller;

import br.com.aluratube.controller.dto.VideoDTO;
import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/videos")
public class VideosController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<VideoDTO> lista(){
        List<Video> videos = videoRepository.findAll();
        return VideoDTO.converter(videos);
    }

}
