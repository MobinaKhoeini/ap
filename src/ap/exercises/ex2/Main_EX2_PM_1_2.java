package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_2 {
    public static void main(String[] args) {
        System.out.println("please enter the size of the square:");
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        char[][] Arr = new char[k+2][k+2];
        for (int i = 0; i < k + 2 ; i++) {
            for (int j = 0; j < k + 2; j++) {
                if (i == 0 || i == k + 1 || j == 0 || j == k + 1)
                {
                    Arr[i][j] = '*';
                }
                else {
                    Arr[i][j] = ' ';
                }
            }
        }
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.println(" ");
        }
    }
}
