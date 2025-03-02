import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
       Scanner Num = new Scanner(System.in);
       int Number = Num.nextInt();
       int Remain = 0;
       while (Number > 0)
       {
           Remain = Number % 2;
           Number = Number / 2;
           System.out.println(Remain);
       }

    }
}