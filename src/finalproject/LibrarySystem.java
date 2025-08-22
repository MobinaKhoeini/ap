package finalproject;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private StudentManager studentManager;
    private BookManager bookManager;
    private MenuHandler menuHandler;
    private Scanner scanner;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookManager = new BookManager();
        this.menuHandler = new MenuHandler(this);
        this.scanner = new Scanner(System.in);
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public void editStudentInformation(Student student) {
        System.out.println("Not implemented.");
    }

    public void borrowBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void returnBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void displayAvailableBooks() {
        bookManager.displayAvailableBooks();
    }

    public void searchBooks() {
        System.out.println("\n--- Search Books ---");

        System.out.print("Title (press Enter to skip): ");
        String title = scanner.nextLine();

        System.out.print("Author (press Enter to skip): ");
        String author = scanner.nextLine();

        System.out.print("Publication Year (press Enter to skip): ");
        String yearInput = scanner.nextLine();
        Integer publicationYear = null;

        if (!yearInput.isEmpty()) {
            try {
                publicationYear = Integer.parseInt(yearInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format.");
            }
        }

        List<Book> results = bookManager.searchBooks(title, author, publicationYear);

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}
