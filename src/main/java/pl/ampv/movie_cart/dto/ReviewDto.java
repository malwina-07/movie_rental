package pl.ampv.movie_cart.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private Long movieId;
    private Double score;
    private String comment;

}
