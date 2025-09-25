package br.edu.poli.screenmatch;

import br.edu.poli.screenmatch.model.ConverteDados;
import br.edu.poli.screenmatch.model.DadosSeries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.edu.poli.screenmatch.service.ConsumoApi;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
        // minha api key OMDb API: http://www.omdbapi.com/?i=tt3896198&apikey=b5b37f47
        SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("http://www.omdbapi.com/?t=the+office&apikey=b5b37f47");
        System.out.println(json);

        var converte = new ConverteDados();
        var dadosSeries = converte.obterDados(json,DadosSeries.class);
        System.out.println(dadosSeries);

        //json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
        //System.out.println(json);
    }
}
