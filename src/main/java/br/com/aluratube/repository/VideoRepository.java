package br.com.aluratube.repository;

import br.com.aluratube.modelo.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findByTitulo(String titulo, Pageable paginacao);
    Optional<Video> findByTitulo(String titulo);
    Video getReferenceByTitulo(String titulo);
    Page<Video> findById(long i, Pageable paginacao);

    Optional<Video> findById(Long id);
}
