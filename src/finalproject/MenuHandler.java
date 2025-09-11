package finalproject;

import java.util.List;
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
            System.out.println("2. Search and Edit Books");
            System.out.println("3. view Loan Requests");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 5);

            switch (choice) {
                case 1:
                    handleAddNewBook();
                    break;
                case 2:
                    handleSearchAndEditBooks();
                    break;
                case 3:
                    handleReviewLoanRequests();
                    break;
                case 4:
                    handleChangePassword();
                    break;
                case 5:
                    currentEmployee = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleReviewLoanRequests() {
        System.out.println("\n--- view Loan Requests ---");

        List<Loan> pendingLoans = librarySystem.getPendingLoansForReview();

        if (pendingLoans.isEmpty()) {
            System.out.println("No loan requests for today or yesterday.");
            return;
        }

        System.out.println("\n Loan Requests:");
        for (int i = 0; i < pendingLoans.size(); i++) {
            Loan loan = pendingLoans.get(i);
            System.out.println((i + 1) + ". " + loan);
        }

        System.out.print("Select request number to review (0 to cancel): ");
        int requestChoice = getIntInput(0, pendingLoans.size());

        if (requestChoice == 0) {
            return;
        }

        Loan selectedLoan = pendingLoans.get(requestChoice - 1);
        reviewLoanRequest(selectedLoan);
    }

    private void reviewLoanRequest(Loan loan) {
        System.out.println("\n--- Review Loan Request ---");
        System.out.println("Request Details: " + loan);

        System.out.println("\n1. Approve Request");
        System.out.println("2. Reject Request");
        System.out.println("3. Cancel");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput(1, 3);

        switch (choice) {
            case 1:
                if (librarySystem.approveLoanRequest(loan.getStudentUsername(), loan.getBookIsbn())) {
                    System.out.println("Loan request approved successfully!");
                    System.out.println("Student can now borrow the book from the library.");
                } else {
                    System.out.println("Failed to approve loan request.");
                }
                break;
            case 2:
                if (librarySystem.rejectLoanRequest(loan.getStudentUsername(), loan.getBookIsbn())) {
                    System.out.println("Loan request rejected.");
                } else {
                    System.out.println("Failed to reject loan request.");
                }
                break;
            case 3:
                System.out.println("Review is not completed.");
                break;
        }
    }
    private void handleSearchAndEditBooks() {
        System.out.println("\n--- Search and Edit Books ---");

        System.out.print("Enter book title to search: ");
        String searchTitle = scanner.nextLine();

        List<Book> foundBooks = librarySystem.searchBooksByTitle(searchTitle);

        if (foundBooks.isEmpty()) {
            System.out.println("No books found with title: " + searchTitle);
            return;
        }


        System.out.println("\nFound Books:");
        for (int i = 0; i < foundBooks.size(); i++) {
            System.out.println((i + 1) + ". " + foundBooks.get(i));
        }

        System.out.print("enter book number / enter 0 to return: ");
        int bookChoice = getIntInput(0, foundBooks.size());

        if (bookChoice == 0) {
            return;
        }

        Book selectedBook = foundBooks.get(bookChoice - 1);
        editBookDetails(selectedBook);
    }
    private void editBookDetails(Book book) {
        System.out.println("\n--- Edit Book ---");
        System.out.println("Current information: " + book);

        System.out.print("New title (press Enter to keep unchanged): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            book.setTitle(newTitle);
        }

        System.out.print("New author (press Enter to keep unchanged): ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.isEmpty()) {
            book.setAuthor(newAuthor);
        }

        System.out.print("New publication year (press Enter to keep unchanged): ");
        String yearInput = scanner.nextLine();
        if (!yearInput.isEmpty()) {
            try {
                int newYear = Integer.parseInt(yearInput);
                book.setPublicationYear(newYear);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format. Year not changed.");
            }
        }
        System.out.print("Change availability? (y/n): ");
        String change = scanner.nextLine();
        if (change.equalsIgnoreCase("y")) {
            book.setAvailable(!book.isAvailable());
        }

        librarySystem.saveBooks();
        System.out.println("Book updated successfully!");
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