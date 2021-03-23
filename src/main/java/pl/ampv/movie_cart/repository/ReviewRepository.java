package pl.ampv.movie_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ampv.movie_cart.model.Review;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query("select Review from Review r WHERE r.movie= :movieId AND r.user= :userId")
    Optional<Review> findByMovieMovieIdAndUserId(Long movieId, Long userId);

}
