package midterm_exam;

public class Case {
    private int price;
    private String color;
    private boolean isStock;
    private String size;
    public Case(int price, String color, boolean isStock, String size) {
        this.price = price;
        this.color = color;
        this.isStock = isStock;
        this.size = size;
    }

//    public int getPrice() {
//        return price;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public boolean isStock() {
//        return isStock;
//    }
//
//    public String getSize() {
//        return size;
//    }

    @Override
    public String toString() {
        return "Case{" +
                "price=" + price +
                ", color='" + color + '\'' +
                ", isStock=" + isStock +
                ", size='" + size + '\'' +
                '}';
    }
}
