package pl.ampv.movie_cart.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.service.MovieService;
import pl.ampv.movie_cart.usecase.AddMovieToCatalogue;
import pl.ampv.movie_cart.usecase.exception.MovieAlreadyExistsInCatalogueException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final AddMovieToCatalogue catalogue;


    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> getAllSortedByTitle() {
        return movieRepository.findAll(Sort.by("title"));
    }

    @Override
    public List<Movie> getAllSortedByTitleDesc() {
        return movieRepository.findAll(Sort.by("title").descending());
    }

    @Override
    public List<Movie> getAllSortedByAvgDesc() {
        return movieRepository.findAll(Sort.by("score").descending());
    }

    @Override
    public List<Movie> getAllSortedByPremiere() {
        return movieRepository.findAll(Sort.by("premiere").descending());
    }

    @Override
    public List<Movie> findByKeyword(String keyword) {
        return movieRepository.findByKeyword(keyword);
    }

    @Override
    public void save(Movie movie) {
        try {
            catalogue.add(movie);
        } catch (MovieAlreadyExistsInCatalogueException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    // from another method
//    @Override
//    public Movie addCopiesToMovieById(Long movieId, Integer amountOfCopy) {
//        Movie movie = movieRepository.findById(movieId).orElse(null);
//        if(movie == null){
//            throw new NullPointerException("Movie doesnt exists");
//        }
//        movie.addCopies(amountOfCopy);
//
//        return movie;
//    }
//    @Override
//    public boolean deleteCopyFromMovieById(Long movieId){
//        Movie movie = movieRepository.findById(movieId).orElse(null);
//        if(movie == null){
//            throw new NullPointerException("Movie doesnt exists");
//        }
//        if(movie.getAmountCopies() <= 0){
//            log.warn("No copies to delete");
//            return false;
//        }
//        movie.deleteCopy();
//        return true;
//    }


}
