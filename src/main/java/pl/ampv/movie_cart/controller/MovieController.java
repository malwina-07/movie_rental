package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.service.MovieService;
import pl.ampv.movie_cart.service.ReviewService;
import pl.ampv.registration.model.User;
import pl.ampv.registration.repository.UserRepository;
import pl.ampv.registration.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;
    private final ReviewService reviewService;


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

    @GetMapping("/movie/rate/{movieId}")
    public String rateAMovie(@PathVariable Long movieId, Model model) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idLoggedUser = principal.getId();

        Movie movieById = movieService.getById(movieId);

        model.addAttribute("movie", movieById);

        Review review = new Review();
        review.setMovie(movieById);
        review.setUser(userService.findById(idLoggedUser));

        model.addAttribute("rates", review);
        return "movie_rate";
    }

    @PostMapping("/movie/rate/save")
    public String saveRate(@Valid @ModelAttribute Review review, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Error occurred in front: " + errors.getFieldError());
            return "movie_rate";
        }
        reviewService.save(review);
        log.info("Success! New review has been save.");
        return "redirect:movie/catalogue";
    }


}
