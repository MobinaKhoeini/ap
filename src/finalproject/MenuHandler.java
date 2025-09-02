package finalproject;

import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentUser;
    private boolean isManagerLoggedIn = false;
    private Employee currentEmployee;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentUser = null;
        this.isManagerLoggedIn = false;
        this.currentEmployee = null;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Enter as Student");
            System.out.println("2. Enter as Guest");
            System.out.println("3. Enter as Manager");
            System.out.println("4. Enter as Employee");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    displayStudentAccessMenu();
                    break;
                case 2:
                    displayGuestMenu();
                    break;
                case 3:
                    displayManagerMenu();
                    break;
                case 4:
                    displayEmployeeAccessMenu();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
            System.out.println("___________________________");
        }
    }

    private void displayStudentAccessMenu() {
        while (true) {
            System.out.println("\n=== Student Access ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    handleStudentRegistration();
                    break;
                case 2:
                    handleStudentLogin();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    private void displayGuestMenu() {

        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("1. View Registered Student Count");
            System.out.println("2. Search Books");
            System.out.println("3. View Library Statistics");
            System.out.println("4. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    displayStudentCount();
                    break;
                case 2:
                    guestBookSearch();
                case 3:
                    displayLibraryStatistics();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    private void displayEmployeeAccessMenu() {
        while (true) {
            System.out.println("\n=== Employee Access ===");
            System.out.println("1. Login");
            System.out.println("2. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 2);

            switch (choice) {
                case 1:
                    handleEmployeeLogin();
                    break;
                case 2:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    private void displayManagerMenu() {
        System.out.println("\n=== Manager Access ===");
        System.out.println("1. Login");
        System.out.println("2. Back to Main Menu");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput(1, 2);

        switch (choice) {
            case 1:
                handleManagerLogin();
                break;
            case 2:
                System.out.println("Returning to main menu...");
                return;
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }
    private void handleManagerLogin() {
        System.out.println("\n--- Manager Login ---");

        System.out.print("Username: (admin) ");
        String username = scanner.nextLine();

        System.out.print("Password: (123) ");
        String password = scanner.nextLine();
        if (librarySystem.authenticateManager(username, password)) {
            System.out.println("Login successful! Welcome, Manager.");
            isManagerLoggedIn = true;
            displayLoggedInManagerMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    private void displayLoggedInManagerMenu() {
        System.out.println("\n=== Manager Dashboard ===");
        System.out.println("1. Register New Employee");
        System.out.println("2. Logout");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput(1, 2);

        switch (choice) {
            case 1:
                handleEmployeeRegistration();
                break;
            case 2:
                isManagerLoggedIn = false;
                System.out.println("Logged out successfully.");
                return;
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }
    private void handleEmployeeRegistration() {
        System.out.println("\n--- New Employee Registration ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerEmployee(username, password);
        System.out.println("Employee registered successfully!");
    }
    private void handleEmployeeLogin() {
        System.out.println("\n--- Employee Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentEmployee = librarySystem.authenticateEmployee(username, password);

        if (currentEmployee != null) {
            System.out.println("Login successful! Welcome, " + currentEmployee.getUsername());
            displayLoggedInEmployeeMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    private void displayLoggedInEmployeeMenu() {
        while (currentEmployee != null) {
            System.out.println("\n=== Employee Dashboard ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Change Password");
            System.out.println("3. Logout");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    handleAddNewBook();
                    break;
                case 2:
                    handleChangePassword();
                    break;
                case 3:
                    currentEmployee = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    private void handleAddNewBook() {
        System.out.println("\n--- Add New Book ---");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Year: ");
        int year = getIntInput(1000, 9999);

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        librarySystem.addBook(title, author, year, isbn);

    }
    private void handleChangePassword() {
        System.out.println("\n--- Change Password ---");

        System.out.print("Current Password: ");
        String currentPassword = scanner.nextLine();

        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Confirm New Password: ");
        String newPassword2 = scanner.nextLine();

        if (!currentPassword.equals(currentEmployee.getPassword())) {
            System.out.println("Current password is incorrect.");
            return;
        }

        if (!newPassword.equals(newPassword2)) {
            System.out.println("New passwords do not match.");
            return;
        }

        if (librarySystem.changeEmployeePassword(currentEmployee.getUsername(), newPassword)) {
            System.out.println("Password changed successfully!");
            currentEmployee.setPassword(newPassword);
        } else {
            System.out.println("Failed to change password.");
        }
    }

    private void guestBookSearch() {
        System.out.println("\n--- Search Books by Title ---");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        librarySystem.searchBooksByTitleForGuest(title);
    }

    private void displayStudentCount() {
        int studentCount = librarySystem.getStudentCount();
        System.out.println("Total registered students: " + studentCount);
    }
    private void displayLibraryStatistics() {
        System.out.println("\n--- Library Statistics ---");
        System.out.println("Total students in university: " + librarySystem.getUniversityStudentCount());
        System.out.println("Total books: " + librarySystem.getTotalBooksCount());
        System.out.println("Total loans: " + librarySystem.getTotalLoansCount());
        System.out.println("number of lately borrowed books: " + librarySystem.getActiveLoansCount());
    }

    private void handleStudentRegistration() {
        System.out.println("\n--- New Student Registration ---");

        System.out.print("Student name: ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void handleStudentLogin() {
        System.out.println("\n--- Student Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = librarySystem.authenticateStudent(username, password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() {
        while (currentUser != null) {
            System.out.println("\n=== Student Dashboard ===");
            System.out.println("1. View My Information");
            System.out.println("2. Edit My Information");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. View Available Books");
            System.out.println("6. Search Books");
            System.out.println("7. View My Loans");
            System.out.println("8. Logout");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 9);

            switch (choice) {
                case 1:
                    System.out.println("\n--- My Information ---");
                    System.out.println(currentUser);
                    break;
                case 2:
                    librarySystem.editStudentInformation(currentUser);
                    break;
                case 3:
                    librarySystem.borrowBook(currentUser);
                    break;
                case 4:
                    librarySystem.returnBook(currentUser);
                    break;
                case 5:
                    librarySystem.displayAvailableBooks();
                    break;
                case 6:
                    librarySystem.searchBooks();
                    break;
                case 7:
                    librarySystem.viewMyLoans(currentUser);
                    break;
                case 8:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}