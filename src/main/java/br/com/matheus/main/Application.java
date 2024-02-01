package br.com.matheus.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.matheus.models.Movie;
import br.com.matheus.services.HTMLGenerator;
import br.com.matheus.services.HttpClientService;
import br.com.matheus.utils.JsonParser;

public class Application {
	public static void main(String[] args) throws IOException, InterruptedException {
		String json = new HttpClientService().get("movie/top_rated");

		JsonParser parser = new JsonParser();
		
		json = parser.fromJson(json, JsonNode.class).get("results").toString();

		List<Movie> movies = parser.toList(json, Movie.class);

		HTMLGenerator htmlGenerator = new HTMLGenerator(new FileWriter("movies.html"));
		htmlGenerator.generate(movies);
	}
}
