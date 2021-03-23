package pl.ampv.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.usecase.AddMovieToCart;
import pl.ampv.movie_cart.usecase.AddMovieToCatalogue;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;

import java.util.Map;

@SpringBootTest
class AddMovieToCartTest {


    @Autowired private AddMovieToCatalogue addMovieToCatalogue;
    @Autowired private AddMovieToCart addMovieToCart;

    @Test
    void adds_movie_to_cart() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setTitle("Ogniem i mieczem");
        movie = addMovieToCatalogue.add(movie);

        //when
        Map<Movie, Integer> moviesInCart = addMovieToCart.add(movie.getMovieId());

        //then
        Assertions.assertThat(moviesInCart).isNotEmpty();
        Assertions.assertThat(moviesInCart).hasSize(1);

        Map.Entry<Movie, Integer> firstElement = moviesInCart.entrySet().stream().findFirst().get();

        Assertions.assertThat(firstElement.getKey()).isEqualTo(movie);
        Assertions.assertThat(firstElement.getValue()).isEqualTo(1);
    }
    @Test
    void adds_second_copy_of_a_movie_to_cart() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setTitle("Avatar");
        movie = addMovieToCatalogue.add(movie);

        //when
        addMovieToCart.add(movie.getMovieId());
        Map<Movie, Integer> moviesInCart = addMovieToCart.add(movie.getMovieId());

        //then
        Assertions.assertThat(moviesInCart).isNotEmpty();
        Assertions.assertThat(moviesInCart).hasSize(1);
        Map.Entry<Movie, Integer> firstElement = moviesInCart.entrySet().stream().findFirst().get();
        Assertions.assertThat(firstElement.getKey()).isEqualTo(movie);
        Assertions.assertThat(firstElement.getValue()).isEqualTo(2);
    }

    @Test
    void adds_another_movie_while_cart_is_not_empty() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setTitle("Incepcja");
        movie = addMovieToCatalogue.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Matrix");
        movie2 = addMovieToCatalogue.add(movie2);

        //when
        addMovieToCart.add(movie.getMovieId());
        Map<Movie, Integer> moviesInCart = addMovieToCart.add(movie2.getMovieId());

        //then
        Assertions.assertThat(moviesInCart).isNotEmpty();
        Assertions.assertThat(moviesInCart).hasSize(2);

        Assertions.assertThat(moviesInCart.containsKey(movie)).isTrue();
        Assertions.assertThat(moviesInCart.containsKey(movie2)).isTrue();

    }
    @Test
    void cannot_add_non_existing_movie_to_cart() {
        //given

        //when

        //then
        Assertions.assertThatExceptionOfType(MovieDoesNotExistInCatalogue.class)
                .isThrownBy(() -> addMovieToCart.add(999L));
    }
}