package pl.ampv.movie_cart.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.adapter.SessionCartService;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Genre;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;
import pl.ampv.movie_cart.usecase.port.CartService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateCopyOfMovieFromCartTest {

    @Autowired CreateCopyOfMovieFromCart createCopyOfMovieFromCart;
    @Autowired CartService sessionCartService;

    @Test
    public void convert_cart_to_copy_list_test() throws MovieDoesNotExistInCatalogue {
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setScore(9.0);
        movie.setPremiere(LocalDate.of(2020,5,15));
        movie.setGenre(Genre.ADVENTURE);
        movie.setTitle("Adventure1");

        Movie movie2 = new Movie();
        movie2.setMovieId(2L);
        movie2.setScore(9.0);
        movie2.setPremiere(LocalDate.of(2020,5,15));
        movie2.setGenre(Genre.ADVENTURE);
        movie2.setTitle("Adventure2");

        Movie movie3 = new Movie();
        movie3.setMovieId(3L);
        movie3.setScore(9.0);
        movie3.setPremiere(LocalDate.of(2020,5,15));
        movie3.setGenre(Genre.ADVENTURE);
        movie3.setTitle("Adventure3");


        sessionCartService.add(movie.getMovieId());
        sessionCartService.add(movie2.getMovieId());
        sessionCartService.add(movie3.getMovieId());


        List<Copy> listOfCopyCart = createCopyOfMovieFromCart.listOfCopyCart();

        Assertions.assertThat(listOfCopyCart).hasSize(3);
    }


}