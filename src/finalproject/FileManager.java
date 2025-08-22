package finalproject;

import java.io.*;
import java.util.List;

public class FileManager {
    private static final String STUDENT_FILE = "D:/savingLibrary.txt";
    private static final String BOOK_FILE = "D:/savingBooks.txt";

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
            System.out.println("no existing file.");
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
}