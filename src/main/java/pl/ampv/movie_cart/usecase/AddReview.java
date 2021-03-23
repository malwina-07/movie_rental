package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.ReviewRepository;
import pl.ampv.movie_cart.usecase.exception.UserAlreadyRatedThisMovieException;

@Component
@RequiredArgsConstructor
public class AddReview {

    private final ReviewRepository reviewRepository;

    public Review add(Review review) throws UserAlreadyRatedThisMovieException{

        Movie movie = review.getMovie();
        Long userId = review.getUser().getId();

        if (reviewRepository.findByMovieMovieIdAndUserId(movie.getMovieId(),userId).isPresent()) {
            throw new UserAlreadyRatedThisMovieException(movie.getTitle());
        }

        return reviewRepository.save(review);
    }

}
