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
    private LocalDate actualPickupDate;
    private LocalDate actualReturnDate;

    public Loan(String studentUsername, String bookIsbn, LocalDate startDate, LocalDate endDate) {
        this.studentUsername = studentUsername;
        this.bookIsbn = bookIsbn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = false;
        this.status = "pending";
        this.actualPickupDate = null;
        this.actualReturnDate = null;
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

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public boolean isReturnedLate() {
        if (actualReturnDate == null) {
            return false;
        }
        return actualReturnDate.isAfter(endDate);
    }

    public LocalDate getActualPickupDate() {
        return actualPickupDate;
    }

    public void setActualPickupDate(LocalDate actualPickupDate) {
        this.actualPickupDate = actualPickupDate;
    }

    public boolean isPickedUp() {
        return actualPickupDate != null;
    }

    public boolean isReturned() {
        return actualReturnDate != null;
    }

    public String toFileString() {
        return studentUsername + "|" + bookIsbn + "|" +
                startDate.format(DateTimeFormatter.ISO_DATE) + "|" +
                endDate.format(DateTimeFormatter.ISO_DATE) + "|" +
                isActive + "|" + status + "|" +
                (actualPickupDate != null ? actualPickupDate.format(DateTimeFormatter.ISO_DATE) : "null") + "|" +
                (actualReturnDate != null ? actualReturnDate.format(DateTimeFormatter.ISO_DATE) : "null");
    }

    public static Loan fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 8) {
            LocalDate startDate = LocalDate.parse(parts[2], DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(parts[3], DateTimeFormatter.ISO_DATE);
            Loan loan = new Loan(parts[0], parts[1], startDate, endDate);
            loan.setActive(Boolean.parseBoolean(parts[4]));
            loan.setStatus(parts[5]);

            if (!"null".equals(parts[6])) {
                loan.setActualPickupDate(LocalDate.parse(parts[6], DateTimeFormatter.ISO_DATE));
            }

            if (!"null".equals(parts[7])) {
                loan.setActualReturnDate(LocalDate.parse(parts[7], DateTimeFormatter.ISO_DATE));
            }

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
                " | Pickup: " + (actualPickupDate != null ? actualPickupDate : "Not picked up") +
                " | Return: " + (actualReturnDate != null ? actualReturnDate : "Not returned") +
                " | Status: " + status +
                " | Active: " + (isActive ? "Yes" : "No");
    }

}


