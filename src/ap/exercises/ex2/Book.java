package ap.exercises.ex2;

public class Book {
    public String name;
    public String author;
    public int pageNumber;
    public int publicationYear;
    public Book(String name, String author, int pageNumber, int publicationYear) {
        this.name = name;
        this.author = author;
        this.pageNumber = pageNumber;
        this.publicationYear = publicationYear;
    }
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pageNumber=" + pageNumber +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
