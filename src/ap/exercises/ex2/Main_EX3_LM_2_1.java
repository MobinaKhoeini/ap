package ap.exercises.ex2;

import java.io.*;
import java.util.Scanner;
public class Main_EX3_LM_2_1 {
    public static void main(String[] args) {
            Book[] books = {
                    new Book("Pride and Prejudice", "Jane Austen", 259, 1813),
                    new Book("Animal Farm", "George Orwell", 112, 1945),
                    new Book("Harry Potter", "JK Rowling", 500, 1997)
            };

            Student[] students = {
                    new Student("Sahar", "Hedayati", 401, "Math"),
                    new Student("Mahya", "Ahmadi", 404, "Chemistry")
            };

            students[1].borrowBook(books[1]);
            students[0].borrowBook(books[0]);

            saveLoansToFile(students, "D:/borrowedBooks.txt");
            printBorrowedBooks(students);
            loadLoansFromFile(students, books, "D:/borrowedBooks.txt");
        }

        public static void saveLoansToFile (Student[]students, String filename){
            try (PrintWriter writer = new PrintWriter(filename)) {
                for (Student student : students) {
                    for (int i = 0; i < student.borrowedCount; i++) {
                        writer.println(student.studentNumber + "," + student.borrowedBooks[i].name);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error saving loans: " + e.getMessage());
            }
        }

        public static void loadLoansFromFile (Student[]students, Book[]books, String filename){
            try (Scanner scanner = new Scanner(new File(filename))) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",");
                    int studentId = Integer.parseInt(parts[0]);
                    String bookName = parts[1];
                    Student student = findStudent(students, studentId);
                    Book book = findBook(books, bookName);
                    if (student != null && book != null) {
                        student.borrowBook(book);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading loans: " + e.getMessage());
            }
        }

        public static Student findStudent (Student[]students,int id){
            for (Student student : students) {
                if (student.studentNumber == id) {
                    return student;
                }
            }
            return null;
        }

        public static Book findBook (Book[]books, String name){
            for (Book book : books) {
                if (book.name.equals(name)) {
                    return book;
                }
            }
            return null;
        }

        public static void printBorrowedBooks (Student[]students){
            for (Student student : students) {
                System.out.println(student.firstName + " " + student.lastName + ":");
                for (int i = 0; i < student.borrowedCount; i++) {
                    System.out.println("- " + student.borrowedBooks[i].name);
                }
            }
        }
    }
