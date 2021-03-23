package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.exception.MovieAlreadyExistsInCatalogueException;

@Component
@RequiredArgsConstructor
public class AddMovieToCatalogue {

    private final MovieRepository movieRepository;

    public Movie add(Movie movie) throws MovieAlreadyExistsInCatalogueException {
        /*return moviesRepository.findByTitle(movie.getTitle())
                .map(moviesRepository::save)
                .orElseThrow(MovieAlreadyExistsInCatalogueException::new);*/

        if(movieRepository.findByTitle(movie.getTitle()).isPresent()) {
            throw new MovieAlreadyExistsInCatalogueException(movie.getTitle());
        }
        return movieRepository.save(movie);
    }
}
