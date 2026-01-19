package jpahealth.jpashop.dto;

public class ItemUpdateRequest {
    private String name;
    private int price;
    private int stockQuantity;

    public String getName() {
        return name; // this.name도 가능
    }
    public int getPrice() {
        return price; // this.price도 가능
    }
    public int getStockQuantity() {
        return stockQuantity; // this.stockQuantity도 가능
    }
}