package pl.ampv.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.usecase.AddMovieToCatalogue;
import pl.ampv.movie_cart.usecase.ReadMovieFromCatalogue;
import pl.ampv.movie_cart.usecase.exception.MovieAlreadyExistsInCatalogueException;

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)    //efekt: usuwa dane z repo pomiedzy testami
class AddMovieToCatalogueTest {

    @Autowired private AddMovieToCatalogue addMovieToCatalogue;
    @Autowired private ReadMovieFromCatalogue readMovieFromCatalogue;

    @Test
    void add_new_movie_to_catalogue() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setTitle("Ogniem i mieczem");

        //when
        Movie addedMovie = addMovieToCatalogue.add(movie);
        Movie readMovie = readMovieFromCatalogue.readByTitle(movie.getTitle());

        //then
        Assertions.assertThat(addedMovie).isEqualTo(readMovie);
    }
    @Test
    void cannot_add_movie_which_already_exists_in_catalogue() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setTitle("Avatar");

        //when
        addMovieToCatalogue.add(movie);

        //then
        Assertions.assertThatExceptionOfType(MovieAlreadyExistsInCatalogueException.class)
                .isThrownBy(() -> addMovieToCatalogue.add(movie));
    }



}