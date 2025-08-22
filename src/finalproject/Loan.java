package finalproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private String studentUsername;
    private String bookIsbn;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    public Loan(String studentUsername, String bookIsbn, LocalDate startDate, LocalDate endDate) {
        this.studentUsername = studentUsername;
        this.bookIsbn = bookIsbn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    public String getStudentUsername() {
        return studentUsername;
    }
    public String getBookIsbn() {
        return bookIsbn;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String toFileString() {
        return studentUsername + "|" + bookIsbn + "|" +
                startDate.format(DateTimeFormatter.ISO_DATE) + "|" +
                endDate.format(DateTimeFormatter.ISO_DATE) + "|" + isActive;
    }

    public static Loan fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 5) {
            LocalDate startDate = LocalDate.parse(parts[2], DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(parts[3], DateTimeFormatter.ISO_DATE);
            Loan loan = new Loan(parts[0], parts[1], startDate, endDate);
            loan.setActive(Boolean.parseBoolean(parts[4]));
            return loan;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Student: " + studentUsername +
                " | Book ISBN: " + bookIsbn +
                " | Start: " + startDate +
                " | End: " + endDate +
                " | Status: " + (isActive ? "Active" : "Returned");
    }
}

