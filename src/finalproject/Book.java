package finalproject;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, int publicationYear, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { isAvailable = available; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String toFileString() {
        return title + "|" + author + "|" + publicationYear + "|" + isbn + "|" + isAvailable;
    }

    public static Book fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 5) {
            return new Book(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], Boolean.parseBoolean(parts[4]));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                " | Author: " + author +
                " | Year: " + publicationYear +
                " | ISBN: " + isbn +
                " | Status: " + (isAvailable ? "Available" : "Borrowed");
    }
}