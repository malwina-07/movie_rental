package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ampv.movie_cart.service.MovieService;
import pl.ampv.movie_cart.service.ReviewService;
import pl.ampv.registration.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @GetMapping("/movie/details/{movieId}")
    public String movieDetail(@PathVariable Long movieId, Model model) {
        model.addAttribute("movie", movieService.getById(movieId));
        return "movie_detail";
    }

    @GetMapping("/movie/catalogue")
    public String findByKeyword(String keyword, Model model) {
        if (keyword != null) {
            model.addAttribute("movies", movieService.findByKeyword(keyword));
        } else {
            model.addAttribute("movies", movieService.getAll());
        }
        return "movie_catalogue";
    }

    @GetMapping("/movie/sortBy/titleAsc")
    public String sortMovieByTitle(Model model) {
        model.addAttribute("movies", movieService.getAllSortedByTitle());
        return "movie_catalogue";
    }

    @GetMapping("/movie/sortBy/titleDesc")
    public String sortMovieByTitleDsc(Model model) {
        model.addAttribute("movies", movieService.getAllSortedByTitleDesc());
        return "movie_catalogue";
    }

    @GetMapping("/movie/sortBy/score")
    public String sortMovieByAvgScore(Model model) {
        model.addAttribute("movies", movieService.getAllSortedByAvgDesc());
        return "movie_catalogue";
    }

    @GetMapping("/movie/sortBy/premiere")
    public String sortMovieByPremiere(Model model) {
        model.addAttribute("movies", movieService.getAllSortedByPremiere());
        return "movie_catalogue";
    }




}
