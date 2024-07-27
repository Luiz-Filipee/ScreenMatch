package br.com.luizfilie.screenmatch.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignora a procura de dados do json que sao procurados sem o nosso interesse
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie
        // Podemos utilizar uma array de busca para o json, caso nao sabemos o nome
        //@JsonAlias({"Title","Titulo"}) String tituloExemplo,
        (@JsonAlias("Title") String titulo,
         @JsonAlias("totalSeasons") Integer totalTemporadas,
         @JsonAlias("imdbRating") String avaliacao,
         @JsonAlias("Genre") String genero,
         @JsonAlias("Actors") String atores,
         @JsonAlias("Poster") String poster,
         @JsonAlias("Plot") String sinopse)

        //@JsonProperty("imdbRating") String avaliacaoExemplo
{
}
