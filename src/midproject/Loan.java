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
    public Loan(Book borrowedBook, Student borrower, Employee loanEmployee, Date loanDate) {
        this.borrowedBook = borrowedBook;
        this.borrower = borrower;
        this.loanEmployee = loanEmployee;
        this.loanDate = loanDate;
        this.dueDate = calculateDueDate();
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public Student getBorrower() {
        return borrower;
    }

    public Employee getLoanEmployee() {
        return loanEmployee;
    }

    public Employee getReturnemployee() {
        return returnemployee;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }

    public void setLoanEmployee(Employee loanEmployee) {
        this.loanEmployee = loanEmployee;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturnemployee(Employee returnemployee) {
        this.returnemployee = returnemployee;
    }

    public boolean isOverdue() {
        Date now = new Date();
        if (returnDate != null) {
            return returnDate.after(dueDate);
        }
        return now.after(dueDate);
    }
    private Date calculateDueDate() {
        long dueTime = loanDate.getTime() + (14 * 24 * 60 * 60 * 1000);
        return dueDate;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "borrowedBook=" + borrowedBook +
                ", borrower=" + borrower +
                ", loanEmployee=" + loanEmployee +
                ", returnemployee=" + returnemployee +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }

}
