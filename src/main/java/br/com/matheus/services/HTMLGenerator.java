package br.com.matheus.services;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.matheus.models.Movie;

public class HTMLGenerator {
	private Writer writer;

	public HTMLGenerator(Writer writer) {
		this.writer = writer;
	}

	public void generate(List<Movie> movies) {
		try {
			writer.write("<!DOCTYPE html><html>");
			writer
					.append(
							"<head><meta charset=\"UTF-8\"><title>Top Rated Movies</title><link rel=\"stylesheet\" href=\"./style.css\"><link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\"></head>")
					.append("<body>");

			writer.append("<div class=\"cards\" style=\"display:flex;flex-wrap:wrap;gap:1rem;justify-content:center;\">");
			for (Movie movie : movies) {
				var format = new SimpleDateFormat("yyyy-MM-dd");
				var date = format.parse(movie.releaseDate());
				var movieYear = new SimpleDateFormat("yyyy").format(date);

				writer.append("""
						<div class="card" style="width: 18rem;">
							<img class="card-img-top" src="%s" alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title">%s</h5>
								<p class="card-text">Nota: %s - Ano: %s</p>
							</div>
						</div>
							""".formatted("https://image.tmdb.org/t/p/w500" + movie.poster(), movie.title(), movie.rating(),
						movieYear));
			}
			writer.append("</div>");

			writer.append(
					"<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script><script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
			writer.append("</body></html>");
			writer.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
}