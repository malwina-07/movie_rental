package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.CopyRepository;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateScoreInMovie {

    private final GetAverageScore getAverageScore;
    private final MovieRepository movieRepository;



    public Movie updateScore(Long movieId) {
        Double newScore = getAverageScore.averageScore(movieId);

        Movie movie = movieRepository.findById(movieId).get();
        movie.setScore(newScore);

        return movieRepository.save(movie);
    }
}
