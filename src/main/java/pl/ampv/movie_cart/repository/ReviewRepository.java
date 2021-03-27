package pl.ampv.movie_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.registration.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovieMovieId(Long movieId);

//    @Query("select Review from Review r WHERE r.movie= :movieId AND r.user= :userId")
    Optional<Review> findByMovieMovieIdAndUserId(Long movieId, Long userId);

    //sprawdzic tą metodę
    Optional<Review> findByMovieInAndUserIn(Collection<Movie> movie, Collection<User> user);

    List<Review> findByUserId(Long userId);
}
