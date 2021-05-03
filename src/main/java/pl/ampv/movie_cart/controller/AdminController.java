package pl.ampv.movie_cart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.service.MovieService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MovieService movieService;

    public AdminController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/add")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "add_movie";
    }

    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute Movie movie, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Error occurred in front: " + errors.getFieldError());
            return "add_movie";
        }
        movieService.save(movie);
        log.info("Added movie to catalogue: " + movie);
        return "redirect:/movie/catalogue";
    }

    @GetMapping("/delete/{movieId}")
    public String deleteMovie(@PathVariable Long movieId) {
        movieService.delete(movieId);

        return "redirect:/movie/catalogue";
    }


}
