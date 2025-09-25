package br.edu.poli.screenmatch.model;

public interface IConverteDados {
    <T>  T obterDados(String jason, Class<T> classe);

}
