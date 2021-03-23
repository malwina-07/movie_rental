package pl.ampv.movie_cart.usecase.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieAlreadyExistsInCatalogueException extends Exception{

    public MovieAlreadyExistsInCatalogueException(String title) {
        super(title + " already exists in movies catalogue!");
    }
}
