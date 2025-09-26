package br.edu.poli.screenmatch;

import br.edu.poli.screenmatch.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
        // minha api key OMDb API: http://www.omdbapi.com/?i=tt3896198&apikey=b5b37f47
        SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal menu = new Principal();
        menu.exibeMenu();


        //json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
        //System.out.println(json);
    }
}
