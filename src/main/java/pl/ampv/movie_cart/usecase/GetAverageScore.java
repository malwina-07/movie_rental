package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.ReviewRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAverageScore {

    private final ReviewRepository reviewRepository;

    public Double averageScore(Long movieId){

        List<Review> allReviewForMovie = reviewRepository.findByMovieMovieId(movieId);

        double result = allReviewForMovie.stream().mapToDouble(Review::getScore).average().orElse(0.0);

        return Precision.round(result, 2);
    }
}
