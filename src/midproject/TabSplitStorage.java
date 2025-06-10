package midproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class TabSplitStorage implements StorageHandler {
    private final String FILE_PATH = "library_tab.txt";

    @Override
    public void saveLibrary(Library library) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("=== BOOKS ===");
            for (Book book : library.getBooks()) {
                writer.printf("%s\t%s\t%d\t%d\t%b%n",
                        book.getName(), book.getAuthor(),
                        book.getPageNumber(), book.getPublicationYear(),
                        book.isAvailable());
            }
            writer.println("=== STUDENTS ===");
            for (Student student : library.getStudents()) {
                writer.printf("%s\t%s\t%d\t%s%n",
                        student.getFirstName(), student.getLastName(),
                        student.getStudentNumber(), student.getFieldOfStudy());
            }

            writer.println("=== EMPLOYEES ===");
            for (Employee employee : library.getEmployees()) {
                writer.printf("%s\t%s\t%d\t%d\t%d%n",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getEmployeeNumber(),
                        employee.getReceiveNum(), employee.getDeliveryNum());
            }

            writer.println("=== BORROWED ===");
            for (Loan loan : library.getBorrowedBooks()) {
                saveLoan(writer, loan);
            }

            writer.println("=== LOANS ===");
            for (Loan loan : library.getLoans()) {
                saveLoan(writer, loan);
            }

            writer.println("=== RECEIVES ===");
            for (Loan loan : library.getReceives()) {
                saveLoan(writer, loan);
            }
        }
    }

    private void saveLoan(PrintWriter writer, Loan loan) {
        writer.printf("%s\t%s\t%d\t%d\t%d\t",
                loan.getBorrowedBook().getName(),
                loan.getBorrowedBook().getAuthor(),
                loan.getBorrower().getStudentNumber(),
                loan.getLoanEmployee().getEmployeeNumber(),
                loan.getLoanDate().getTime());

        writer.print(loan.getReturnDate() != null ? loan.getReturnDate().getTime() : "0");
        writer.print("\t");

        writer.print(loan.getReturnemployee() != null ? loan.getReturnemployee().getEmployeeNumber() : "0");
        writer.println();
    }

    @Override
    public void loadLibrary(Library library) throws IOException {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Loan> borrowedBooks = new ArrayList<>();
        ArrayList<Loan> loans = new ArrayList<>();
        ArrayList<Loan> receives = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            String currentSection = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("===")) {
                    currentSection = line;
                    continue;
                }

                String[] data = line.split("\t");
                switch (currentSection) {
                    case "=== BOOKS ===":
                        Book book = new Book(data[0], data[1],
                                Integer.parseInt(data[2]),
                                Integer.parseInt(data[3]));
                        book.setAvailable(Boolean.parseBoolean(data[4]));
                        books.add(book);
                        break;
                    case "=== STUDENTS ===":
                        students.add(new Student(
                                data[0], data[1],
                                Integer.parseInt(data[2]),
                                data[3]));
                        break;
                    case "=== EMPLOYEES ===":
                        Employee emp = new Employee(Integer.parseInt(data[2]));
                        emp.setFirstName(data[0]);
                        emp.setLastName(data[1]);
                        emp.setReceiveNum(Integer.parseInt(data[3]));
                        emp.setDeliveryNum(Integer.parseInt(data[4]));
                        employees.add(emp);
                        break;
                    case "=== BORROWED ===":
                        borrowedBooks.add(loadLoan(data, books, students, employees));
                        break;
                    case "=== LOANS ===":
                        loans.add(loadLoan(data, books, students, employees));
                        break;
                    case "=== RECEIVES ===":
                        receives.add(loadLoan(data, books, students, employees));
                        break;
                }
            }

            library.setBooks(books);
            library.setStudents(students);
            library.setEmployees(employees);
            library.setBorrowedBooks(borrowedBooks);
            library.setLoans(loans);
            library.setReceives(receives);
        }
    }

    private Loan loadLoan(String[] data, ArrayList<Book> books,
                          ArrayList<Student> students,
                          ArrayList<Employee> employees) {
        Book book = findBook(data[0], data[1], books);
        Student student = findStudent(Integer.parseInt(data[2]), students);
        Employee loanEmployee = findEmployee(Integer.parseInt(data[3]), employees);

        Date loanDate = new Date(Long.parseLong(data[4]));
        Date returnDate = data[5].equals("0") ? null : new Date(Long.parseLong(data[5]));
        Employee returnEmployee = data[6].equals("0") ? null :
                findEmployee(Integer.parseInt(data[6]), employees);

        Loan loan = new Loan(book, student, loanEmployee, loanDate);
        loan.setReturnDate(returnDate);
        loan.setReturnemployee(returnEmployee);
        return loan;
    }

    private Book findBook(String name, String author, ArrayList<Book> books) {
        for (Book book : books) {
            if (book.getName().equals(name) && book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }

    private Student findStudent(int studentNumber, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.getStudentNumber() == studentNumber) {
                return student;
            }
        }
        return null;
    }

    private Employee findEmployee(int employeeNumber, ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.getEmployeeNumber() == employeeNumber) {
                return employee;
            }
        }
        return null;
    }
}