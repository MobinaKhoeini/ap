package finalproject;

import java.io.*;
import java.util.List;

public class FileManager {
    private static final String STUDENT_FILE = "D:/savingLibrary.txt";
    private static final String BOOK_FILE = "D:/savingBooks.txt";
    private static final String LOAN_FILE = "D:/savingloans.txt";
    private static final String EMPLOYEE_FILE= "D:/savingEmployees.txt";

    public static void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) {
            for (Student student : students) {
                writer.println(student.getName() + "," +
                        student.getStudentId() + "," +
                        student.getUsername() + "," +
                        student.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new java.util.ArrayList<>();
        File file = new File(STUDENT_FILE);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        return students;
    }

    public static void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOK_FILE))) {
            for (Book book : books) {
                writer.println(book.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving book data: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new java.util.ArrayList<>();
        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            System.out.println("no books existing");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOK_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = Book.fromFileString(line);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading book data: " + e.getMessage());
        }

        return books;
    }

    public static void saveLoans(List<Loan> loans) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOAN_FILE))) {
            for (Loan loan : loans) {
                writer.println(loan.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving loan data: " + e.getMessage());
        }
    }

    public static List<Loan> loadLoans() {
        List<Loan> loans = new java.util.ArrayList<>();
        File file = new File(LOAN_FILE);

        if (!file.exists()) {
            return loans;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(LOAN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Loan loan = Loan.fromFileString(line);
                if (loan != null) {
                    loans.add(loan);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading loan data: " + e.getMessage());
        }

        return loans;
    }
    public static void saveEmployees(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                writer.println(employee.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new java.util.ArrayList<>();
        File file = new File(EMPLOYEE_FILE);

        if (!file.exists()) {
            saveEmployees(employees);
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = Employee.fromFileString(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }

        return employees;
    }
}