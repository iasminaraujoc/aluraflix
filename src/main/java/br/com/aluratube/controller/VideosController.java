package br.com.aluratube.controller;

import br.com.aluratube.controller.dto.VideoDTO;
import br.com.aluratube.controller.form.VideoForm;
import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> videoEncontrado(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()){
            return ResponseEntity.ok(new VideoDTO(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VideoDTO> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder){
        Video video = form.converter();
        videoRepository.save(video);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDTO(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid VideoForm form){
        Optional<Video> optional =  videoRepository.findById(id);

        if (optional.isPresent()){
            Video video = form.atualizar(id, videoRepository);
            return ResponseEntity.ok(new VideoDTO(video));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id){
        Optional<Video> optional = videoRepository.findById(id);

        if(optional.isPresent()){
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
