package br.com.matheus.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientService {
	private final String API_KEY = System.getenv("API_KEY");
	private final String BASE_URL = "https://api.themoviedb.org/3/";

	public String get(String url) throws IOException, InterruptedException {
		url = BASE_URL + url + "?api_key=" + API_KEY;
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
}
