package pl.ampv.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.CopyStatus;
import pl.ampv.movie_cart.model.Genre;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.CopyRepository;
import pl.ampv.movie_cart.repository.MovieRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class MovieTest {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Test
    public void saves_movie_and_related_copies(){

        String title = "Titanic";

        //given
        Movie movie1 = new Movie();
        movie1.setTitle(title);
        movie1.setGenre(Genre.HORROR);
        movie1.setPremiere(LocalDate.of(1999,2,2));

        Copy copy1 = new Copy();
        copy1.setMovie(movie1);
        copy1.setCopyStatus(CopyStatus.AVAILABLE);


        Copy copy2 = new Copy();
        copy2.setMovie(movie1);
        copy2.setCopyStatus(CopyStatus.RENTED);

        List<Copy> copies = new ArrayList<>();
        copies.add(copy1);
        copies.add(copy2);

        //add copies list to movie
        movie1.setCopies(copies);

        Optional<Movie> foundMovieOptional = movieRepository.findByTitle(title);

        Assertions.assertThat(foundMovieOptional.isEmpty()).isTrue();

        movieRepository.save(movie1);
        foundMovieOptional = movieRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isPresent()).isTrue();
        Movie foundMovie = foundMovieOptional.get();

        List<Copy> foundCopies = copyRepository.findAll();

        //then
        Assertions.assertThat(foundMovie.getTitle()).isEqualTo(movie1.getTitle());
        Assertions.assertThat(foundMovie.getPremiere()).isEqualTo(movie1.getPremiere());

        Assertions.assertThat(foundCopies.isEmpty()).isFalse();


    }
}