package finalproject;


public class Student extends User {
    private String name;
    private String studentId;
    private boolean isActive;

    public Student(String name, String studentId, String username, String password) {
        super(username, password);
        this.name = name;
        this.studentId = studentId;
        this.isActive = true;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toFileString() {
        return name + "|" + studentId + "|" + getUsername() + "|" + getPassword() + "|" + isActive;
    }

    public static Student fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 5) {
            Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
            student.setActive(Boolean.parseBoolean(parts[4]));
            return student;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + getUsername() +
                " | Status: " + (isActive ? "Active" : "Inactive");
    }
}