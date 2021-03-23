package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;
import pl.ampv.movie_cart.usecase.port.CartService;


import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddMovieToCart {

    private final CartService cartService;

    private final MovieRepository movieRepository;

    private final GetMoviesInCart getMoviesInCart;

    public Map<Movie, Integer> add(Long movieId) throws MovieDoesNotExistInCatalogue {

        Optional<Movie> movie = movieRepository.findById(movieId);

        if(movie.isPresent()) {
            cartService.add(movieId);
            return getMoviesInCart.getMovies();
        }
        else {
            throw new MovieDoesNotExistInCatalogue();
        }
    }
}
