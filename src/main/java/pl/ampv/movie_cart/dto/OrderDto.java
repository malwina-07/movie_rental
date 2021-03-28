package pl.ampv.movie_cart.dto;

import lombok.Data;
import pl.ampv.movie_cart.model.Order;
import pl.ampv.movie_cart.model.OrderStatus;

@Data
public class OrderDto {

  private OrderStatus status;
  private Long orderId;

  public static OrderDto map(Order order) {
      OrderDto orderDto = new OrderDto();
      orderDto.setStatus(order.getStatus());
      orderDto.setOrderId(order.getOrderId());
      return orderDto;
  }
}
