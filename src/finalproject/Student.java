package finalproject;

public class Student extends User {
    private String name;
    private String studentId;

    public Student(String name, String studentId, String username, String password) {
        super(username, password);
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toFileString() {
        return name + "|" + studentId + "|" + getUsername() + "|" + getPassword();
    }

    public static Student fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 4) {
            return new Student(parts[0], parts[1], parts[2], parts[3]);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + getUsername();
    }
}