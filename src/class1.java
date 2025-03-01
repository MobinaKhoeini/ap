import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter the income:");
        Scanner income = new Scanner(System.in);
        double salary = income.nextDouble();
        double tax;
        if (salary <= 50_000)
        {
            tax = salary * 0.01;
        }
        else if (salary > 50_000 && salary <= 75_000)
        {
            tax = (50_000 * 0.01) + (salary * 0.02);
        }
        else if (salary > 75_000 && salary <= 100_000)
        {
            tax = (50_000 * 0.01) + (75_000 * 0.02) + (salary * 0.03);
        }
        else if (salary > 100_000 && salary <= 250_000)
        {
            tax = (50_000 * 0.01) + (75_000 * 0.02) + (100_000 * 0.03) + (salary * 0.04);
        }
        else if (salary > 250_000 && salary <= 500_000)
        {
            tax = (50_000 * 0.01) + (75_000 * 0.02) + (100_000 * 0.03) + (250_000 * 0.04) + (salary * 0.05);
        }
        else
        {
            tax = (50_000 * 0.01) + (75_000 * 0.02) + (100_000 * 0.03) + (250_000 * 0.04) + (500_000 * 0.05) + (salary * 0.06);
        }
        System.out.println("your tax would be " + tax);
    }
}
