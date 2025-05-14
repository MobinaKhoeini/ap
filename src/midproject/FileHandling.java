package midproject;

import java.io.*;
import java.util.ArrayList;

public class FileHandling {
    private final String FIlE = "D:/saveLibrary.txt";

    public void saveLibrary(Library library) {
        try (FileWriter writer = new FileWriter(FIlE)) {
            writer.write("=== BOOKS ===\n");
            for (Book book : library.getBooks()) {
                writer.write(book.getName() + "," + book.getAuthor() + ","
                        + book.getPageNumber() + "," + book.getPublicationYear() + "\n");
            }
            writer.write("=== STUDENTS ===\n");
            for (Student student : library.getStudents()) {
                writer.write(student.getFirstName() + "," + student.getLastName() + ","
                        + student.getStudentNumber() + "," + student.getFieldOfStudy() + "\n");
            }
            writer.write("=== EMPLOYEES ===\n");
            for (Employee employee : library.getEmployees()) {
                writer.write(employee.getFirstName() + "," + employee.getLastName() + ","
                        + employee.getEmployeeNumber() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error in saving file" + e.getMessage());
        }
    }

    public void loadLibrary(Library library) throws IOException {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FIlE))) {
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
                        books.add(new Book(data[0], data[1],
                                Integer.parseInt(data[2]),
                                Integer.parseInt(data[3])));
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
                        employees.add(emp);
                        break;
                }
            }
        }catch (IOException e) {
            System.err.println("Error in loading file" + e.getMessage());
        }
        library.setBooks(books);
        library.setStudents(students);
        library.setEmployees(employees);
    }
}

