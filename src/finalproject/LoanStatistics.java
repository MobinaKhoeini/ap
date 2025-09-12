package finalproject;

public class LoanStatistics {
    private int totalLoanRequests;
    private int totalLoansApproved;
    private int totalLoansCompleted;
    private double averageLoanDuration;

    public LoanStatistics(int totalLoanRequests, int totalLoansApproved,
                          int totalLoansCompleted, double averageLoanDuration) {
        this.totalLoanRequests = totalLoanRequests;
        this.totalLoansApproved = totalLoansApproved;
        this.totalLoansCompleted = totalLoansCompleted;
        this.averageLoanDuration = averageLoanDuration;
    }

    public int getTotalLoanRequests() {
        return totalLoanRequests;
    }

    public int getTotalLoansApproved() {
        return totalLoansApproved;
    }

    public int getTotalLoansCompleted() {
        return totalLoansCompleted;
    }

    public double getAverageLoanDuration() {
        return averageLoanDuration;
    }

    @Override
    public String toString() {
        return "Loan Statistics Report:\n" +
                "Total Loan Requests: " + totalLoanRequests + "\n" +
                "Total Loans Approved: " + totalLoansApproved + "\n" +
                "Total Loans Completed: " + totalLoansCompleted + "\n" +
                "Average Loan Duration: " + String.format("%.2f", averageLoanDuration) + " days\n" +
                "Approval Rate: " + String.format("%.1f", (totalLoanRequests > 0 ?
                (double) totalLoansApproved / totalLoanRequests * 100 : 0)) + "%";
    }
}
