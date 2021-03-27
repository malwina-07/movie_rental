package pl.ampv.movie_cart.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.repository.ReviewRepository;

@SpringBootTest
class UpdateScoreInMovieTest {
    @Autowired private UpdateScoreInMovie updateScoreInMovie;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private GetAverageScore getAverageScore;
    @Autowired private MovieRepository movieRepository;

    @Test
    public void checkIfMovieHasNowScoreTest(){
        Movie movie = movieRepository.findById(5l).get();

        Review review = new Review();
        review.setMovie(movie);
        review.setScore(8.8);

        Review review2 = new Review();
        review2.setMovie(movie);
        review2.setScore(8.0);

        Review review3 = new Review();
        review3.setMovie(movie);
        review3.setScore(7.8);

        reviewRepository.save(review);
        reviewRepository.save(review2);
        reviewRepository.save(review3);

        Double expected = getAverageScore.averageScore(movie.getMovieId());

        //when
        Movie update = updateScoreInMovie.updateScore(movie.getMovieId());
        //then

        Assertions.assertThat(movieRepository).isNotNull();

        Assertions.assertThat(update).isEqualTo(movie);

        Assertions.assertThat(update.getScore()).isEqualTo(expected);
    }

}