package pl.ampv.movie_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ampv.movie_cart.model.Copy;


@Repository
public interface CopyRepository extends JpaRepository<Copy,Long> {

    @Query("select c from Copy c where c.movie = ?1")
    Copy findMovieCopyByMovieId(Long movieId);
}
