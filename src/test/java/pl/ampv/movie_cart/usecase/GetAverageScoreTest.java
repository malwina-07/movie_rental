package pl.ampv.movie_cart.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.ReviewRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetAverageScoreTest {

    @Autowired private ReviewRepository reviewRepository;
    @Autowired private GetAverageScore getAverageScore;

    @Test
    public void getAverageScoreForMovieTest(){
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setTitle("titanic");
        movie.setScore(7.9);

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

        Double actual = getAverageScore.averageScore(movie.getMovieId());

        Assertions.assertThat(actual).isEqualTo(8.20);

    }

}