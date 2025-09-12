package finalproject;

public class Employee extends User {
    private int booksAdded;
    private int booksLoaned;
    private int booksReturned;

    public Employee(String username, String password) {
        super(username, password);
        this.booksAdded = 0;
        this.booksLoaned = 0;
        this.booksReturned = 0;
    }

    public int getBooksAdded() {
        return booksAdded;
    }

    public int getBooksLoaned() {
        return booksLoaned;
    }

    public int getBooksReturned() {
        return booksReturned;
    }

    public void incrementBooksAdded() {
        this.booksAdded++;
    }

    public void incrementBooksLoaned() {
        this.booksLoaned++;
    }

    public void incrementBooksReturned() {
        this.booksReturned++;
    }

    @Override
    public String toFileString() {
        return getUsername() + "|" + getPassword() + "|" +
                booksAdded + "|" + booksLoaned + "|" + booksReturned;
    }

    public static Employee fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 5) {
            Employee employee = new Employee(parts[0], parts[1]);
            employee.booksAdded = Integer.parseInt(parts[2]);
            employee.booksLoaned = Integer.parseInt(parts[3]);
            employee.booksReturned = Integer.parseInt(parts[4]);
            return employee;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() +
                " | Books Added: " + booksAdded +
                " | Books Loaned: " + booksLoaned +
                " | Books Returned: " + booksReturned;
    }

}
