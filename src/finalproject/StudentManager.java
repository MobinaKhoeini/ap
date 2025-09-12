package finalproject;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private final IFileManager fileManager;

    public StudentManager(IFileManager fileManager) {
        if (fileManager == null) {
            throw new IllegalArgumentException("FileManager cannot be null");
        }
        this.fileManager = fileManager;
        this.students = fileManager.loadStudents();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Student newStudent = new Student(name, studentId, username, password);
        students.add(newStudent);
        fileManager.saveStudents(students);
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateStudent(String username, String password) {
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void displayStudents() {
        System.out.println("\n--- List of Registered Students ---");

        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }
    public Student getStudentByUsername(String username) {
        return students.stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    public boolean setStudentStatus(String username, boolean isActive) {
        Student student = getStudentByUsername(username);
        if (student != null) {
            student.setActive(isActive);
            fileManager.saveStudents(students);
            return true;
        }
        return false;
    }
    public void displayStudentsWithStatus() {
        System.out.println("\n--- List of Registered Students ---");

        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }
    private boolean isUsernameTaken(String username) {
        return students.stream().anyMatch(s -> s.getUsername().equals(username));
    }

    public int getStudentCount() {
        return students.size();
    }
}
