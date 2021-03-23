package pl.ampv.movie_cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ampv.registration.model.User;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "fk_movie")
    private Movie movie;

    @Size(min = 1, max = 10, message = "Score form 1 to 10")
    private String score;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "fk_review")
    private User user;


}
