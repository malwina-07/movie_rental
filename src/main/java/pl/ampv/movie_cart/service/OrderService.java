package pl.ampv.movie_cart.service;

import pl.ampv.movie_cart.model.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    List<Order> findByUserId(Long userId);

    Order findByOrderId(Long orderId);
}
