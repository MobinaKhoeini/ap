package finalproject;

public class StudentDelayStats {
    private String username;
    private int delayedLoansCount;
    private long totalDelayDays;

    public StudentDelayStats(String username, int delayedLoansCount, long totalDelayDays) {
        this.username = username;
        this.delayedLoansCount = delayedLoansCount;
        this.totalDelayDays = totalDelayDays;
    }

    public String getUsername() { return username; }
    public int getDelayedLoansCount() { return delayedLoansCount; }
    public long getTotalDelayDays() { return totalDelayDays; }

    @Override
    public String toString() {
        return "Student: " + username +
                " | Delayed Loans: " + delayedLoansCount +
                " | Total Delay Days: " + totalDelayDays;
    }
}
