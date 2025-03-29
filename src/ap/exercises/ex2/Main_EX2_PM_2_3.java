package ap.exercises.ex2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_3 {
    public static void main(String[] args) {
        int k = 0;
        char[][] Arr = new char[k + 2][k + 2];
        int score = 0 , points = 0;
        int n = 1, m = 1;
        int e = 1, d = 1;
        long start = 0;
        int a = 0;
        File saveFile = new File("D:/save.txt");
        if (saveFile.exists()) {
            try (Scanner fileScanner = new Scanner(new FileReader(saveFile))) {
                k = fileScanner.nextInt();
                Arr = new char[k + 2][k + 2];
                score = fileScanner.nextInt();
                n = fileScanner.nextInt();
                m = fileScanner.nextInt();
                start = fileScanner.nextLong();
                points = fileScanner.nextInt();
                fileScanner.nextLine();
                for (int i = 0; i < k + 2; i++) {
                    String line = fileScanner.nextLine().trim();
                    for (int j = 0; j < k + 2; j++) {
                        if (j < line.length()) {
                            Arr[i][j] = line.charAt(j);
                        } else {
                            Arr[i][j] = ' ';
                        }
                    }
                }
                Arr[n][m] = 'X';
            printArray(Arr, k);
            } catch (Exception exception) {
                System.out.println("error loading game");
                Arr = null;
            }
        }
            if (Arr == null) {
                System.out.println("please enter the size of the square:");
                Scanner s = new Scanner(System.in);
                k = s.nextInt();
                n = 1;
                m = 1;
                score = 0;
                 Arr = new char[k + 2][k + 2];
                for (int i = 0; i < k + 2; i++) {
                    for (int j = 0; j < k + 2; j++) {
                        if (i == 0 || i == k + 1 || j == 0 || j == k + 1) {
                            Arr[i][j] = '*';
                        } else {
                            Arr[i][j] = ' ';
                        }
                    }
                }
                Arr[n][m] = 'X';
                System.out.println("please enter the number of points:");
                Scanner scanner = new Scanner(System.in);
                int c = scanner.nextInt();
                points = c;
                int emptySpace = k * k;
                while (c > emptySpace) {
                    System.out.println("please enter the number of points again:");
                    scanner = new Scanner(System.in);
                    c = scanner.nextInt();
                }
                while (c > 0) {
                    Random rand = new Random();
                    int row = rand.nextInt(k + 2);
                    int column = rand.nextInt(k + 2);
                    if (row != 0 && column != 0 && row != k + 1 && column != k + 1 && Arr[row][column] == ' ') {
                        Arr[row][column] = '.';
                        c--;
                    }
                }
                start = System.currentTimeMillis();
                printArray(Arr, k);
            }
                System.out.println("your score:" + score);

                Arr[n][m] = ' ';

                System.out.println("use 'w' for up, 'a' for left, 's' for down, 'd' for right");
                System.out.println("game finishes when pressing 'q'");


        String choice;
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
                case "q":{
                    System.out.println("EXIT");
                } break;
                default:
                    System.out.println("WARNING");
            }
            if (Arr[n][m] == '*')
            {
                System.out.println("hitting the game wall");
                n = e; //giving the previous value to n, m
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
                printArray(Arr, k);
                System.out.println("your score:" + score);
                Arr[n][m] = ' ';
            }
            saveGame(Arr, k, score, n, m, start, points);
            if (score == points) {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("time spent:" + timeElapsed);
                choice = "q";
            }
        } while (!choice.equals("q"));
    }
    private static void saveGame(char[][] Arr, int k, int score, int n, int m, long start , int points){
        try(PrintWriter writer = new PrintWriter(new FileWriter("D:/save.txt"))){
            writer.println(k);
            writer.println(score);
            writer.println(n);
            writer.println(m);
            writer.println(start);
            writer.println(points);
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    writer.print(Arr[i][j]);
                }
                writer.println();
            }
        }catch (Exception e) {
            System.out.println("error saving the game");
        }
    }
    private static void printArray(char[][] Arr, int k) {
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.println(" ");
        }
    }
}
