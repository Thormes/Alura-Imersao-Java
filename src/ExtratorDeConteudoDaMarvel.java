import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExtratorDeConteudoDaMarvel implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json){
        JsonParser parser = new JsonParser();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj;
        try{
            obj = mapper.readTree(json);
        }
        catch (JsonProcessingException ex){
            return null;
        }
        ArrayNode lista = (ArrayNode) obj.get("data").get("results");
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        return StreamSupport.stream(lista.spliterator(), false).map(t -> {
            String titulo = t.get("name").textValue();
            JsonNode thumb = t.get("thumbnail");
            String urlImagem = thumb.get("path").textValue() + "." + thumb.get("extension").textValue();
            return new Conteudo(titulo, urlImagem);
        }).collect(Collectors.toList());
    }
}
