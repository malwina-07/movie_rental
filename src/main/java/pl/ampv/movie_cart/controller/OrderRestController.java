package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ampv.movie_cart.dto.OrderDto;
import pl.ampv.movie_cart.model.Order;
import pl.ampv.movie_cart.model.OrderStatus;
import pl.ampv.movie_cart.repository.OrderRepository;
import pl.ampv.movie_cart.usecase.exception.OrderNotFoundException;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders", produces = "application/json")
public class OrderRestController {

    private final OrderRepository orderRepository;


    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {

        return orderRepository.findById(orderId).map(OrderDto::map).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @PostMapping("/{orderId}")
    public OrderDto setOrderStatus(@PathVariable Long orderId, @RequestParam(name = "status") String orderStatus){
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        var order = orderRepository.getOne(orderId);
        order.setStatus(status); //event
        orderRepository.save(order);
        return OrderDto.map(order);
    }

}
