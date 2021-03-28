//package pl.ampv.movie_cart.usecase;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.ampv.movie_cart.model.Copy;
//import pl.ampv.movie_cart.model.Genre;
//import pl.ampv.movie_cart.model.Movie;
//import pl.ampv.movie_cart.repository.CopyRepository;
//import pl.ampv.movie_cart.repository.MovieRepository;
//import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class CreateCopyOfAMovieTest {
//
//    @Autowired
//    CreateCopyOfAMovie createCopyOfAMovie;
//    @Autowired
//    MovieRepository movieRepository;
//    @Autowired
//    CopyRepository copyRepository;
//
//    @Test
//    public void create_copy_of_movie_from_repo_and_save_to_copy_repo() throws MovieDoesNotExistInCatalogue {
//        Movie movie = new Movie();
//        movie.setMovieId(1L);
//        movie.setScore(9.0);
//        movie.setPremiere(LocalDate.of(2020, 5, 15));
//        movie.setGenre(Genre.ADVENTURE);
//        movie.setTitle("Adventure1");
//        movie.setCopies(null);
//        movieRepository.save(movie);
//
//        Copy copy = createCopyOfAMovie.create(movie.getMovieId());
//
//        Copy expected = copyRepository.findById(copy.getCopyId()).get();
//
//        Assertions.assertThat(copy).isEqualTo(expected);
//
//
//    }
//
//}