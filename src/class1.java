import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter your number:");
        Scanner N = new Scanner(System.in);
        int Number = N.nextInt();
        int Remain = 0, Sum = 0;
        while (Number > 0) {
            Remain = Number % 10;
            if (Remain % 2 != 0)
            {
                Sum +=Remain;
            }
            Number = Number / 10;
        }
        System.out.println("the sum is " + Sum);
    }
}