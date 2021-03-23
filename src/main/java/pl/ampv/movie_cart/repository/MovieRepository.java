package pl.ampv.movie_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ampv.movie_cart.model.Movie;


import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select * from Movie m where m.title like %:keyword% or m.premiere like %:keyword%", nativeQuery = true)
    List<Movie> findByKeyword(@Param("keyword") String keyword);

//    @Query(value = "select * from Movie m where m.genre =:genre")
//    List<Movie> findByGenre(String genre);

// no need anymore
//    List<Movie> findByScoreGreaterThan(String avgScore);

    Optional<Movie> findByTitle(String title);
}
