package beans;


import lombok.Builder;


@Builder
public class Item {
    private String name;
    private int price_for_quantity;
    private String quantity_unit;
    private int warranty;
}
