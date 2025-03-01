import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        int Sum = 0;
        for (int i = 2; i < 101 ; i++)
        {
            if(i % 2 == 0)
            {
                Sum += i;
            }
        }
        System.out.println("the sum is " + Sum);
    }
}
