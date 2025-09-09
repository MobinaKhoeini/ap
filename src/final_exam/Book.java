package final_exam;

public class Book extends Product{
    private String title;
    private String author;

    public Book(String name, long price, String title, String author) {
        super(name, price);
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name=" + getName() +
                ",title=" + title +
                ", author=" + author +
                ", price=" + getPrice() +
                '}';
    }
}
