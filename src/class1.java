import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter the number:");
        Scanner Num = new Scanner(System.in);
        float Number = Num.nextFloat();
        if (Number == 0)
        {
            System.out.println("zero");
        }
        else if (Number > 0)
        {
            System.out.println("positive");
        }
        else if (Number < 0)
        {
            System.out.println("nagative");
        }
        if (Math.abs(Number) < 1)
        {
            System.out.println("small");
        }
        else if (Math.abs(Number) > 1_000_000)
        {
            System.out.println("large");
        }
    }
}
