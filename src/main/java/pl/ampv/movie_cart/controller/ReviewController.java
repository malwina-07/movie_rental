package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.ampv.movie_cart.dto.ReviewDto;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Review;
import pl.ampv.movie_cart.service.MovieService;
import pl.ampv.movie_cart.service.ReviewService;
import pl.ampv.registration.model.User;
import pl.ampv.registration.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final MovieService movieService;
    private final UserService userService;
    private final ReviewService reviewService;


    @GetMapping("/movie/rate/{movieId}")
    public String rateAMovie(@PathVariable Long movieId, Model model) {

        Movie movieById = movieService.getById(movieId);

        model.addAttribute("movie", movieById);

        model.addAttribute("rates", new Review());
        return "movie_rate";
    }

    @PostMapping("/movie/rate/save")
    public String saveRate(@Valid @ModelAttribute ReviewDto reviewDto, Errors errors) {

        if (errors.hasErrors()) {
            log.error("Error occurred in front: " + errors.getFieldError());
            return "movie_rate";
        }

        Review review = new Review();

        Movie movieById = movieService.getById(reviewDto.getMovieId());
        review.setMovie(movieById);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idLoggedUser = principal.getId();
        review.setUser(userService.findById(idLoggedUser));

        review.setScore(reviewDto.getScore());
        review.setComment(reviewDto.getComment());


        reviewService.save(review);
        log.info("Success! New review has been save.");
        return "redirect:/movie/catalogue";
    }
}
