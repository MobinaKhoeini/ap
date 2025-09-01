package finalproject;

public class Employee {
    private String username;
    private String password;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toFileString() {
        return username + "|" + password;
    }

    public static Employee fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 2) {
            return new Employee(parts[0], parts[1]);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }
}

