package br.com.matheus.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Movie(
	String title,
	@JsonProperty("poster_path") String poster,
	@JsonProperty("vote_average") Double rating,
	@JsonProperty("release_date") String releaseDate
) implements Comparable<Movie> {
	@Override
	public int compareTo(Movie other) {
		return other.rating().compareTo(this.rating());
	}
}
