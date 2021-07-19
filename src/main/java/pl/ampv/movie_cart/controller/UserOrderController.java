package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.ampv.movie_cart.model.Order;
import pl.ampv.movie_cart.model.OrderStatus;
import pl.ampv.movie_cart.service.OrderService;
import pl.ampv.movie_cart.usecase.PriceCalcService;

import java.time.LocalDate;

/**
 * Created by @author malwina.k on 19.07.2021
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserOrderController {
    private final PriceCalcService priceCalcService;
    private final OrderService orderService;


    @GetMapping("/user/order/{orderId}")
    public String getUserSingleOrder(@PathVariable Long orderId, Model model) {

        model.addAttribute("order", orderService.findByOrderId(orderId));


        Order byOrderId = orderService.findByOrderId(orderId);
        model.addAttribute("copies", byOrderId.getCopies());


        return "single_order";
    }



    @GetMapping("/user/order/return/{orderId}")
    public String returnOrder(@PathVariable Long orderId, Model model) {
        Order order = orderService.findByOrderId(orderId);
        model.addAttribute("returned", order);
        return "single_order_return";
    }


    @PostMapping(value = "/user/order/return/{orderId}")
    public String returnOrder(@PathVariable Long orderId) {

        Order orderUpdate = orderService.findByOrderId(orderId);

        orderUpdate.setStatus(OrderStatus.RETURNED);

        orderUpdate.setReturnDate(LocalDate.now());
        Double calculateTotalPrice = priceCalcService.calculateTotalPrice(orderId);
        orderUpdate.setTotalPrice(calculateTotalPrice);

        orderService.save(orderUpdate);

        return "redirect:/user/order/return/" + orderUpdate.getOrderId();
    }
}
