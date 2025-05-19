package midproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Library {
    private String libraryName;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Employee> employees;
    private ArrayList<Loan> borrowedBooks;
    private ArrayList<Loan> loans;
    private ArrayList<Loan> receives;
    InputProcessing inputProcessing = new InputProcessing();
    FileHandling fileHandeling = new FileHandling();

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.receives = new ArrayList<>();
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

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public void setReceives(ArrayList<Loan> receives) {
        this.receives = receives;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public ArrayList<Loan> getReceives() {
        return receives;
    }

    public void menuInitialize(Library library) throws IOException {
        Menu menu = new Menu();
        int choice, option, roleType;
        fileHandeling.loadLibrary(library);
        do {
            menu.primaryMenu();
            roleType = inputProcessing.getNum();
            boolean back = false;
            while (!back) {
                if (roleType == 1) {
                    menu.managerMenu();
                    choice = inputProcessing.getNum();
                    switch (choice) {
                        case 1:
                            addingEmployee();
                            fileHandeling.saveLibrary(library);
                            break;
                        case 2:
                            printOverdueLoans();
                            break;
                        case 3:
                            printTop10MostBorrowedBooks();
                            break;
                        case 4:
                            printDeliveriesAndReceives();
                            break;
                        case 5:
                            back = true;
                    }
                }
                if (roleType == 2) {
                    menu.studentMenu1();
                    choice = inputProcessing.getNum();
                    switch (choice) {
                        case 1:
                            signingUp();
                            fileHandeling.saveLibrary(library);
                            break;
                        case 2: {
                            if (studentLogin()) {
                                menu.studentMenu2();
                                option = inputProcessing.getNum();
                                switch (option) {
                                    case 1: {
                                        searchBooks();
                                        break;
                                    }
                                    case 2: {
                                        requestBook();
                                        break;
                                    }
                                    case 3: {
                                        requestDelivery();
                                        break;
                                    }
                                    case 4: {
                                        printBorrowedBooks();
                                        break;
                                    }
                                    case 5: {
                                        back = true;
                                        break;
                                    }
                                }
                            }
                        }
                        case 3: {
                            back = true; break;
                        }
                    }
                }
                if (roleType == 3) {
                    menu.employeeMenu1();
                    choice = inputProcessing.getNum();
                    if (choice == 1) {
                        if (employeeLogin() != null) {
                            menu.employeeMenu2();
                            option = inputProcessing.getNum();
                            switch (option) {
                                case 1: {
                                    addingBooks();
                                    fileHandeling.saveLibrary(library);
                                    break;
                                }
                                case 2: {
                                    completingEmployeeInfo();
                                    fileHandeling.saveLibrary(library);
                                    break;
                                }
                                case 3: {
                                    changingEmployeeInfo();
                                    fileHandeling.saveLibrary(library);
                                    break;
                                }
                                case 4: {
                                    acceptRequest();
                                    break;
                                }
                                case 5: {
                                    acceptDeliveryRequest();
                                    break;
                                }
                                case 6: {
                                    back = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (choice == 2) {
                        back = true;
                    }
                }
                if (roleType == 4) {
                    return;
                }
            }
        }while (true);
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
        ArrayList<Loan> overdueLoans = new ArrayList<>();
        for (Loan loan : borrowedBooks) {
            if (loan.isOverdue()) {
                overdueLoans.add(loan);
            }
        }
        return overdueLoans;
    }

    public void printOverdueLoans() {
        boolean found = false;
        if (receives.isEmpty()) {
            System.out.println("no overdue loans!");
            return;
        }
        for (Loan loan: receives) {
            if (loan.isOverdue()) {
                System.out.println(loan);
                found = true;
            }
        }
        if (!found) {
            System.out.println("no overdue loans!");
        }

    }

    public void signingUp() {
        Student student1 = inputProcessing.signingUp();
        for (Student student : students) {
            if (student.getStudentNumber() == student1.getStudentNumber()) {
                {
                    System.out.println("you have already signed up!");
                    return;
                }
            }
        }
        students.add(student1);
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
            if (student.getFirstName().equalsIgnoreCase(student1.getFirstName()) && student.getLastName().equalsIgnoreCase(student1.getLastName()) && student.getStudentNumber() == student1.getStudentNumber() && student.getFieldOfStudy().equalsIgnoreCase(student1.getFieldOfStudy())) {
                System.out.println("logged successfully!");
                return true;
            }
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

    public void requestBook() {
        Random random = new Random();
        String name = inputProcessing.searchBooks();
        int stuNum = inputProcessing.searchStudent();
        if (books.isEmpty()) {
            System.out.println("no books available!");
            return;
        }
        if (!students.isEmpty()) {
            for (Student student : students) {
                if (student.getStudentNumber() == stuNum) {
                    for (Book book : books) {
                        if (book.getName().equals(name) && book.isAvailable()) {
                            if (!employees.isEmpty()) {
                                for (Employee employee : employees) {
                                    Employee employee1 = employees.get(random.nextInt(employees.size()));
                                    Loan loan = new Loan(book, student, employee1, new Date());
                                    loans.add(loan);
                                    System.out.println("the request has been sent");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("having some issues sending the request!");
    }
    public void requestDelivery() {
        Random random = new Random();
        String name = inputProcessing.searchBooks();
        int stuNum = inputProcessing.searchStudent();
        if (borrowedBooks.isEmpty()) {
            System.out.println("no books available!");
            return;
        }
        if (!students.isEmpty()) {
            for (Student student : students) {
                if (student.getStudentNumber() == stuNum) {
                    for (Loan loan : borrowedBooks) {
                        if (loan.getBorrowedBook().getName().equals(name) && loan.getBorrower().getStudentNumber() == stuNum) {
                            if (!employees.isEmpty()) {
                                for (Employee employee : employees) {
                                    Employee employee1 = employees.get(random.nextInt(employees.size()));
                                    loan.setReturnemployee(employee1);
                                    receives.add(loan);
                                    System.out.println("the request has been sent");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("having some issues sending the request!");
    }
    public void acceptRequest() {
        boolean done = false;
        int empNum = inputProcessing.searchEmployee();
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                if (employee.getEmployeeNumber() == empNum) {
                    if (!loans.isEmpty()) {
                        for (Loan loan : loans) {
                            if (loan.getLoanEmployee().equals(employee) && loan.getBorrowedBook().isAvailable()) {
                                System.out.println(loan);
                                if (inputProcessing.acceptRequest()) {
                                    System.out.println("accepted the loan!");
                                    loan.getBorrowedBook().setAvailable(false);
                                    employee.deliveryNumIncrement();
                                    done = true;
                                    borrowedBooks.add(loan);
                                    loans.remove(loan);
                                    return;
                                } else {
                                    System.out.println("not accepted the loan!");
                                    loans.remove(loan);
                                    return;
                                }
                            }
                        }
                    } else {
                        System.out.println("no receives available");
                        return;
                    }
                }
            }
        }
        if (!done) {
            System.out.println("unable to accept request");
        }
    }

    public void acceptDeliveryRequest() {
        boolean done = false;
        int empNum = inputProcessing.searchEmployee();
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                if (employee.getEmployeeNumber() == empNum) {
                    if (!receives.isEmpty()) {
                        for (Loan loan : receives) {
                            if (loan.getReturnemployee().getEmployeeNumber() == empNum && !loan.getBorrowedBook().isAvailable()) {
                                System.out.println(loan);
                                if (inputProcessing.acceptRequest()) {
                                    System.out.println("accepted the delivery!");
                                    employee.receiveNumIncrement();
                                    loan.getBorrowedBook().setAvailable(true);
                                    loan.setReturnDate(new Date());
                                    done = true;
                                    borrowedBooks.remove(loan);
                                    return;
                                } else {
                                    System.out.println("not accepted the delivery!");
                                    receives.remove(loan);
                                    if (receives.isEmpty()) return;
                                }
                            }
                        }
                    } else {
                        System.out.println("no receives available");
                        return;
                    }
                }
            }
        }
        if (!done) {
            System.out.println("unable to accept delivery");
        }
    }
    public void printBorrowedBooks() {
        System.out.println("list of borrowed books that are not returned:");
        for (Loan loan: borrowedBooks) {
            System.out.println(loan.getBorrowedBook());
        }
    }
    public void printDeliveriesAndReceives() {
        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }

    public void printTop10MostBorrowedBooks() {
        Date oneYearAgo = new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000);
        ArrayList<Book> uniqueBooks = new ArrayList<>();
        ArrayList<Integer> borrowCounts = new ArrayList<>();
        for (Loan loan : receives) {
            if (loan.getLoanDate().after(oneYearAgo)) {
                Book currentBook = loan.getBorrowedBook();
                boolean found = false;
                for (int i = 0; i < uniqueBooks.size(); i++) {
                    if (uniqueBooks.get(i).equals(currentBook)) {
                        borrowCounts.set(i, borrowCounts.get(i) + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    uniqueBooks.add(currentBook);
                    borrowCounts.add(1);
                }
            }
        }
        for (int i = 0; i < uniqueBooks.size() - 1; i++) {
            for (int j = i + 1; j < uniqueBooks.size(); j++) {
                if (borrowCounts.get(i) < borrowCounts.get(j)) {
                    Book tempBook = uniqueBooks.get(i);
                    uniqueBooks.set(i, uniqueBooks.get(j));
                    uniqueBooks.set(j, tempBook);
                    int tempCount = borrowCounts.get(i);
                    borrowCounts.set(i, borrowCounts.get(j));
                    borrowCounts.set(j, tempCount);
                }
            }
        }
        System.out.println("\nTop 10 Most Borrowed Books in the Last Year:");
        int count = Math.min(10, uniqueBooks.size());
        for (int i = 0; i < count; i++) {
            Book book = uniqueBooks.get(i);
            System.out.printf("%d. %s by %s - Borrowed %d times%n",
                    i + 1,
                    book.getName(),
                    book.getAuthor(),
                    borrowCounts.get(i));
        }

        if (count == 0) {
            System.out.println("No books have been borrowed in the last year.");
        }
    }

}




