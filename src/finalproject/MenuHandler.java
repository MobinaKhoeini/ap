package finalproject;


import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentUser;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentUser = null;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Enter as Student");
            System.out.println("2. Enter as Guest");
            System.out.println("3. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    displayStudentAccessMenu();
                    break;
                case 2:
                    displayGuestMenu();
                    break;
                case 3:
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
            System.out.println("2. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 2);

            switch (choice) {
                case 1:
                    displayStudentCount();
                    break;
                case 2:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void displayStudentCount() {
        int studentCount = librarySystem.getStudentCount();
        System.out.println("Total registered students: " + studentCount);
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