package midproject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Library library = new Library("Beheshti");
        Manager manager = new Manager("Raha", "Alipour", "Master's degree");
        library.menuInitialize(library);

    }
}
