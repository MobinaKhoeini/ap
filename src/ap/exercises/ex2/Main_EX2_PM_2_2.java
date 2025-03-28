package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2 {
    public static void main(String[] args) {
        System.out.println("please enter the size of the square:");
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        String choice;
        int n = 1, m = 1;
        int score=0;
        int e = 1, d = 1;
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
        System.out.println("please enter the number of points:");
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int points = c;
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
        long start = System.currentTimeMillis();
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println("your score:" + score);
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
            if (Arr[n][m] == '*')
            {
                System.out.println("hitting the game wall");
                n = e;
                m = d;
            }
            else {
                if (Arr[n][m] == '.')
                {
                    score++;
                }
                Arr[n][m] = 'X';
                e = n;
                d = m;
                for (int i = 0; i < k + 2; i++) {
                    for (int j = 0; j < k + 2; j++) {
                        System.out.print(Arr[i][j]);
                    }
                    System.out.println(" ");
                }
                System.out.println("your score:" + score);
                Arr[n][m] = ' ';
            }
            if (score == points) {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("time spent:" + timeElapsed);
                choice = "q";
            }
        } while (!choice.equals("q"));
    }
}

