package br.com.luizfilie.screenmatch.screenmatch.start;

import br.com.luizfilie.screenmatch.screenmatch.principal.Principal;
import br.com.luizfilie.screenmatch.screenmatch.repository.EpisodioRepository;
import br.com.luizfilie.screenmatch.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    @Autowired
    private SerieRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository);
        principal.exibiMenu();
    }
}
