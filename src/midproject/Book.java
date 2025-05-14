package midproject;

public class Book {
    private String name;
    private String author;
    private int pageNumber;
    private int publicationYear;
    public Book(String name, String author, int pageNumber, int publicationYear) {
        this.name = name;
        this.author = author;
        this.pageNumber = pageNumber;
        this.publicationYear = publicationYear;
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
