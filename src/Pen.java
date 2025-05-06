public class Pen {
    private String color;
    private long price;
    private String brand;
    public Pen(String color, long price, String brand) {
        this.color = color;
        this.price = price;
        this.brand = brand;
    }
    public String getColor() {
        return color;
    }
    public String getBrand() {
        return brand;
    }
    public Long getPrice() {
        return price;
    }
}
