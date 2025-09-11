package finalproject;

public class Employee extends User {
    public Employee(String username, String password) {
        super(username, password);
    }

    public String toFileString() {
        return getUsername() + "|" + getPassword();
    }

    public static Employee fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 2) {
            return new Employee(parts[0], parts[1]);
        }
        return null;
    }
}
