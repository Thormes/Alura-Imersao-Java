import ConsoleStyler.ConsoleStyler;
import ConsoleStyler.Realce;
import ConsoleStyler.Cor;
import Exceptions.HttpClientException;
import GeradorFigurinhas.GeradorFigurinhas;

import java.io.*;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        EnvParser envparser = new EnvParser();
        String nasa_key = envparser.properties.get("NASA_KEY");

        //Acessar o IMDB e buscar lista de filmes
        API api = API.IMDB;


        String body = "";
        ClienteHTTP cliente = new ClienteHTTP();
        try{
            body = cliente.buscaDados(api.getUrl());
        } catch (HttpClientException ex){
            System.out.println(ex.getMessage());
            return;
        }

        //extrair só os dados que interessam (título, poster, rating)
        List<Conteudo> lista = api.getExtrator().extraiConteudos(body);

        ConsoleStyler styler = new ConsoleStyler();

        // exibir e manipular os dados
            File diretorio = new File("figurinhas/");
            diretorio.mkdir();
        for (Conteudo conteudo: lista) {
            InputStream inputStream = new URL(conteudo.urlImage()).openStream();
            GeradorFigurinhas geradora = new GeradorFigurinhas();
            geradora.cria(inputStream, "figurinhas/" + conteudo.titulo() + ".png", "TOPZERA", getImageFromClassificacao("10"));
            System.out.println(styler.estilizado("Título: ", new String[] {styler.textColor(Cor.CINZA)})  + styler.estilizado(conteudo.titulo(), new String[] {Realce.NEGRITO.value}));
//          System.out.println(styler.estilizado("Poster: ", new String[] {styler.textColor(Cor.CINZA)})  + styler.estilizado(filme.get("image"), new String[] {Realce.NEGRITO.value}));
//          System.out.println(styler.estilizado("Classificação: " + filme.get("imDbRating"), new String[] {styler.backColorFromRating(filme.get("imDbRating")), styler.textColor(Cor.PRETO)}));
//          System.out.println(styler.starsFromRating(filme.get("imDbRating")));
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

