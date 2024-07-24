package br.com.luizfilie.screenmatch.screenmatch.principal;

import br.com.luizfilie.screenmatch.screenmatch.model.DadosEpisodio;
import br.com.luizfilie.screenmatch.screenmatch.model.DadosSerie;
import br.com.luizfilie.screenmatch.screenmatch.model.DadosTemporada;
import br.com.luizfilie.screenmatch.screenmatch.model.Episodio;
import br.com.luizfilie.screenmatch.screenmatch.service.ConsumoApi;
import br.com.luizfilie.screenmatch.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=6585022c";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    public void exibiMenu() {

        System.out.println("Digite o nome da serie para buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.ObterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> dadosTemporadaList = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoApi.ObterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            dadosTemporadaList.add(dadosTemporada);
        }
        dadosTemporadaList.forEach(System.out::println);
       /*for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
            List<DadosEpisodio> episodios = dadosTemporadaList.get(i).episodios();
            for (int j = 0; j < episodios.size(); j++) {
                System.out.println(episodios.get(j).tituloEpisodio());/            }
        }*/

        dadosTemporadaList.forEach(t -> t.episodios().forEach(e -> System.out.println(e.tituloEpisodio())));

        List<DadosEpisodio> dadosEpisodios = dadosTemporadaList.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episodios");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacaoEpisodio().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacaoEpisodio).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = dadosTemporadaList.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);


//        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
//
//        nomes.stream()
//                .sorted() // ordenando
//                .limit(3) // limitando quant de dados
//                .filter(n -> n.startsWith("N")) // filtrando por letra
//                .map(String::toUpperCase) // transformado o nome filtrado em uppercase
//                .forEach(System.out::println); // percorrendo e imprimindo os dados mapeados anteriomente
//
//        List<String> palavras = Arrays.asList("Java", "Stream" , "Java");
//
//        List<String> resultado = palavras.stream()
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(resultado);
//
//      palavras.stream()
//               .distinct()
//               .forEach(palavra -> System.out.println("Linguagem " + palavra + "!"));
//
//        List<Integer> tamanhos = palavras.stream()
//                .map(s -> s.length())
//                .collect(Collectors.toList());
//        System.out.println(tamanhos);
//
//        List<Integer> numeros = Arrays.asList(1,2,4);
//        Set<Integer> numerosPares = numeros.stream()
//                .filter(n -> n % 2 == 0)
//                .collect(Collectors.toSet());
//        System.out.println(numerosPares);

    }


}
