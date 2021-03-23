package pl.ampv.movie_cart.usecase.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyRatedThisMovieException extends Exception{
    public UserAlreadyRatedThisMovieException(String title){
    super(title+ " hase been rated");}
}

