package midproject;

import java.util.Scanner;

public class InputProcessing {
    Scanner scanner = new Scanner(System.in);
    public Book addingBooks() {
        System.out.println("the name of book:");
        String name = scanner.next();
        System.out.println("the author:");
        String author = scanner.next();
        System.out.println("the page number:");
        int pageNumber = scanner.nextInt();
        System.out.println("the publication year:");
        int publicationYear = scanner.nextInt();
        Book book = new Book(name, author, pageNumber, publicationYear);
        return book;
    }
    public Student signingUp() {
        System.out.println("please input your information");
        System.out.println("your first name:");
        String firstName = scanner.next();
        System.out.println("your last name:");
        String lastName = scanner.next();
        System.out.println("your field of study:");
        String fieldOfStudy = scanner.next();
        System.out.println("your student number:");
        int studentNumber = scanner.nextInt();
        Student student = new Student(firstName, lastName, studentNumber, fieldOfStudy);
        return student;
    }
    public String searchBooks() {
        System.out.println("please input the name of book:");
        String name = scanner.next();
        return name;
    }
    public Student studentLogin() {
        System.out.println("please input your information");
        System.out.println("your first name:");
        String firstName = scanner.next();
        System.out.println("your last name:");
        String lastName = scanner.next();
        System.out.println("your field of study:");
        String fieldOfStudy = scanner.next();
        System.out.println("your student number:");
        int studentNumber = scanner.nextInt();
        Student student = new Student(firstName, lastName, studentNumber, fieldOfStudy);
        return student;
    }
    public Employee employeeLogin() {
        System.out.println("please input your information");
        System.out.println("your employee number:");
        int employeeNumber = scanner.nextInt();
        Employee employee = new Employee(employeeNumber);
        return employee;
    }
    public Employee addingEmployee() {
        System.out.println("please input employee's information");
        System.out.println("employee number:");
        int employeeNumber = scanner.nextInt();
        Employee employee = new Employee(employeeNumber);
        return employee;
    }
    public String getEmployeeFirstName() {
        System.out.println("your first name:");
        String firstName = scanner.next();
        return firstName;
    }
    public String getEmployeeLastName() {
        System.out.println("your last name:");
        String lastName = scanner.next();
        return lastName;
    }

}
