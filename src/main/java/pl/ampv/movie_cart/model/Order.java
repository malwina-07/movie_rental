package pl.ampv.movie_cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.ampv.registration.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders") // @Table annotation. Using this annotation I've specified table name as order_table. In your case by default hibernate tried to generate table order. ORDER is service word in any sql.
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    private Double totalPrice;

    @OneToMany(mappedBy = "order")
    private List<Copy> copies;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

}
