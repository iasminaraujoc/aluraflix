package br.com.aluratube.controller;

import br.com.aluratube.config.security.TokenService;
import br.com.aluratube.controller.dto.CategoriaDTO;
import br.com.aluratube.controller.dto.TokenDto;
import br.com.aluratube.controller.dto.VideoDTO;
import br.com.aluratube.controller.exception.ItemNotFoundException;
import br.com.aluratube.controller.form.CategoriaForm;
import br.com.aluratube.controller.form.LoginForm;
import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.modelo.Video;
import br.com.aluratube.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<CategoriaDTO> listaCategorias(@PageableDefault(page = 0, size = 5) Pageable paginacao) {
        Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
        return CategoriaDTO.converter(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> encontrarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(new CategoriaDTO(categoria.get()));
        }
        throw new ItemNotFoundException();
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<VideoDTO>> videoPorCategoria(@PathVariable Long id, @PageableDefault(page = 0, size = 5) Pageable paginacao) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = categoriaRepository.getReferenceById(id);
            List<Video> videosLista = categoria.getVideos();
            Page<Video> videosPagina = new PageImpl<>(videosLista);
            return ResponseEntity.ok(VideoDTO.converter(videosPagina));
        }
        throw new ItemNotFoundException();
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria novaCategoria = form.converter();
        categoriaRepository.save(novaCategoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(novaCategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDTO(novaCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaForm form) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = form.atualizar(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDTO(categoria));
        }
        throw new ItemNotFoundException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> deletarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ItemNotFoundException();
    }
}
