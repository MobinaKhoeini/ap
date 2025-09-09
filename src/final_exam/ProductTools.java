package final_exam;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class ProductTools {

    public static void printProductInfo(List<Product> products) {
        products.stream().
                distinct().sorted(Comparator.comparing(Product::getName).thenComparing(Product::getPrice))
                .forEach(System.out::println);
    }
    public static Map<String, Long> PenByColor(List<Pen> pens) {
        return pens.stream().collect(Collectors.
                groupingBy(pen -> pen.getColor().name(), Collectors.counting()));
    }
}
