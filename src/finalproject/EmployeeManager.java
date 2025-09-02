package finalproject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private List<Employee> employees;

    public EmployeeManager() {
        this.employees = FileManager.loadEmployees();
    }

    public void registerEmployee(String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Employee newEmployee = new Employee(username, password);
        employees.add(newEmployee);
        FileManager.saveEmployees(employees);
        System.out.println("Employee registration completed successfully.");
    }

    public Employee authenticateEmployee(String username, String password) {
        return employees.stream()
                .filter(e -> e.getUsername().equals(username) && e.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void displayEmployees() {
        System.out.println("\n--- List of Employees ---");

        if (employees.isEmpty()) {
            System.out.println("No employees have been registered yet.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    public boolean changeEmployeePassword(String username, String newPassword) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                employee.setPassword(newPassword);
                FileManager.saveEmployees(employees);
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameTaken(String username) {
        return employees.stream().anyMatch(e -> e.getUsername().equals(username));
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}
