package finalproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanManager {
    private List<Loan> loans;
    private BookManager bookManager;

    public LoanManager(BookManager bookManager) {
        this.loans = FileManager.loadLoans();
        this.bookManager = bookManager;
    }

    public boolean borrowBook(String studentUsername, String bookIsbn, LocalDate startDate, LocalDate endDate) {
        List<Book> books = bookManager.searchBooks(null, null, null);
        Book targetBook = books.stream()
                .filter(b -> b.getIsbn().equals(bookIsbn) && b.isAvailable())
                .findFirst()
                .orElse(null);

        if (targetBook == null) {
            System.out.println("Book not available or not found.");
            return false;
        }
        boolean hasActiveLoan = loans.stream()
                .anyMatch(loan -> loan.getStudentUsername().equals(studentUsername) &&
                        loan.getBookIsbn().equals(bookIsbn) &&
                        loan.isActive());

        if (hasActiveLoan) {
            System.out.println("You already have an active loan for this book.");
            return false;
        }
        Loan newLoan = new Loan(studentUsername, bookIsbn, startDate, endDate);
        loans.add(newLoan);
        targetBook.setAvailable(false);
        FileManager.saveBooks(bookManager.getAllBooks());
        FileManager.saveLoans(loans);

        System.out.println("Book borrowed successfully from " + startDate + " to " + endDate);
        return true;
    }

    public boolean returnBook(String studentUsername, String bookIsbn) {
        Loan activeLoan = loans.stream()
                .filter(loan -> loan.getStudentUsername().equals(studentUsername) &&
                        loan.getBookIsbn().equals(bookIsbn) &&
                        loan.isActive())
                .findFirst()
                .orElse(null);

        if (activeLoan == null) {
            System.out.println("No active loan found for this book.");
            return false;
        }
        activeLoan.setActive(false);
        List<Book> books = bookManager.searchBooks(null, null, null);
        Book targetBook = books.stream()
                .filter(b -> b.getIsbn().equals(bookIsbn))
                .findFirst()
                .orElse(null);

        if (targetBook != null) {
            targetBook.setAvailable(true);
            FileManager.saveBooks(books);
        }

        FileManager.saveLoans(loans);
        System.out.println("Book returned successfully.");
        return true;
    }

    public List<Loan> getStudentLoans(String studentUsername) {
        return loans.stream()
                .filter(loan -> loan.getStudentUsername().equals(studentUsername))
                .collect(Collectors.toList());
    }

    public List<Loan> getActiveLoans() {
        return loans.stream()
                .filter(Loan::isActive)
                .collect(Collectors.toList());
    }

    public void saveLoans() {
        FileManager.saveLoans(loans);
    }
}