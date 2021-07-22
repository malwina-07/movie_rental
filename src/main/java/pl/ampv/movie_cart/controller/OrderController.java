package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Order;
import pl.ampv.movie_cart.model.OrderStatus;
import pl.ampv.movie_cart.service.OrderService;
import pl.ampv.movie_cart.usecase.CreateCopyOfMovieFromCart;
import pl.ampv.movie_cart.usecase.PriceCalcService;
import pl.ampv.movie_cart.usecase.exception.MovieDoesNotExistInCatalogue;
import pl.ampv.movie_cart.usecase.port.CartService;
import pl.ampv.registration.model.User;
import pl.ampv.registration.service.UserService;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.util.List;

@Slf4j
//@RequestMapping("/movie/order")
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CreateCopyOfMovieFromCart createCopyOfMovieFromCart;
    private final PriceCalcService priceCalcService;
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping("/movie/order/")
    public String createOrder() throws MovieDoesNotExistInCatalogue {
        Order order = new Order();

        order.setRentedDate(LocalDate.now());

        List<Copy> movieCopyCart = createCopyOfMovieFromCart.listOfCopyCart();
        for (Copy c : movieCopyCart) {
            c.setOrder(order);
        }
        order.setCopies(movieCopyCart);
        order.setStatus(OrderStatus.ORDERED);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idLoggedUser = principal.getId();
        order.setUser(userService.findById(idLoggedUser));

        order = orderService.save(order);

        return "redirect:/movie/order/" + order.getOrderId();
    }

    @GetMapping("/movie/order/{orderId}")
    public String createOrder(Model model, @PathVariable Long orderId) {
        Order order = orderService.findByOrderId(orderId);
        model.addAttribute("order", order);
        // set the price for 1 day as a total price
        Double calcPriceForOneDay = priceCalcService.calculatePriceForOneDay(order.getOrderId());
        order.setTotalPrice(calcPriceForOneDay);
        orderService.save(order);
        return "order_page_second";
    }




}


