package pl.ampv.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.usecase.AddMovieToCatalogue;
import pl.ampv.movie_cart.usecase.UpdateScoreInMovie;
import pl.ampv.movie_cart.usecase.CreateCopyOfAMovie;
import pl.ampv.movie_cart.usecase.exception.MovieAlreadyExistsInCatalogueException;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;

@SpringBootTest
public class RateTheMovieTest {
    @Autowired
    private CreateCopyOfAMovie createCopyOfAMovie;
    @Autowired
    private UpdateScoreInMovie addNewScoreToMovie;
    @Autowired
    private AddMovieToCatalogue addMovieToCatalogue;

    @Test
    public void update_movie_score_after_rate() throws MovieDoesNotExistInCatalogue, MovieAlreadyExistsInCatalogueException {


        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setTitle("titanic");
        movie.setScore(7.9);

//------------------------------------------------------------
        Review review = new Review();
        review.setMovie(movie);
        review.setScore(8.8);

        Review review2 = new Review();
        review2.setMovie(movie);
        review2.setScore(8.0);

        Review review3 = new Review();
        review3.setMovie(movie);
        review3.setScore(7.8);

        addNewScoreToMovie.updateScore(1l);

        Double expected = 8.20;

        Assertions.assertThat(movie.getScore()).isEqualTo(expected);


//        getMovieAndChangeTheAvgScore();
//        findMovieByMovieId -> getAllReview().sum() ilosc głosów lub findAllReviewForMovieMovieId -> getScore().sum() suma ocen
//        Double sumaOcen = movie.getReview().stream().filter(r -> r.getScore()).mapToDouble().sum() //sume wszysykich ocen danego filmu
//        Double sumaUserów = movie.getReview().stream().filter(r -> r.getUser()).mapToLong().sum()   // suma wszyskich użytkowników
//        movie.setScore(sumaOcen/sumaUserów);


    }
}
