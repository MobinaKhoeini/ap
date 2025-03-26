package ap.exercises.ex2;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_1 {
    public static void main(String[] args) {
        System.out.println("please enter the size of the square:");
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        String choice;
        int n = 1, m = 1;
        int c = 1, d = 1;
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
        Arr[n][m] = 'X';
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.println(" ");
        }
        Arr[n][m] = ' ';
        System.out.println("use 'w' for up, 'a' for left, 's' for down, 'd' for right");
        System.out.println("game finishes when pressing 'q'");
        do {
            Scanner input = new Scanner(System.in);
            choice = input.next();
            switch (choice) {
                case "w":{
                    System.out.println("UP");
                    n--;
                } break;
                case "d":{
                    System.out.println("RIGHT");
                    m++;
                } break;
                case "s":{
                    System.out.println("DOWN");
                    n++;
                } break;
                case "a":{
                    System.out.println("LEFT");
                    m--;
                } break;
                default:
                    System.out.println("WARNING");
            }
            if (Arr[n][m] != ' ')
            {
                System.out.println("hitting the game wall");
                n = c;
                m = d;
            }
            else {
                Arr[n][m] = 'X';
                c = n;
                d = m;
                for (int i = 0; i < k + 2; i++) {
                    for (int j = 0; j < k + 2; j++) {
                        System.out.print(Arr[i][j]);
                    }
                    System.out.println(" ");
                }
                Arr[n][m] = ' ';
            }
        } while (!choice.equals("q"));
    }
}

