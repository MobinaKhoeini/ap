package finalproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private StudentManager studentManager;
    private BookManager bookManager;
    private LoanManager loanManager;
    private EmployeeManager employeeManager;
    private MenuHandler menuHandler;
    private Scanner scanner;
    private IFileManager fileManager;

    private static final int UNIVERSITY_STUDENT_COUNT = 5000;
    private static final String MANAGER_USERNAME = "admin";
    private static final String MANAGER_PASSWORD = "123";

    public LibrarySystem() {
        fileManager = new FileManager();
        this.studentManager = new StudentManager(fileManager);
        this.bookManager = new BookManager(fileManager);
        this.loanManager = new LoanManager(bookManager, fileManager, studentManager);
        this.employeeManager = new EmployeeManager(fileManager);
        this.menuHandler = new MenuHandler(this);
        this.scanner = new Scanner(System.in);
    }


    public void addBook(String title, String author, int publicationYear, String isbn) {
        bookManager.addBook(title, author, publicationYear, isbn);
    }
    public List<Loan> getApprovedButNotPickedUpLoans() {
        return loanManager.getApprovedButNotPickedUpLoans();
    }

    public boolean recordBookPickup(String studentUsername, String bookIsbn) {
        return loanManager.recordBookPickup(studentUsername, bookIsbn);
    }

    public List<Loan> getActiveLoansToReturn() {
        return loanManager.getActiveLoansToReturn();
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookManager.searchBooksByTitle(title);
    }
    public StudentLoanStatistics getStudentLoanStatistics(String studentUsername) {
        return loanManager.getStudentLoanStatistics(studentUsername);
    }

    public List<Loan> getStudentLoanHistory(String studentUsername) {
        return loanManager.getStudentLoanHistory(studentUsername);
    }

    public void saveBooks() {
        bookManager.saveBooks();
    }
    public List<Loan> getApprovedLoans() {
        return loanManager.getApprovedLoans();
    }

    public boolean authenticateManager(String username, String password) {
        return MANAGER_USERNAME.equals(username) && MANAGER_PASSWORD.equals(password);
    }
    public boolean setStudentStatus(String username, boolean isActive) {
        return studentManager.setStudentStatus(username, isActive);
    }

    public Student getStudentByUsername(String username) {
        return studentManager.getStudentByUsername(username);
    }
    public List<Loan> getAllPendingLoans() {
        return loanManager.getAllPendingLoans();
    }

    public void displayStudentsWithStatus() {
        studentManager.displayStudentsWithStatus();
    }

    public Employee authenticateEmployee(String username, String password) {
        return employeeManager.authenticateEmployee(username, password);
    }

    public boolean changeEmployeePassword(String username, String newPassword) {
        return employeeManager.changeEmployeePassword(username, newPassword);
    }

    public void registerEmployee(String username, String password) {
        employeeManager.registerEmployee(username, password);
    }

    public List<Loan> getPendingLoansForReview() {
        return loanManager.getPendingLoansForToday();
    }
    public void incrementEmployeeBooksAdded(String username) {
        employeeManager.incrementEmployeeBooksAdded(username);
    }

    public void incrementEmployeeBooksLoaned(String username) {
        employeeManager.incrementEmployeeBooksLoaned(username);
    }

    public boolean approveLoanRequest(String studentUsername, String bookIsbn) {
        return loanManager.approveLoan(studentUsername, bookIsbn);
    }

    public boolean rejectLoanRequest(String studentUsername, String bookIsbn) {
        return loanManager.rejectLoan(studentUsername, bookIsbn);
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public int getUniversityStudentCount() {
        return UNIVERSITY_STUDENT_COUNT;
    }

    public int getTotalBooksCount() {
        return bookManager.getAllBooks().size();
    }

    public int getTotalLoansCount() {
        return loanManager.getAllLoans().size();
    }
    public void incrementEmployeeBooksReturned(String username) {
        employeeManager.incrementEmployeeBooksReturned(username);
    }

    public void displayEmployeePerformance() {
        employeeManager.displayEmployeePerformance();
    }

    public int getActiveLoansCount() {
        return loanManager.getActiveLoans().size();
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
            Loan newLoan = new Loan(student.getUsername(), isbn, startDate, endDate);
            loanManager.addLoan(newLoan);

            System.out.println("Loan request submitted. Please wait for employee approval.");
            System.out.println("After approval, you can borrow the book from the library.");

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

    public void searchBooksByTitleForGuest(String title) {
        List<Book> results = bookManager.searchBooks(title, null, null);

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No books found titled: " + title);
        } else {
            for (Book book : results) {
                System.out.println("Title: " + book.getTitle() +
                        " | Author: " + book.getAuthor() +
                        " | Year: " + book.getPublicationYear() +
                        " | ISBN: " + book.getIsbn());
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
