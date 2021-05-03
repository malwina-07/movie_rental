package pl.ampv.movie_cart.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Copy {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "copy_id")
        private Long copyId;

        @ManyToOne
        @JoinColumn(name = "movie_id")
        private Movie movie;

        private Integer amount;

        private CopyStatus copyStatus;

        @ManyToOne
        @JoinColumn(name = "fk_order")
        private Order order;

}
