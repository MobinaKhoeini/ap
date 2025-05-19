package midproject;

public class Book {
    private String name;
    private String author;
    private int pageNumber;
    private int publicationYear;
    private boolean available;
    public Book(String name, String author, int pageNumber, int publicationYear) {
        this.name = name;
        this.author = author;
        this.pageNumber = pageNumber;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() { return available; }

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
