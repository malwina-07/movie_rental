package pl.ampv.movie_cart.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "premiere")
    private LocalDate premiere;

    @Column(name = "description")
    private String description;

    @Column(name = "score")
    private Double score;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Copy> copies;

    @OneToMany(mappedBy = "movie")
    private List<Review> review;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(movieId, movie.movieId) && Objects.equals(title, movie.title) && Objects.equals(premiere, movie.premiere) && genre == movie.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, title, premiere, genre);
    }

}
