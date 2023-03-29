import ConsoleStyler.ConsoleStyler;
import ConsoleStyler.Realce;
import ConsoleStyler.Cor;
import GeradorFigurinhas.GeradorFigurinhas;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Acessar o IMDB e buscar lista de filmes
        String url = URLList.POPULAR_MOVIES.url;
        HttpClient client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (título, poster, rating)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        ConsoleStyler styler = new ConsoleStyler();

        // exibir e manipular os dados
            File diretorio = new File("figurinhas/");
            diretorio.mkdir();
        for (Map<String, String> filme: listaDeFilmes) {
            InputStream inputStream = new URL(filme.get("image")).openStream();
            GeradorFigurinhas geradora = new GeradorFigurinhas();
            geradora.cria(inputStream, "figurinhas/" + filme.get("title") + ".png", getTextoFromClassificacao(filme.get("imDbRating")), getImageFromClassificacao(filme.get("imDbRating")));
            System.out.println(styler.estilizado("Título: ", new String[] {styler.textColor(Cor.CINZA)})  + styler.estilizado(filme.get("title"), new String[] {Realce.NEGRITO.value}));
            System.out.println(styler.estilizado("Poster: ", new String[] {styler.textColor(Cor.CINZA)})  + styler.estilizado(filme.get("image"), new String[] {Realce.NEGRITO.value}));
            System.out.println(styler.estilizado("Classificação: " + filme.get("imDbRating"), new String[] {styler.backColorFromRating(filme.get("imDbRating")), styler.textColor(Cor.PRETO)}));
            System.out.println(styler.starsFromRating(filme.get("imDbRating")));
            System.out.println();
        }
    }

    private static String getTextoFromClassificacao(String classificacao){
        double rating = Double.parseDouble(classificacao);
        if (rating <= 5.0){
            return "FULERAGE";
        } else if (rating < 8) {
            return "MARROMENO";
        } else{
            return "XIBATA";
        }
    }
    private static InputStream getImageFromClassificacao(String classificacao) throws FileNotFoundException {
        double rating = Double.parseDouble(classificacao);
        if (rating < 8.0){
            return new FileInputStream(new File("entrada/dislike.png"));
        } else{
            return new FileInputStream(new File("entrada/like.png"));
        }
    }
}

