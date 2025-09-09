package final_exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static final_exam.ProductTools.PenByColor;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<Pen> pens = new ArrayList<>();
        pens.add(new Pen("pen",20_000,Pen.Color.BLUE));
        pens.add(new Pen("pen",15_000,Pen.Color.RED));
        pens.add(new Pen("pen",29_000,Pen.Color.BLUE));
        pens.add(new Pen("pen", 18_000,Pen.Color.BLACK));
        products.add(new Product("tv", 30_000_000));
        products.add(new Book("Book", 4_500, "harry potter", "Jane Austin"));
        products.add(new Book("Book", 12_000, "harry potter", "JK"));
        products.add(new Pen("pen", 12_000,Pen.Color.BLACK));
        products.add(new Pen("pen", 12_000,Pen.Color.BLACK));
        ProductTools.printProductInfo(products);

        Map<String, Long> finalResult = PenByColor(pens);
        System.out.println(finalResult);

    }
}

