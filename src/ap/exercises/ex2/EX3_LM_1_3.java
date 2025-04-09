package ap.exercises.ex2;

public class EX3_LM_1_3 {
    public static class Student {
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
            return "Student {" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", studentNumber=" + studentNumber +
                    ", fieldOfStudy='" + fieldOfStudy + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        Student[] students = {
                new Student("Sahar", "Hedayati", 401, "Mathematics"),
                new Student("Mahya", "Ahmadi", 404, "Chemistry engineering"),
                new Student("Ahmad", "hassani", 401, "Electrical engineering")
        };
        searchStudentByName(students, "sahar");
    }
    public static void searchStudentByName(Student[] students, String name) {
        boolean findStudent = false;
        for (Student student : students) {
            if (student.firstName.equalsIgnoreCase(name) || student.lastName.equalsIgnoreCase(name)) {
                System.out.println(student);
                findStudent = true;
            }
        }
        if (!findStudent) {
            System.out.println("no students found !");
        }
    }
}
