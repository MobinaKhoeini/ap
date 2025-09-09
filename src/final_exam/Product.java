package final_exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    List<Product> products = new ArrayList<>();
    private String name;
    private long price;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                " name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
