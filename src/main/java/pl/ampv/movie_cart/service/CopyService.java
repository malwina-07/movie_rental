package pl.ampv.movie_cart.service;


import pl.ampv.movie_cart.model.Copy;

public interface CopyService {

    Copy getCopyByMovieId(Long movieId);



}
