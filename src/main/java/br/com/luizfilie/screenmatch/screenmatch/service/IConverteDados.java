package br.com.luizfilie.screenmatch.screenmatch.service;

import br.com.luizfilie.screenmatch.screenmatch.model.DadosSerie;

public interface IConverteDados {
    // Criando um metodo para retonar um tipo generico
    <T> T obterDados(String json, Class<T> classe);
}
