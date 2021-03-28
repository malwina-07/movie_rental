package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.CopyStatus;
import pl.ampv.movie_cart.repository.CopyRepository;
import pl.ampv.movie_cart.repository.OrderRepository;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;
import pl.ampv.movie_cart.usecase.port.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateCopyOfMovieFromCart {

    private final CartService cartService;
    private final CreateCopyOfAMovie createCopyOfAMovie;

    public List<Copy> listOfCopyCart() throws MovieDoesNotExistInCatalogue {

        List<Copy> copyList = new ArrayList<>();

        for(Map.Entry<Long, Integer> cartEntry : cartService.getCartEntries().entrySet()){
            Long keyMovieId = cartEntry.getKey();
            Copy copy = createCopyOfAMovie.create(keyMovieId);

            Integer valueMovieCopies = cartEntry.getValue();
            copy.setAmount(valueMovieCopies);

            copy.setCopyStatus(CopyStatus.RENTED);
            copyList.add(copy);
        }

        return copyList;

    }
}
