package br.edu.poli.screenmatch.Principal;

import br.edu.poli.screenmatch.model.*;
import br.edu.poli.screenmatch.service.ConsumoApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Principal {
    private static final String BASE_URL = "https://www.omdbapi.com/";
    private static final String API_KEY = "b5b37f47"; // considere ler de variável de ambiente
    private final ConsumoApi consumoApi = new ConsumoApi();

    public void exibeMenu() {
        Scanner leitura = new Scanner(System.in); // crie uma vez; feche no final da app, se desejar
        System.out.print("Digite o nome da série para a busca: ");
        String nomeSerie = leitura.nextLine();
        int ano = 0;
        LocalDate dataBusca;
        DateTimeFormatter formData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Codifica corretamente o termo de busca
        String titulo = URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);

        // Se quiser buscar episódio específico, inclua Season/Episode:
        // String url = BASE_URL + "?t=" + titulo + "&Season=1&Episode=4&apikey=" + API_KEY;
        //url = https://www.omdbapi.com/?t=Game+of+Thrones&Seanson=1&Episode=4&apikey=b5b37f47
        String url = BASE_URL + "?t=" + titulo + "&apikey=" + API_KEY;

        String json = consumoApi.obterDados(url);

        if (json == null || json.isBlank()) {
            System.out.println("Não foi possível obter dados da API.");
            return;
        }

        ConverteDados conversor = new ConverteDados();
        DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dados.totalTemporadas(); i++) {
            url = BASE_URL + "?t=" + titulo + "&Season="+i+"&apikey=" + API_KEY;
            json = this.consumoApi.obterDados(url);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //temporadas.forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("Top 10 episódios masi bem avaliados");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .peek(e -> System.out.println("Limite " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);
        /*
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);
        System.out.println("A partir de que ano você deseja ver os episódios? ");
        ano = leitura.nextInt();
        leitura.nextLine();

        dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada:  " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data lançamento: " + e.getDataLancamento().format(formatador)
                ));

         */

    }
}
