package pl.ampv.registration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MovieService movieService;

    @GetMapping("")
    public String homePage(){
        return "main_page";
    }

    @GetMapping("/home")
    public String handleGetHome(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", username);
        model.addAttribute("movies", movieService.getAllSortedByAvgDesc().stream().limit(10).collect(Collectors.toList()));

        return "home_page";
    }

}
