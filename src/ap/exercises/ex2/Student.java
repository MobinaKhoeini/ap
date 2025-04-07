package ap.exercises.ex2;

public class Student {
    public String firstName;
    public String lastName;
    public int studentNumber;
    public String fieldOfStudy;
    public Student(String firstName, String lastName, int studentNumber, String fieldOfStudy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.fieldOfStudy = fieldOfStudy;
    }
    @Override
    public String toString() {
        return "Book{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentNumber=" + studentNumber +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                '}';
    }
}
