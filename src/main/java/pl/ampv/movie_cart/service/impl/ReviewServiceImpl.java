package pl.ampv.movie_cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.repository.ReviewRepository;
import pl.ampv.movie_cart.service.ReviewService;
import pl.ampv.movie_cart.usecase.AddReview;
import pl.ampv.movie_cart.usecase.exception.UserAlreadyRatedThisMovieException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final AddReview addReview;
    private final ReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        try {
            addReview.add(review);
        } catch (UserAlreadyRatedThisMovieException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
