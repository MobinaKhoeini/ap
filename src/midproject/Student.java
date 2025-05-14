package midproject;

import java.util.Date;

public class Student {
    private String firstName;
    private String lastName;
    private int studentNumber;
    private String fieldOfStudy;
    private Date joinDate;
    public Student(String firstName, String lastName, int studentNumber, String fieldOfStudy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.fieldOfStudy = fieldOfStudy;
        this.joinDate = new Date();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentNumber=" + studentNumber +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
