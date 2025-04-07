package ap.exercises.ex2;

import java.io.*;

public class Main_EX3_LM_1_2 {
    public static void main(String[] args) {
        Book[] books = {
                new Book("Pride and Prejudice", "Jane Austen", 259, 1813),
                new Book("Pulp", "Charles Bukowski", 208, 1994),
                new Book("Harry Potter", "JK Rowling", 500, 1997),
                new Book("war and peace", "Leo Tolstoy", 1352, 1867)
        };
        Student[] students = {
                new Student("Sahar", "Hedayati", 401, "Mathematics"),
                new Student("Mahya", "Ahmadi", 404, "Chemistry engineering"),
                new Student("Ahmad", "hassani", 401, "Electrical engineering")
        };

        // Write and read books
        writeBooksToFile(books, "D:/saveBooks.txt");
        Book[] writtenBooks = readBooksFromFile("D:/saveBooks.txt", books.length);
        for (Book book : writtenBooks) {
            System.out.println(book);
        }

        // Write and read students
        writeStudentsToFile(students, "D:/saveStudents.txt");
        Student[] writtenStudents = readStudentsFromFile("D:/saveStudents.txt", students.length);
        for (Student student : writtenStudents) {
            System.out.println(student);
        }
    }

    public static void writeBooksToFile(Book[] books, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Book book : books) {
                writer.write(book.name + "," + book.author + "," + book.pageNumber + "," + book.publicationYear);
                writer.newLine();
            }
            System.out.println("Books written successfully");
        } catch (IOException e) {
            System.out.println("Error writing books to file: " + e.getMessage());
        }
    }

    public static Book[] readBooksFromFile(String fileName, int size) {
        Book[] books = new Book[size];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < size; i++) {
                String line = reader.readLine();
                if (line != null) {
                    String[] parts = line.split(",");
                    books[i] = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books from file: " + e.getMessage());
        }
        return books;
    }

    public static void writeStudentsToFile(Student[] students, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.firstName + "," + student.lastName + "," +
                        student.studentNumber + "," + student.fieldOfStudy);
                writer.newLine();
            }
            System.out.println("Students written successfully");
        } catch (IOException e) {
            System.out.println("Error writing students to file: " + e.getMessage());
        }
    }

    public static Student[] readStudentsFromFile(String fileName, int size) {
        Student[] students = new Student[size];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < size; i++) {
                String line = reader.readLine();
                if (line != null) {
                    String[] parts = line.split(",");
                    students[i] = new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students from file: " + e.getMessage());
        }
        return students;
    }
}