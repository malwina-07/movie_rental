package pl.ampv.movie_cart.model;

public enum PriceType {
    PREMIERE(2.0 * 3),
    NEWNESS(2.0 * 2),
    STANDARD(2.0),
    CLASSIC(2.0 * 0.5);

    private final Double dayPrice;

    PriceType(Double price) {
        this.dayPrice = price;
    }

    public Double getDayPrice() {
        return dayPrice;
    }
}