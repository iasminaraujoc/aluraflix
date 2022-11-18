package br.com.aluratube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class AluratubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluratubeApplication.class, args);
	}

}
