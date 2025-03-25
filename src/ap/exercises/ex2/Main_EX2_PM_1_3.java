package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_3 {
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
        System.out.println("please enter the number of points:");
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int emptySpace = k * k;
        while (c > emptySpace){
            System.out.println("please enter the number of points again:");
            scanner = new Scanner(System.in);
            c = scanner.nextInt();
        }
        while (c > 0){
            Random rand = new Random();
            int row = rand.nextInt(k + 2);
            int column = rand.nextInt(k + 2);
            if (row != 0 && column !=0 && row != k + 1 && column != k + 1 && Arr[row][column]== ' ') {
                Arr[row][column] = '.';
                c--;
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