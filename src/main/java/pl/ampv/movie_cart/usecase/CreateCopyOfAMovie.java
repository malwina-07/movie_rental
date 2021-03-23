package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.CopyRepository;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateCopyOfAMovie {
    private final CopyRepository copyRepository;
    private final MovieRepository movieRepository;

    public Copy create(Long movieId) throws MovieDoesNotExistInCatalogue {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()) {
            Copy copy = new Copy();
            copy.setMovie(movie.get());
            return copyRepository.save(copy);
        }
        else {
            throw new MovieDoesNotExistInCatalogue();
        }
    }
}
