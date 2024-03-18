package br.com.luizfilie.screenmatch.screenmatch.principal;

import br.com.luizfilie.screenmatch.screenmatch.model.DadosSerie;
import br.com.luizfilie.screenmatch.screenmatch.service.ConsumoApi;
import br.com.luizfilie.screenmatch.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.ObterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
        //System.out.println(json);
        //json = consumoApi.ObterDados("https://coffee.alexflipnote.dev/random.json");
        ConverteDados converteDados = new ConverteDados();
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);
    }
}
