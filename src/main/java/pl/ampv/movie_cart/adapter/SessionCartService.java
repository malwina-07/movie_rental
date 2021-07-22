package pl.ampv.movie_cart.adapter;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.ampv.movie_cart.usecase.port.CartService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService implements CartService {

    private Map<Long, Integer> cartEntries = new HashMap<>();


    @Override
    public Map<Long, Integer> add(Long movieId) {
        cartEntries.put(movieId, cartEntries.containsKey(movieId) ? cartEntries.get(movieId) + 1 : 1);
        return getCartEntries();
    }

    @Override
    public Map<Long, Integer> getCartEntries() {
        return Collections.unmodifiableMap(cartEntries);
    }

    @Override
    public void clear() {
        cartEntries.clear();
    }

    @Override
    public void removeById(Long movieId) {
        if (cartEntries.get(movieId) > 1) {
            cartEntries.computeIfPresent(movieId, (kay, val) -> val - 1);
        } else {
            cartEntries.remove(movieId, 1);
        }
    }

}
