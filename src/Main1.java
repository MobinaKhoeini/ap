import java.util.ArrayList;

public class Main1 {
    public static void main(String[] args) {
        Pen pen1 = new Pen("red", 25_000, "zebra");
        Pen pen2 = new Pen("blue", 30_000, "kian");
        Book book1 = new Book("Pride", 500_000);
        Book book2 = new Book("Nahal", 800_000);
        ArrayList<Pen> pens = new ArrayList<Pen>();
        ArrayList<Book> books = new ArrayList<Book>();
        pens.add(pen1);
        pens.add(pen2);
        books.add(book1);
        books.add(book2);
        for (Pen pen: pens) {
            System.out.println("color:" + pen.getColor() + "price:" + pen.getPrice() + "brand:" + pen.getBrand());
        }
        for (Book book: books) {
            System.out.println(book);
            System.out.println("name:" + book.getName() + "price:" + book.getPrice());
        }
    }
}
