package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_5 {
    public static void main(String[] args) {
        System.out.println("please enter the size of the square:");
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
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
        while (true) {
            Random random = new Random();
            int rand = random.nextInt(4);
            switch (rand) {
                case 0:{
                    System.out.println("UP");
                    n--;
                } break;
                case 1:{
                    System.out.println("RIGHT");
                    m++;
                } break;
                case 2:{
                    System.out.println("DOWN");
                    n++;
                } break;
                case 3:{
                    System.out.println("LEFT");
                    m--;
                } break;
            }
            if (m == 0 || n == 0 || m == k - 1 || n == k - 1)
            {
                System.out.println("hitting the game wall");
                 n = c;
                 m = d;
            }
            else{
                Arr[n][m] = 'X';
                c = n;
                d = m;
                for (int i = 0; i < k + 2; i++) {
                    for (int j = 0; j < k + 2; j++) {
                        System.out.print(Arr[i][j]);
                    }
                    System.out.println(" ");
                }
                Arr [n][m] = ' ';
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e)
            {
                System.out.println("ERROR");
            }

        }
    }
}
