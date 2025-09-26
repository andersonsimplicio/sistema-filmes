package br.edu.poli.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(
        @JsonAlias("Season")Integer numero,
        @JsonAlias("Episodes") ArrayList<DadosEpisodios> listaEpisodios
) {
}
