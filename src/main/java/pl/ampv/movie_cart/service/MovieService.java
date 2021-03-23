package pl.ampv.movie_cart.service;


import pl.ampv.movie_cart.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    Movie getById(Long id);

    List<Movie> getAllSortedByTitle();
    List<Movie> getAllSortedByTitleDesc();
    List<Movie> getAllSortedByAvgDesc();
    List<Movie> getAllSortedByPremiere();

    List<Movie> findByKeyword(String keyword);
//
//    List<Movie> getBestScoreMovie();

    void save(Movie movie);

}
