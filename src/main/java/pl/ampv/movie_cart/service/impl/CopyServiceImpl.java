package pl.ampv.movie_cart.service.impl;

import org.springframework.stereotype.Service;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.repository.CopyRepository;
import pl.ampv.movie_cart.service.CopyService;


@Service
public class CopyServiceImpl implements CopyService {

    private final CopyRepository copyRepository;

    public CopyServiceImpl(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @Override
    public Copy getCopyByMovieId(Long movieId) {
       return copyRepository.findMovieCopyByMovieId(movieId);
    }
}

