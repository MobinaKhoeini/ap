package midproject;

import java.util.ArrayList;

public class Library {
    private String libraryName;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Employee> employees;
    private ArrayList<Loan> borrowedBooks;
    InputProcessing inputProcessing = new InputProcessing();

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
    }

    public String getLibraryName() {
        return libraryName;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Loan> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setBorrowedBooks(ArrayList<Loan> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void addingEmployee() {
        Employee employee1 = inputProcessing.addingEmployee();
        for (Employee employee: employees) {
            if (employee.getEmployeeNumber() == employee1.getEmployeeNumber()) {
                System.out.println("we already have this employee in our system!");
                return;
            }
        }
        employees.add(employee1);
        System.out.println("added employee successfully");
    }

    public void addingBooks() {
        Book book1 = inputProcessing.addingBooks();
        for (Book book: books) {
            if (book.getName().equalsIgnoreCase(book1.getName()) && book.getAuthor().equalsIgnoreCase(book1.getAuthor())) {
                System.out.println("this book already exists in the library!");
                return;
            }
        }
        books.add(book1);
        System.out.println("added the book successfully!");
    }

    public ArrayList<Loan> getOverdueLoans() {
        ArrayList<Loan> overdueloans = new ArrayList<>();
        for (Loan loan : borrowedBooks) {
            if (loan.isOverdue()) {
                overdueloans.add(loan);
            }
        }
        return overdueloans;
    }

    public void printOverdueLoans() {
        ArrayList<Loan> overdueLoans = getOverdueLoans();
        if (overdueLoans.isEmpty()) {
            System.out.println("no late loans");
        } else {
            for (Loan loan : overdueLoans) {
                System.out.println(loan);
            }
        }
    }

    public void signingUp() {
        Student student = inputProcessing.signingUp();
        students.add(student);
        System.out.println("signed up successfully!");
    }

    public void searchBooks() {
        String name = inputProcessing.searchBooks();
        if (books.isEmpty()) {
            System.out.println("no books available!");
        }
        for (Book book : books) {
            if (book.getName().equals(name)) {
                System.out.println("found the book!");
            } else {
                System.out.println("not found the book!");
            }
        }
    }

    public boolean studentLogin(){
        Student student1 = inputProcessing.studentLogin();
        for (Student student: students) {
            if (student.getFirstName().equalsIgnoreCase(student1.getFirstName()) && student.getLastName().equalsIgnoreCase(student1.getLastName()) && student.getStudentNumber() == student1.getStudentNumber() && student.getFieldOfStudy().equalsIgnoreCase(student1.getFieldOfStudy()));
            System.out.println("logged successfully!");
            return true;
        }
        System.out.println("no student found!");
        return false;
    }
    public Employee employeeLogin() {
        Employee employee1 = inputProcessing.employeeLogin();
        for (Employee employee: employees) {
            if (employee.getEmployeeNumber() == employee1.getEmployeeNumber()) {
                return employee;
            }
        }
        System.out.println("no employee found!");
        return null;
    }
    public void completingEmployeeInfo() {
        Employee employee = employeeLogin();
        if (employee == null) {
            //DO NOTHING
        }
        else {
            if (employee.getLastName() == null || employee.getFirstName() == null) {
                employee.setFirstName(inputProcessing.getEmployeeFirstName());
                employee.setLastName(inputProcessing.getEmployeeLastName());
                System.out.println("completed information!");
            } else {
                System.out.println("your information is already completed!");
            }
        }
    }
    public void changingEmployeeInfo() {
        Employee employee = employeeLogin();
        if (employee == null) {
            //DO NOTHING
        }
        else {
            if (employee.getLastName() != null || employee.getFirstName() != null) {
                employee.setFirstName(inputProcessing.getEmployeeFirstName());
                employee.setLastName(inputProcessing.getEmployeeLastName());
                System.out.println("changed information!");
            } else {
                System.out.println("complete your information first!");
            }
        }
    }
}
