package br.com.aluratube.repository;

import br.com.aluratube.modelo.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
