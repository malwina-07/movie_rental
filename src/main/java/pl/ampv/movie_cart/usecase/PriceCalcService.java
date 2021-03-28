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

@Component
@RequiredArgsConstructor
public class PriceCalcService {

    private final OrderRepository orderRepository;

    public Double calculateTotalPrice(Long orderId) {
        Order order = orderRepository.getOne(orderId);

        double totalPrice = 0.0;
        int daysRented = (int) ChronoUnit.DAYS.between(order.getReturnDate(), order.getRentedDate());
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

        if (premiereDate.isBefore(twoWeeks)) {
            return PriceType.PREMIERE;
        } else if (premiereDate.isAfter(twoWeeks) & premiereDate.isBefore(treeMonths)) {
            return PriceType.NEWNESS;
        } else if (premiereDate.isAfter(treeMonths) & premiereDate.isBefore(oneYear)) {
            return PriceType.STANDARD;
        } else {
            return PriceType.CLASSIC;
        }
    }


}
