package pl.ampv.movie_cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ampv.movie_cart.model.Order;
import pl.ampv.movie_cart.repository.OrderRepository;
import pl.ampv.movie_cart.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
       return orderRepository.save(order);
    }


    @Override
    public List<Order> findByUserId(Long userId) {
        List<Order> userOrders = orderRepository.findByUserId(userId);
        if(userOrders.isEmpty()){
            return new ArrayList<>();
        }
        return userOrders;
    }

    @Override
    public Order findByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
