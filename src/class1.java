import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter a word:");
       Scanner N = new Scanner(System.in);
       String str = N.next();
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }

    }
}