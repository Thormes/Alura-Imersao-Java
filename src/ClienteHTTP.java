import Exceptions.HttpClientException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHTTP {
    public String buscaDados (String url) throws HttpClientException {
        try{
        HttpClient client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
        } catch(IOException | InterruptedException ex){
            throw new HttpClientException("Não foi possível buscar o conteúdo: " + ex.getCause());
        }
    }
}
