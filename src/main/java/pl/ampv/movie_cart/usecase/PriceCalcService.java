package pl.ampv.movie_cart.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.model.Order;
import pl.ampv.movie_cart.model.PriceType;
import pl.ampv.movie_cart.repository.MovieRepository;
import pl.ampv.movie_cart.repository.OrderRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceCalcService {

    private final OrderRepository orderRepository;

    public Double calculateTotalPrice(Long orderId) {
        Order order = orderRepository.getOne(orderId);

        double totalPrice = 0.0;

        int daysRented = (int) ChronoUnit.DAYS.between(order.getReturnDate(), order.getRentedDate());

        if (daysRented <= 0) {
            daysRented = 1;
        }

        for (Copy c : order.getCopies()) {
            totalPrice = Double.sum(calculateTotalPrice(c.getMovie(), daysRented), totalPrice);
        }
        order.setTotalPrice(totalPrice);
        return totalPrice;
    }

    private Double calculateTotalPrice(Movie movie, int daysRented) {
        return daysRented * calculatePriceType(movie, daysRented).getDayPrice().doubleValue();
    }

    private PriceType calculatePriceType(Movie movie, int daysRented) {
        LocalDate premiereDate = movie.getPremiere();

        LocalDate today = LocalDate.now();
        LocalDate orderDate = today.minusDays(daysRented);
        LocalDate twoWeeks = orderDate.minusDays(14);
        LocalDate treeMonths = orderDate.minusMonths(3);
        LocalDate oneYear = orderDate.minusYears(1);

        if (premiereDate.isAfter(twoWeeks)) {
            return PriceType.PREMIERE;
        } else if (premiereDate.isBefore(twoWeeks) && premiereDate.isAfter(treeMonths)) {
            return PriceType.NEWNESS;
        } else if (premiereDate.isBefore(treeMonths) && premiereDate.isAfter(oneYear)) {
            return PriceType.STANDARD;
        } else {
            return PriceType.CLASSIC;
        }
    }


    public Double calculatePriceForOneDay(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        LocalDate returnedDate = LocalDate.now().plusDays(1);

        double totalPrice = 0.0;
        int daysRented = (int) ChronoUnit.DAYS.between(order.getRentedDate(), returnedDate);
        for (Copy c : order.getCopies()) {
            totalPrice = Double.sum(calculateTotalPrice(c.getMovie(), daysRented), totalPrice);
        }
        order.setTotalPrice(totalPrice);
        return totalPrice;
    }
}
