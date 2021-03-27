package pl.ampv.movie_cart.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.ReviewRepository;
import pl.ampv.movie_cart.usecase.exception.UserAlreadyRatedThisMovieException;
import pl.ampv.registration.model.User;
@SpringBootTest
class AddReviewTest {

    @Autowired private  AddReview addReview;
    @Autowired private ReviewRepository reviewRepository;


    @Test
    public void add_review_to_repository_Test() throws UserAlreadyRatedThisMovieException {
        User user = new User();
        user.setId(2L);
        user.setFirstName("Malwina");
        user.setLastName("Kosowska");
        user.setEmail("mk@mail.com");

        Movie movie = new Movie();
        movie.setMovieId(3L);
        movie.setTitle("Titanic");

        Review review = new Review();
        review.setReviewId(1l);
        review.setUser(user);
        review.setMovie(movie);
        review.setScore(7.0);
        review.setComment("It was good movie.");

        addReview.add(review);

        Assertions.assertThat(reviewRepository).isNotNull();
    }
//    @Test
//    void cannot_add_more_then_1_review_to_movie() throws UserAlreadyRatedThisMovieException {
//        User user = new User();
//        user.setId(2L);
//        user.setFirstName("Malwina");
//        user.setLastName("Kosowska");
//        user.setEmail("mk@mail.com");
//
//        Movie movie = new Movie();
//        movie.setMovieId(3L);
//        movie.setTitle("Titanic");
//
//        Review review = new Review();
//        review.setReviewId(1l);
//        review.setUser(user);
//        review.setMovie(movie);
//        review.setScore(7.0);
//        review.setComment("It was good movie.");
//
//        addReview.add(review);
//
//        //then
//        Assertions.assertThatExceptionOfType(UserAlreadyRatedThisMovieException.class)
//                .isThrownBy(() -> addReview.add(review));
//    }

}