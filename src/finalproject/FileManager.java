package finalproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements IFileManager {
    private final String studentFile;
    private final String bookFile;
    private final String loanFile;
    private final String employeeFile;
    public FileManager() {
        this.studentFile = "D:/savingLibrary.txt";
        this.bookFile = "D:/savingBooks.txt";
        this.loanFile = "D:/savingloans.txt";
        this.employeeFile = "D:/savingEmployees.txt";
    }

    @Override
    public void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(studentFile))) {
            for (Student student : students) {
                writer.println(student.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    @Override
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(studentFile);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromFileString(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        return students;
    }

    @Override
    public void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(bookFile))) {
            for (Book book : books) {
                writer.println(book.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving book data: " + e.getMessage());
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(bookFile);

        if (!file.exists()) {
            System.out.println("No books existing");
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(bookFile))) {
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

    @Override
    public void saveLoans(List<Loan> loans) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(loanFile))) {
            for (Loan loan : loans) {
                writer.println(loan.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving loan data: " + e.getMessage());
        }
    }

    @Override
    public List<Loan> loadLoans() {
        List<Loan> loans = new ArrayList<>();
        File file = new File(loanFile);

        if (!file.exists()) {
            return loans;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(loanFile))) {
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

    @Override
    public void saveEmployees(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(employeeFile))) {
            for (Employee employee : employees) {
                writer.println(employee.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(employeeFile);

        if (!file.exists()) {
            saveEmployees(employees);
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile))) {
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