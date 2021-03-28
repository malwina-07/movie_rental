package pl.ampv.movie_cart.model;

public enum PriceType {
    PREMIERE(10.0*3),
    NEWNESS(10.0*2),
    STANDARD(10.0),
    CLASSIC(10.0*0.5);

//    private static final Double basePrice = 10.0;

    private final Double dayPrice;

    PriceType(Double price) {
        this.dayPrice = price;
    }

    public Double getDayPrice() {
        return dayPrice;
    }
}