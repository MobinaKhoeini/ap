package finalproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private String studentUsername;
    private String bookIsbn;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private String status;

    public Loan(String studentUsername, String bookIsbn, LocalDate startDate, LocalDate endDate) {
        this.studentUsername = studentUsername;
        this.bookIsbn = bookIsbn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = false;
        this.status = "pending";
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

    public String getStatus() {
        return status;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return studentUsername + "|" + bookIsbn + "|" +
                startDate.format(DateTimeFormatter.ISO_DATE) + "|" +
                endDate.format(DateTimeFormatter.ISO_DATE) + "|" +
                isActive + "|" + status;
    }

    public static Loan fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 6) {
            LocalDate startDate = LocalDate.parse(parts[2], DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(parts[3], DateTimeFormatter.ISO_DATE);
            Loan loan = new Loan(parts[0], parts[1], startDate, endDate);
            loan.setActive(Boolean.parseBoolean(parts[4]));
            loan.setStatus(parts[5]);
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
                " | Status: " + status +
                " | Active: " + (isActive ? "Yes" : "No");
    }

    public boolean isStartDateTodayOrYesterday() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        return startDate.equals(today) || startDate.equals(yesterday);
    }
}


