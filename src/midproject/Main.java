package midproject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Library library = new Library("Beheshti");
        Manager manager = new Manager("Raha", "Alipour", "Master's degree");
        FileHandling fileHandeling = new FileHandling();
        fileHandeling.loadLibrary(library);
        Menu menu = new Menu();
        String roleType;
        int choice, option;
        do {
        System.out.println("please choose your role:");
        System.out.println("1.Manager");
        System.out.println("2.Student");
        System.out.println("3.Employee");
        System.out.println("4.Exit");
        Scanner scanner = new Scanner(System.in);
        roleType = scanner.nextLine();
        boolean back = false;
        while (!back) {
            if (roleType.equals("1")) {
                menu.managerMenu();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        library.addingEmployee();
                        fileHandeling.saveLibrary(library);

                        break;
                    case 2:
                        library.printOverdueLoans();
                        fileHandeling.saveLibrary(library);
                        break;
                    case 5:
                        back = true;
                }
            }
            if (roleType.equals("2")) {
                menu.studentMenu1();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        library.signingUp();
                        fileHandeling.saveLibrary(library);
                        break;
                    case 2: {
                        if (library.studentLogin()) {
                            menu.studentMenu2();
                            option = scanner.nextInt();
                            if (option == 1) {
                                library.searchBooks();
                            }
                            if (option == 5) {
                                back = true; break;
                            }
                        }
                    }
                    case 3: {
                        back = true; break;
                    }
                }
            }
            if (roleType.equals("3")) {
                menu.employeeMenu1();
                choice = scanner.nextInt();
                if (choice == 1) {
                    if (library.employeeLogin() != null) {
                        menu.employeeMenu2();
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                library.addingBooks();
                                fileHandeling.saveLibrary(library);
                                break;
                            case 2:
                                library.completingEmployeeInfo();
                                fileHandeling.saveLibrary(library);
                                break;
                            case 3:
                                library.changingEmployeeInfo();
                                fileHandeling.saveLibrary(library);
                                break;
                            case 4:
                                back = true;
                        }
                    }
                }
                if (choice == 2) {
                    back = true;
                }
            }
            if (roleType.equals("4")) {
                return;
            }
        }
        }while (true);
    }

}
