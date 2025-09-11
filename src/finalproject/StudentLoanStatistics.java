package finalproject;

import java.util.List;

public class StudentLoanStatistics {
    private int totalLoans;
    private int notReturnedCount;
    private int lateReturnsCount;
    private List<Loan> loanHistory;

    public StudentLoanStatistics(int totalLoans, int notReturnedCount, int lateReturnsCount, List<Loan> loanHistory) {
        this.totalLoans = totalLoans;
        this.notReturnedCount = notReturnedCount;
        this.lateReturnsCount = lateReturnsCount;
        this.loanHistory = loanHistory;
    }

    public int getTotalLoans() {
        return totalLoans;
    }

    public int getNotReturnedCount() {
        return notReturnedCount;
    }

    public int getLateReturnsCount() {
        return lateReturnsCount;
    }

    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    @Override
    public String toString() {
        return "Student Loan Statistics:\n" +
                "Total Loans: " + totalLoans + "\n" +
                "Books Not Returned: " + notReturnedCount + "\n" +
                "Late Returns: " + lateReturnsCount;
    }
}

