import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json){
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        return listaDeAtributos.stream().map(t -> {
            String titulo = t.get("title");
            String urlImagem = t.get("image");
            return new Conteudo(titulo, urlImagem);
        }).collect(Collectors.toList());
    }
}
