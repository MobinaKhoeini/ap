package finalproject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoanManager {
    private List<Loan> loans;
    private BookManager bookManager;
    private final IFileManager fileManager;
    private final StudentManager studentManager;

    public LoanManager(BookManager bookManager, IFileManager fileManager, StudentManager studentManager) {
        this.fileManager = fileManager;
        this.loans = fileManager.loadLoans();
        this.bookManager = bookManager;
        this.studentManager = studentManager;
    }

    public List<Loan> getPendingLoansForToday() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        return loans.stream()
                .filter(loan -> loan.getStatus().equals("pending"))
                .filter(loan -> loan.getStartDate().equals(today) ||
                        loan.getStartDate().equals(yesterday))
                .collect(Collectors.toList());
    }

    public List<Loan> getAllPendingLoans() {
        return loans.stream()
                .filter(loan -> loan.getStatus().equals("pending"))
                .collect(Collectors.toList());
    }


    public boolean approveLoan(String studentUsername, String bookIsbn) {
        for (Loan loan : loans) {
            if (loan.getStudentUsername().equals(studentUsername) &&
                    loan.getBookIsbn().equals(bookIsbn) &&
                    loan.getStatus().equals("pending")) {

                loan.setStatus("approved");
                loan.setActive(true);

                Book book = bookManager.findBookByIsbn(bookIsbn);
                if (book != null) {
                    book.setAvailable(false);
                }

                fileManager.saveLoans(loans);
                fileManager.saveBooks(bookManager.getAllBooks());
                System.out.println("Loan approved successfully!");
                return true;
            }
        }
        return false;
    }
    public LoanStatistics getLoanStatistics() {
        int totalLoanRequests = loans.size();

        int totalLoansApproved = (int) loans.stream()
                .filter(loan -> "approved".equals(loan.getStatus()))
                .count();

        int totalLoansCompleted = (int) loans.stream()
                .filter(loan -> loan.isReturned())
                .count();

        double averageLoanDuration = loans.stream()
                .filter(loan -> loan.isPickedUp() && loan.isReturned())
                .mapToLong(loan -> ChronoUnit.DAYS.between(
                        loan.getActualPickupDate(),
                        loan.getActualReturnDate()
                ))
                .average()
                .orElse(0.0);

        return new LoanStatistics(totalLoanRequests, totalLoansApproved,
                totalLoansCompleted, averageLoanDuration);
    }

    public boolean rejectLoan(String studentUsername, String bookIsbn) {
        for (Loan loan : loans) {
            if (loan.getStudentUsername().equals(studentUsername) &&
                    loan.getBookIsbn().equals(bookIsbn) &&
                    loan.getStatus().equals("pending")) {

                loan.setStatus("rejected");
                fileManager.saveLoans(loans);
                return true;
            }
        }
        return false;
    }

    public void addLoan(Loan loan) {
        Student student = studentManager.getStudentByUsername(loan.getStudentUsername());
        if (student != null && !student.isActive()) {
            System.out.println("Cannot create loan: Student is inactive.");
            return;
        }

        loans.add(loan);
        fileManager.saveLoans(loans);
    }

    public boolean borrowBook(String studentUsername, String bookIsbn, LocalDate startDate, LocalDate endDate) {
        Student student = studentManager.getStudentByUsername(studentUsername);
        if (student != null && !student.isActive()) {
            System.out.println("Cannot borrow book: Student is inactive.");
            return false;
        }

        List<Book> books = bookManager.getAllBooks();
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
        bookManager.saveBooks();
        fileManager.saveLoans(loans);

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
        activeLoan.setActualReturnDate(LocalDate.now());

        List<Book> books = bookManager.getAllBooks();
        Book targetBook = books.stream()
                .filter(b -> b.getIsbn().equals(bookIsbn))
                .findFirst()
                .orElse(null);

        if (targetBook != null) {
            targetBook.setAvailable(true);
            fileManager.saveBooks(books);
        }
        fileManager.saveLoans(loans);
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
    public boolean recordBookPickup(String studentUsername, String bookIsbn) {
        for (Loan loan : loans) {
            if (loan.getStudentUsername().equals(studentUsername) &&
                    loan.getBookIsbn().equals(bookIsbn) &&
                    "approved".equals(loan.getStatus())) {
                loan.setActualPickupDate(LocalDate.now());
                loan.setActive(true);
                fileManager.saveLoans(loans);
                System.out.println("Book pickup recorded successfully for student: " + studentUsername);
                return true;
            }
        }
        System.out.println("No approved loan found for this student and book.");
        return false;
    }

    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }

    public void saveLoans() {
        fileManager.saveLoans(loans);
    } public List<Loan> getStudentLoanHistory(String studentUsername) {
        return loans.stream()
                .filter(loan -> loan.getStudentUsername().equals(studentUsername))
                .collect(Collectors.toList());
    }

    public StudentLoanStatistics getStudentLoanStatistics(String studentUsername) {
        List<Loan> studentLoans = getStudentLoanHistory(studentUsername);

        int totalLoans = studentLoans.size();
        int notReturnedCount = (int) studentLoans.stream()
                .filter(loan -> loan.isActive() && loan.getStatus().equals("approved"))
                .count();
        int lateReturnsCount = (int) studentLoans.stream()
                .filter(Loan::isReturnedLate)
                .count();

        return new StudentLoanStatistics(totalLoans, notReturnedCount, lateReturnsCount, studentLoans);
    }
    public List<Loan> getApprovedButNotPickedUpLoans() {
        return loans.stream()
                .filter(loan -> "approved".equals(loan.getStatus()))
                .filter(loan -> !loan.isPickedUp())
                .collect(Collectors.toList());
    }

    public List<Loan> getActiveLoansToReturn() {
        return loans.stream()
                .filter(loan -> loan.isActive() && loan.isPickedUp() && !loan.isReturned())
                .collect(Collectors.toList());
    }
    public List<Loan> getApprovedLoans() {
        return loans.stream()
                .filter(loan -> "approved".equals(loan.getStatus()))
                .collect(Collectors.toList());
    }
    public StudentLoanReport getStudentLoanReport(String studentUsername) {
        List<Loan> studentLoans = getStudentLoanHistory(studentUsername);

        int totalLoans = studentLoans.size();
        int notReturnedCount = (int) studentLoans.stream()
                .filter(loan -> loan.isActive() && "approved".equals(loan.getStatus()))
                .count();

        int lateReturnsCount = (int) studentLoans.stream()
                .filter(loan -> loan.isReturned() && loan.getActualReturnDate().isAfter(loan.getEndDate()))
                .count();

        int onTimeReturnsCount = (int) studentLoans.stream()
                .filter(loan -> loan.isReturned() &&
                        !loan.getActualReturnDate().isAfter(loan.getEndDate()))
                .count();

        return new StudentLoanReport(studentUsername, totalLoans, notReturnedCount,
                lateReturnsCount, onTimeReturnsCount, studentLoans);
    }

    public List<StudentDelayStats> getTopStudentsWithDelays(int limit) {
        Map<String, List<Loan>> loansByStudent = loans.stream()
                .filter(loan -> loan.isReturned())
                .collect(Collectors.groupingBy(Loan::getStudentUsername));

        List<StudentDelayStats> studentStats = new ArrayList<>();

        for (Map.Entry<String, List<Loan>> entry : loansByStudent.entrySet()) {
            String username = entry.getKey();
            List<Loan> studentLoans = entry.getValue();

            long totalDelayDays = studentLoans.stream()
                    .filter(loan -> loan.getActualReturnDate().isAfter(loan.getEndDate()))
                    .mapToLong(loan -> ChronoUnit.DAYS.between(
                            loan.getEndDate(),
                            loan.getActualReturnDate()
                    ))
                    .sum();

            int delayedLoansCount = (int) studentLoans.stream()
                    .filter(loan -> loan.getActualReturnDate().isAfter(loan.getEndDate()))
                    .count();

            studentStats.add(new StudentDelayStats(username, delayedLoansCount, totalDelayDays));
        }

        return studentStats.stream()
                .sorted(Comparator.comparingInt(StudentDelayStats::getDelayedLoansCount).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}