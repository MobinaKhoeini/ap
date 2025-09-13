package finalproject;

import java.util.List;

public class StudentLoanReport {
    private String studentUsername;
    private int totalLoans;
    private int notReturnedCount;
    private int lateReturnsCount;
    private int onTimeReturnsCount;
    private List<Loan> loanHistory;

    public StudentLoanReport(String studentUsername, int totalLoans, int notReturnedCount, int lateReturnsCount, int onTimeReturnsCount, List<Loan> loanHistory) {
        this.studentUsername = studentUsername;
        this.totalLoans = totalLoans;
        this.notReturnedCount = notReturnedCount;
        this.lateReturnsCount = lateReturnsCount;
        this.onTimeReturnsCount = onTimeReturnsCount;
        this.loanHistory = loanHistory;
    }

    public String getStudentUsername() { return studentUsername; }
    public int getTotalLoans() { return totalLoans; }
    public int getNotReturnedCount() { return notReturnedCount; }
    public int getLateReturnsCount() { return lateReturnsCount; }
    public int getOnTimeReturnsCount() { return onTimeReturnsCount; }
    public List<Loan> getLoanHistory() { return loanHistory; }

    @Override
    public String toString() {
        return "Student Loan Report for: " + studentUsername + "\n" +
                "Total Loans: " + totalLoans + "\n" +
                "Books Not Returned: " + notReturnedCount + "\n" +
                "Late Returns: " + lateReturnsCount + "\n" +
                "On-Time Returns: " + onTimeReturnsCount + "\n" +
                "Return Compliance Rate: " + (totalLoans > 0 ?
                String.format("%.1f", (double) onTimeReturnsCount / totalLoans * 100) : "0") + "%";
    }
}

