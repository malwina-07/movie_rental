package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.port.CartService;


import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetMoviesInCart {

    private final MovieRepository movieRepository;
    private final CartService cartService;


    public Map<Movie, Integer> getMovies() {
        return cartService.getCartEntries().entrySet().stream()
                .map(this::mapEntryWithLongToEntryWithMovie)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<Movie, Integer> mapEntryWithLongToEntryWithMovie(Map.Entry<Long, Integer> cartEntries) {
        return new AbstractMap.SimpleEntry<Movie, Integer>(mapLongToMovie(cartEntries.getKey()), cartEntries.getValue());
    }

    private Movie mapLongToMovie(Long movieId) {
        return movieRepository.findById(movieId).get();
    }
}