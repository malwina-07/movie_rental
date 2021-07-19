package pl.ampv.movie_cart.usecase.port;

import java.util.Map;

public interface CartService {
    Map<Long, Integer> add(Long movieId);

    Map<Long, Integer> getCartEntries();

    void clear();
}
