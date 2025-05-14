package midproject;

import java.util.Date;
public class Loan {
    private Book borrowedBook;
    private Student borrower;
    private Employee loanEmployee;
    private Employee returnemployee;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    public Loan(Book borrowedBook, Student borrower, Employee loanEmployee, Date loanDate, Date dueDate) {
        this.borrowedBook = borrowedBook;
        this.borrower = borrower;
        this.loanEmployee = loanEmployee;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }
    public boolean isOverdue() {
        Date now = new Date();
        if (returnDate != null) {
            return returnDate.after(dueDate);
        }
        return now.after(dueDate);
    }
}
