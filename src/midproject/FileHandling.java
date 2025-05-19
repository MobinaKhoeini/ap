
package midproject;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class FileHandling {
    private final String FILE_PATH = "D:/saveLibrary.txt";

    public void saveLibrary(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("=== BOOKS ===");
            for (Book book : library.getBooks()) {
                writer.printf("%s,%s,%d,%d,%b%n",
                        book.getName(),
                        book.getAuthor(),
                        book.getPageNumber(),
                        book.getPublicationYear(),
                        book.isAvailable());
            }
            writer.println("=== STUDENTS ===");
            for (Student student : library.getStudents()) {
                writer.printf("%s,%s,%d,%s%n",
                        student.getFirstName(),
                        student.getLastName(),
                        student.getStudentNumber(),
                        student.getFieldOfStudy());
            }
            writer.println("=== EMPLOYEES ===");
            for (Employee employee : library.getEmployees()) {
                writer.printf("%s,%s,%d,%d,%d%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmployeeNumber(),
                        employee.getReceiveNum(),
                        employee.getDeliveryNum());
            }
            writer.println("=== BORROWED BOOKS ===");
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

            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    private void saveLoan(PrintWriter writer, Loan loan) {
        writer.printf("%s,%s,%d,%d,%d,",
                loan.getBorrowedBook().getName(),
                loan.getBorrowedBook().getAuthor(),
                loan.getBorrower().getStudentNumber(),
                loan.getLoanEmployee().getEmployeeNumber(),
                loan.getLoanDate().getTime());

        if (loan.getReturnDate() != null) {
            writer.print(loan.getReturnDate().getTime());
        } else {
            writer.print("0");
        }

        writer.print(",");

        if (loan.getReturnemployee() != null) {
            writer.print(loan.getReturnemployee().getEmployeeNumber());
        } else {
            writer.print("0");
        }

        writer.println();
    }

    public void loadLibrary(Library library) {
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

                String[] data = line.split(",");
                switch (currentSection) {
                    case "=== BOOKS ===":
                        Book book = new Book(data[0], data[1],
                                Integer.parseInt(data[2]),
                                Integer.parseInt(data[3]));
                        book.setAvailable(Boolean.parseBoolean(data[4]));
                        books.add(book);
                        break;
                    case "=== STUDENTS ===":
                        students.add(new Student(data[0], data[1],
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
                    case "=== BORROWED BOOKS ===":
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

            System.out.println("Data loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
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