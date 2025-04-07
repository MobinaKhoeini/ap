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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/saveBooks.txt"))) {
            for (Book book : books) {
                writer.write(book.name + "," + book.author + "," + book.pageNumber + "," + book.publicationYear);
                writer.newLine();
            }
            writer.close();
            System.out.println("books written successfully");

        } catch (IOException e) {
            System.out.println("error writing books to file");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/saveStudents.txt"))) {
            for (Student student : students) {
                writer.write(student.firstName + " " + student.lastName + "," + student.studentNumber + "," + student.fieldOfStudy);
                writer.newLine();
            }
            System.out.println("students written successfully");
            writer.close();

        } catch (IOException e) {
            System.out.println("error writing students to file");
        }
    }
}
