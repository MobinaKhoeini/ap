package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_1 {
    public static void main(String[] args) {
        System.out.println("please enter the size of the square:");
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        for (int i = 0; i < k + 2; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < k; i++) {
            System.out.print("*");
            for (int j = 0; j < k; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
        for (int i = 0; i < k + 2; i++) {
            System.out.print("*");
        }
    }
}

