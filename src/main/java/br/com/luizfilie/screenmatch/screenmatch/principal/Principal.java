package br.com.luizfilie.screenmatch.screenmatch.principal;

import br.com.luizfilie.screenmatch.screenmatch.model.DadosSerie;
import br.com.luizfilie.screenmatch.screenmatch.model.DadosTemporada;
import br.com.luizfilie.screenmatch.screenmatch.model.Serie;
import br.com.luizfilie.screenmatch.screenmatch.service.ConsumoApi;
import br.com.luizfilie.screenmatch.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=6585022c";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    public void exibiMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar serie
                    2 - Buscar episodios
                    3 - Lista series buscadas
                                        
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarSerieWeb();
                case 2 -> buscarEpisodioPorSerie();
                case 3 -> listarSeriesBuscadas();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida");
            }
        }
    }

    private void listarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = dadosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DadosSerie dadosSerie = getDadosSerie();
        dadosSeries.add(dadosSerie);
        System.out.println(dadosSerie);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da serie para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.ObterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumoApi.ObterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
