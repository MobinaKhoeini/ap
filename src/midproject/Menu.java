package midproject;

public class Menu {
    private String userType;
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public void primaryMenu() {
        System.out.println("please choose your role:");
        System.out.println("1.Manager");
        System.out.println("2.Student");
        System.out.println("3.Employee");
        System.out.println("4.Exit");
    }
    public void managerMenu() {
        System.out.println("please choose your required function:");
        System.out.println("1.adding a new employee");
        System.out.println("2.getting a list of books that were returned late");
        System.out.println("3.getting a list of 10 top popular books");
        System.out.println("4.getting the number of delivery and receiving books for each employee");
        System.out.println("5.getting back");
    }
    public void studentMenu1() {
        System.out.println("please choose your required function:");
        System.out.println("1.signing up");
        System.out.println("2.logging in");
        System.out.println("3.getting back");
    }
    public void studentMenu2() {
        System.out.println("please choose your required function:");
        System.out.println("1.searching a book");
        System.out.println("2.borrowing a book");
        System.out.println("3.returning a book");
        System.out.println("4.getting a list of borrowed books that are not returned");
        System.out.println("5.getting back");
    }
    public void employeeMenu1() {
        System.out.println("please choose your required function:");
        System.out.println("1.logging in");
        System.out.println("2.getting back");
    }
    public void employeeMenu2() {
        System.out.println("please choose your required function:");
        System.out.println("1.adding a book");
        System.out.println("2.completing information");
        System.out.println("3.changing information");
        System.out.println("4.accepting loan request(s)");
        System.out.println("5.accepting delivery request(s)");
        System.out.println("6.getting back");
    }
}
