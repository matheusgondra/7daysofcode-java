package br.com.matheus.main;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apiKey = System.getenv("API_KEY");
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

    }

}
