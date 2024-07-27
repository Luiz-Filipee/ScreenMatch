package br.com.luizfilie.screenmatch.screenmatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;


public class Episodio {
    private Integer temporada;
    private String tituloEpisodio;
    private Integer numeroEpisodio;
    private Double avaliacaoEpisodio;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.tituloEpisodio = dadosEpisodio.tituloEpisodio();
        this.numeroEpisodio = dadosEpisodio.numeroEpisodio();
        try{
            this.avaliacaoEpisodio = Double.valueOf(dadosEpisodio.avaliacaoEpisodio());
        }catch (NumberFormatException ex){
            this.avaliacaoEpisodio = 0.0;
        }
        try{
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        }catch (DateTimeException ex){
            this.dataLancamento = null;
        }
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTituloEpisodio() {
        return tituloEpisodio;
    }

    public void setTituloEpisodio(String tituloEpisodio) {
        this.tituloEpisodio = tituloEpisodio;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacaoEpisodio() {
        return avaliacaoEpisodio;
    }

    public void setAvaliacaoEpisodio(Double avaliacaoEpisodio) {
        this.avaliacaoEpisodio = avaliacaoEpisodio;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "temporada=" + temporada +
                ", tituloEpisodio='" + tituloEpisodio + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacaoEpisodio=" + avaliacaoEpisodio +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}
