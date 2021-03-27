package pl.ampv.movie_cart.service;

import pl.ampv.movie_cart.model.Review;

import java.util.List;

public interface ReviewService {

    void save(Review review);

    List<Review> findByUserId(Long userId);
}
