package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.repository.ReviewRepository;
import pl.ampv.movie_cart.usecase.exception.UserAlreadyRatedThisMovieException;
import pl.ampv.registration.model.User;

@Component
@RequiredArgsConstructor
public class AddReview {

    private final ReviewRepository reviewRepository;
    private final UpdateScoreInMovie updateScoreInMovie;

    public Review add(Review review) throws UserAlreadyRatedThisMovieException{
//sprawdzenie czy użytkownik nie dodal juz oceny do tego fimlu
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Movie movie = review.getMovie();
//
//        if (reviewRepository.findByMovieMovieIdAndUserId(movie.getMovieId(),principal.getId()).isPresent()) {
//            throw new UserAlreadyRatedThisMovieException(movie.getTitle());
//        }

        Review savedReview = reviewRepository.save(review);
        updateScoreInMovie.updateScore(review.getMovie().getMovieId());

        return savedReview;
    }


}
