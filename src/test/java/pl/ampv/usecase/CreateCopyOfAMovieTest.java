package pl.ampv.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.usecase.AddMovieToCatalogue;
import pl.ampv.movie_cart.usecase.CreateCopyOfAMovie;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;

@SpringBootTest
class CreateCopyOfAMovieTest {

    @Autowired
    private AddMovieToCatalogue addMovieToCatalogue;
    @Autowired private CreateCopyOfAMovie createCopyOfAMovie;

    @Test
    void creates_copy_of_a_given_movie() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setTitle("Ogniem i mieczem");
        movie = addMovieToCatalogue.add(movie);

        //when
        Copy copy = createCopyOfAMovie.create(movie.getMovieId());

        //then
        Assertions.assertThat(copy.getMovie()).isEqualTo(movie);    //mozliwe dzieki prawidlowej implementacji equals()
        //Assertions.assertThat(copy.getMovie().getId()).isEqualTo(movie.getId());
        //Assertions.assertThat(copy.getMovie().getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    void cannot_create_a_copy_of_non_existing_movie() {
        //given

        //when

        //then
        Assertions.assertThatExceptionOfType(MovieDoesNotExistInCatalogue.class)
                .isThrownBy(() -> createCopyOfAMovie.create(999L));
    }


}