package br.edu.poli.screenmatch.model;
import br.edu.poli.screenmatch.model.DadosSeries;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();
    public DadosSeries obterDados(String json){
        return null;
    }

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
