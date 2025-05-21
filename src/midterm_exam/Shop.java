package midterm_exam;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Laptop> laptops;
    private ArrayList<Case> cases;

    public Shop() {
        this.laptops = new ArrayList<>();
        this.cases = new ArrayList<>();
    }

    public void addLaptop(Laptop laptop1) {
        laptops.add(laptop1);
    }

    public void addCase(Case case1) {
        cases.add(case1);
    }

    public void printLaptops() {
        System.out.println("laptops:");
        for (Laptop laptop : laptops) {
            //System.out.println("price:" + laptop.getPrice() + " color:"+ laptop.getColor() + " brand:" + laptop.getBrand() + " memory:" + laptop.getMemory());
            System.out.println(laptop);
        }
    }

    public void printCases() {
        System.out.println("cases:");
        for (Case case1 : cases) {
            //System.out.println("price:" + case1.getPrice() + " color:" + case1.getColor() +" stock:" + case1.isStock() + " size:" + case1.getSize());
            System.out.println(case1);
        }
    }
}

