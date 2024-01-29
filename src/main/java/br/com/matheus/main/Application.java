package br.com.matheus.main;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        List<String> titles = parseJson(json, "\"title\":\\s*\"([^\"]+)\"");
        List<String> urlImages = parseJson(json, "\"poster_path\":\\s*\"([^\"]+)\"");
        List<String> ratings = parseJson(json, "\"vote_average\":\\s*([0-9]+(\\.[0-9]+)?)");
        List<String> years = parseJson(json, "\"release_date\":\\s*\"([0-9]{4})");

        for (int i = 0; i < titles.size(); i++) {
            System.out.println("Title: " + titles.get(i) + " - URL Image: https://image.tmdb.org/t/p/original" + urlImages.get(i) + " - Rating: " + ratings.get(i) + " - Year: " + years.get(i));
        }
    }

    private static List<String> parseJson(String json, String regex) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        while (matcher.find()) {
            list.add(matcher.group(1));
        }

        return list;
    }
}
