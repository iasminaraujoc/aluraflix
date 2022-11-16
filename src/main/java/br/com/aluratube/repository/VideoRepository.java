package br.com.aluratube.repository;

import br.com.aluratube.modelo.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByTitulo(String titulo);
    Video getReferenceByTitulo(String titulo);
}
