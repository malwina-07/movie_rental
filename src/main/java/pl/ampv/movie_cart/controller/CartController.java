package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.usecase.port.CartService;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart/add/{movieId}")
    public String addMovieToCart(@PathVariable Long movieId, Model model){
        model.addAttribute("movie", cartService.add(movieId));
        return "redirect:/movie/catalogue";
    }


    @GetMapping("/cart")
    public String showCart(Model model){
        model.addAttribute("movies", cartService.getCartEntries());
        return "cart";
    }

//    @PostMapping("/cart")
//    public String showMovieInCart(Model model){
//        model.addAttribute("movies", cartService.getCartEntries());
//        return "cart";
//    }

//    @ModelAttribute("movies")
//    public Map<Long,Integer> movies(){
//        Map<Long, Integer> cartEntries = cartService.getCartEntries();
//
//        movieRepository.findById(cartEntries.get());
//    }





//
//    @GetMapping("/cart")
//    public String viewCardPage(){
//        cartService.getCartEntries();
//        return "cart";
//    }
//
//    @GetMapping("/add/{movieId}")
//    public String addMovieToCard(@PathVariable Long movieId, Model model) throws MovieDoesNotExistInCatalogue {
//        model.addAttribute("movie", movieService.getById(movieId));
//        cartService.add(movieId);
//        movieService.deleteCopyFromMovieById(movieId);
//        return "cart";
//    }
//
//    @PostMapping("/cart")
//    public String viewCard(Model model){
//        model.addAttribute("cart", cartService.getCartEntries());
//        return "cart";
//    }



}
