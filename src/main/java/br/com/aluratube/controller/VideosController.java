package br.com.aluratube.controller;

import br.com.aluratube.controller.dto.VideoDTO;
import br.com.aluratube.controller.exception.ItemNotFoundException;
import br.com.aluratube.controller.form.VideoForm;
import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.CategoriaRepository;
import br.com.aluratube.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videos")
public class VideosController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<VideoDTO> pesquisaPorTitulo(@RequestParam(required = false) String titulo,
                                            @PageableDefault(page=0, size=5) Pageable paginacao){
        if(titulo==null){
            Page<Video> videos = videoRepository.findAll(paginacao);
            return VideoDTO.converter(videos);
        }
        Optional<Video> optional =  videoRepository.findByTitulo(titulo);
        if (optional.isPresent()){
            Page<Video> video = videoRepository.findByTitulo(titulo, paginacao);
            return VideoDTO.converter(video);
        }
        throw new ItemNotFoundException();
    }

    @GetMapping("/free")
    public Page<VideoDTO> retornaVideosFree(@PageableDefault(page=0, size=5) Pageable paginacao){
        List<Video> videos = new ArrayList<>();
        Optional<Video> aux = videoRepository.findById((long) 1);
        for(long i = 2; i<6; i++){
            if(aux.isPresent()){
                Video video = videoRepository.getReferenceById(i-1);
                videos.add(video);
            }
            else break;
            aux = videoRepository.findById(i);
        }
        Page<Video> pageVideos = new PageImpl<Video>(videos, paginacao, videos.size());
        return VideoDTO.converter(pageVideos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> videoEncontrado(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()){
            return ResponseEntity.ok(new VideoDTO(video.get()));
        }
        throw new ItemNotFoundException();
    }

    @PostMapping
    public ResponseEntity<VideoDTO> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder){
        Video video = form.converter(categoriaRepository);
        videoRepository.save(video);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDTO(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid VideoForm form){
        Optional<Video> optional =  videoRepository.findById(id);

        if (optional.isPresent()){
            Video video = videoRepository.getReferenceById(id);
            video = form.atualizar(video);
            return ResponseEntity.ok(new VideoDTO(video));
        }
        throw new ItemNotFoundException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id){
        Optional<Video> optional = videoRepository.findById(id);

        if(optional.isPresent()){
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ItemNotFoundException();
    }

}
