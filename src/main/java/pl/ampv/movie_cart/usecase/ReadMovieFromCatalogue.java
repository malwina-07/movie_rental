package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;


@Component
@RequiredArgsConstructor
public class ReadMovieFromCatalogue {

    private final MovieRepository moviesRepository;

    public Movie readByTitle(String title) throws MovieDoesNotExistInCatalogue {
        return moviesRepository.findByTitle(title)
                .orElseThrow(MovieDoesNotExistInCatalogue::new);
    }
}
