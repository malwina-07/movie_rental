package pl.ampv.movie_cart.usecase.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Long id) {
        super(id + " does not exist in catalogue!");
    }
}
