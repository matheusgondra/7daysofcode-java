package br.com.matheus.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.matheus.models.Movie;
import br.com.matheus.services.HttpClientService;
import br.com.matheus.utils.DataBind;

public class Application {
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClientService service = new HttpClientService();
		String json = service.get("movie/top_rated");

		DataBind dataBind = new DataBind();
		JsonNode jsonNode = dataBind.fromJson(json, JsonNode.class);
		json = jsonNode.get("results").toString();

		Movie[] movies = dataBind.fromJson(json, Movie[].class);
		List<Movie> moviesList = Arrays.asList(movies);

		moviesList.forEach(System.out::println);
	}
}
