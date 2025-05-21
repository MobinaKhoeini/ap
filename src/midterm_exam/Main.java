package midterm_exam;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Laptop laptop1 = new Laptop(30_000_000 , "black", "ASUS", "256G");
        Laptop laptop2 = new Laptop(50_000_000 , "gray", "ASUS", "128G");
        Case case1 = new Case(20_000_000, "black" , true, "small");
        Case case2 = new Case(10_000_000, "black" , false, "big");
        shop.addLaptop(laptop1);
        shop.addLaptop(laptop2);
        shop.addCase(case1);
        shop.addCase(case2);
        shop.printLaptops();
        shop.printCases();
    }

}
