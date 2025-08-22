package finalproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private StudentManager studentManager;
    private BookManager bookManager;
    private LoanManager loanManager;
    private MenuHandler menuHandler;
    private Scanner scanner;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookManager = new BookManager();
        this.loanManager = new LoanManager(bookManager);
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
        System.out.println("\n--- Borrow Book ---");

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Start date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();

        System.out.print("End date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine();

        try {
            LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);

            if (endDate.isBefore(startDate)) {
                System.out.println("End date cannot be before start date.");
                return;
            }

            loanManager.borrowBook(student.getUsername(), isbn, startDate, endDate);

        } catch (Exception e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void returnBook(Student student) {
        System.out.println("\n--- Return Book ---");

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        loanManager.returnBook(student.getUsername(), isbn);
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

    public void viewMyLoans(Student student) {
        System.out.println("\n--- My Loans ---");
        List<Loan> myLoans = loanManager.getStudentLoans(student.getUsername());

        if (myLoans.isEmpty()) {
            System.out.println("You have no loans.");
            return;
        }

        for (Loan loan : myLoans) {
            System.out.println(loan);
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
