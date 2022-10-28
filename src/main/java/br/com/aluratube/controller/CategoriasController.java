package br.com.aluratube.controller;

import br.com.aluratube.controller.dto.CategoriaDTO;
import br.com.aluratube.controller.form.CategoriaForm;
import br.com.aluratube.modelo.Categoria;
import br.com.aluratube.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaDTO> listaCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDTO.converter(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> encontrarCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return ResponseEntity.ok(new CategoriaDTO(categoria.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder){
        Categoria novaCategoria = form.converter();
        categoriaRepository.save(novaCategoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(novaCategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDTO(novaCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaForm form){
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if(categoriaOpt.isPresent()){
            Categoria categoria = form.atualizar(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDTO(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> deletarCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
