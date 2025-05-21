package midterm_exam;

public class Laptop {
    private int price;
    private String color;
    private String brand;
    private String memory;
    public Laptop (int price, String color, String brand, String memory) {
        this.price = price;
        this.color = color;
        this.brand = brand;
        this.memory = memory;
    }

//    public int getPrice() {
//        return price;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public String getMemory() {
//        return memory;
//    }

    @Override
    public String toString() {
        return "Laptop{" +
                "price=" + price +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
