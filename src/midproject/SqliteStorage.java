package midproject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class SqliteStorage implements StorageHandler {
    private final String DB_PATH = "library.db";

    @Override
    public void saveLibrary(Library library) throws IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                    "name TEXT, author TEXT, pages INTEGER, year INTEGER, available BOOLEAN)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                    "firstName TEXT, lastName TEXT, studentNumber INTEGER PRIMARY KEY, fieldOfStudy TEXT)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employees (" +
                    "firstName TEXT, lastName TEXT, employeeNumber INTEGER PRIMARY KEY, " +
                    "receiveNum INTEGER, deliveryNum INTEGER)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS loans (" +
                    "bookName TEXT, bookAuthor TEXT, studentNumber INTEGER, " +
                    "employeeNumber INTEGER, loanDate INTEGER, returnDate INTEGER, " +
                    "returnEmployee INTEGER)");

            stmt.executeUpdate("DELETE FROM books");
            stmt.executeUpdate("DELETE FROM students");
            stmt.executeUpdate("DELETE FROM employees");
            stmt.executeUpdate("DELETE FROM loans");

            for (Book book : library.getBooks()) {
                String sql = String.format(
                        "INSERT INTO books VALUES ('%s', '%s', %d, %d, %b)",
                        book.getName().replace("'", "''"),
                        book.getAuthor().replace("'", "''"),
                        book.getPageNumber(),
                        book.getPublicationYear(),
                        book.isAvailable());
                stmt.executeUpdate(sql);
            }

            for (Student student : library.getStudents()) {
                String sql = String.format(
                        "INSERT INTO students VALUES ('%s', '%s', %d, '%s')",
                        student.getFirstName().replace("'", "''"),
                        student.getLastName().replace("'", "''"),
                        student.getStudentNumber(),
                        student.getFieldOfStudy().replace("'", "''"));
                stmt.executeUpdate(sql);
            }

            for (Employee employee : library.getEmployees()) {
                String sql = String.format(
                        "INSERT INTO employees VALUES ('%s', '%s', %d, %d, %d)",
                        employee.getFirstName().replace("'", "''"),
                        employee.getLastName().replace("'", "''"),
                        employee.getEmployeeNumber(),
                        employee.getReceiveNum(),
                        employee.getDeliveryNum());
                stmt.executeUpdate(sql);
            }

            for (Loan loan : library.getBorrowedBooks()) {
                saveLoan(stmt, loan, "borrowed");
            }
            for (Loan loan : library.getLoans()) {
                saveLoan(stmt, loan, "pending");
            }
            for (Loan loan : library.getReceives()) {
                saveLoan(stmt, loan, "returning");
            }

        } catch (SQLException e) {
            throw new IOException("Database error: " + e.getMessage(), e);
        }
    }

    private void saveLoan(Statement stmt, Loan loan, String type) throws SQLException {
        String sql = String.format(
                "INSERT INTO loans VALUES ('%s', '%s', %d, %d, %d, %d, %d)",
                loan.getBorrowedBook().getName().replace("'", "''"),
                loan.getBorrowedBook().getAuthor().replace("'", "''"),
                loan.getBorrower().getStudentNumber(),
                loan.getLoanEmployee().getEmployeeNumber(),
                loan.getLoanDate().getTime(),
                loan.getReturnDate() != null ? loan.getReturnDate().getTime() : 0,
                loan.getReturnemployee() != null ? loan.getReturnemployee().getEmployeeNumber() : 0);
        stmt.executeUpdate(sql);
    }

    @Override
    public void loadLibrary(Library library) throws IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
             Statement stmt = conn.createStatement()) {

            ArrayList<Book> books = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book(rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("pages"),
                        rs.getInt("year"));
                book.setAvailable(rs.getBoolean("available"));
                books.add(book);
            }
            library.setBooks(books);

            ArrayList<Student> students = new ArrayList<>();
            rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                students.add(new Student(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("studentNumber"),
                        rs.getString("fieldOfStudy")));
            }
            library.setStudents(students);

            ArrayList<Employee> employees = new ArrayList<>();
            rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                Employee emp = new Employee(rs.getInt("employeeNumber"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setReceiveNum(rs.getInt("receiveNum"));
                emp.setDeliveryNum(rs.getInt("deliveryNum"));
                employees.add(emp);
            }
            library.setEmployees(employees);

            ArrayList<Loan> borrowedBooks = new ArrayList<>();
            ArrayList<Loan> loans = new ArrayList<>();
            ArrayList<Loan> receives = new ArrayList<>();

            rs = stmt.executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                Loan loan = loadLoan(rs, books, students, employees);
                if (loan.getReturnDate() == null) {
                    borrowedBooks.add(loan);
                } else if (loan.getReturnemployee() != null) {
                    receives.add(loan);
                } else {
                    loans.add(loan);
                }
            }

            library.setBorrowedBooks(borrowedBooks);
            library.setLoans(loans);
            library.setReceives(receives);

        } catch (SQLException e) {
            throw new IOException("Database error: " + e.getMessage(), e);
        }
    }

    private Loan loadLoan(ResultSet rs, ArrayList<Book> books,
                          ArrayList<Student> students,
                          ArrayList<Employee> employees) throws SQLException {
        Book book = findBook(
                rs.getString("bookName"),
                rs.getString("bookAuthor"), books);

        Student student = findStudent(
                rs.getInt("studentNumber"), students);

        Employee loanEmployee = findEmployee(
                rs.getInt("employeeNumber"), employees);

        Date loanDate = new Date(rs.getLong("loanDate"));
        Date returnDate = rs.getLong("returnDate") == 0 ?
                null : new Date(rs.getLong("returnDate"));

        Employee returnEmployee = rs.getInt("returnEmployee") == 0 ?
                null : findEmployee(rs.getInt("returnEmployee"), employees);

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
