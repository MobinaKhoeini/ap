package finalproject;

import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        this.books = FileManager.loadBooks();
    }

    public List<Book> searchBooks(String title, String author, Integer publicationYear) {
        return books.stream()
                .filter(book ->
                        (title == null || title.isEmpty() || book.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                                (author == null || author.isEmpty() || book.getAuthor().toLowerCase().contains(author.toLowerCase())) &&
                                (publicationYear == null || book.getPublicationYear() == publicationYear)
                )
                .collect(Collectors.toList());
    }
    public void addBook(String title, String author, int publicationYear, String isbn) {
        if (isIsbnTaken(isbn)) {
            System.out.println("This ISBN already exists. Please enter another one.");
            return;
        }

        Book newBook = new Book(title, author, publicationYear, isbn, true);
        books.add(newBook);
        FileManager.saveBooks(books);
        System.out.println("Book added successfully!");
    }

    private boolean isIsbnTaken(String isbn) {
        return books.stream().anyMatch(b -> b.getIsbn().equals(isbn));
    }
    public void displayAvailableBooks() {
        List<Book> availableBooks = books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());

        System.out.println("\n--- Available Books ---");
        if (availableBooks.isEmpty()) {
            System.out.println("No books available at the moment.");
            return;
        }

        for (Book book : availableBooks) {
            System.out.println(book);
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }
}