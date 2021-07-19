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
import pl.ampv.registration.model.User;
import pl.ampv.registration.service.UserService;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.util.List;

@Slf4j
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

//        set the total price after return
//        Double calcTotalPrice = priceCalcService.calculateTotalPrice(order.getOrderId());
//        order.setTotalPrice(calcTotalPrice);

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

    @GetMapping("/movie/order/accept")
    public String orderAccepted() {

        //TODO: zamknąć sesje koszyka

        log.info("Success! You completed your order.");
        return "redirect:/movie/catalogue";
    }



//    @PostMapping("/movie/order/data")
//    public String fillOrderWithData(@Valid @ModelAttribute OrderDto orderDto, Errors errors) throws MovieDoesNotExistInCatalogue {
//        if (errors.hasErrors()) {
//            log.error("Error occurred in front: " + errors.getFieldError());
//            return "order_page_first";
//        }
//        Order order = new Order();
//
//        order.setRentedDate(orderDto.getRentedDate());
//        order.setReturnDate(orderDto.getReturnDate());
//
//        List<Copy> movieCopyCart = createCopyOfMovieFromCart.listOfCopyCart();
//        order.setCopies(movieCopyCart);
//
//        Double calcTotalPrice = priceCalcService.calculateTotalPrice(order.getOrderId());
//        order.setTotalPrice(calcTotalPrice);
//
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long idLoggedUser = principal.getId();
//        order.setUser(userService.findById(idLoggedUser));
//
//        orderService.save(order);
//        log.info("Your order has been successfully refilled");
//
//        return "order_page_second";
//    }

//    @GetMapping("/movie/order/{orderId}")
//    public String showOrder(@PathVariable Long orderId, Model model) {
//
//
//        Order order = orderService.findByOrderId(orderId);
//
//        Double calcTotalPrice = priceCalcService.calculateTotalPrice(order.getOrderId());
//        order.setTotalPrice(calcTotalPrice);
//
//        model.addAttribute("order", order);
//
//        return "order_page_second";
//    }
//


}


