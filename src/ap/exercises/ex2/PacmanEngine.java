package ap.exercises.ex2;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class PacmanEngine {
    private int k;
    private int c;
    private char[][] Arr;
    private int n = 1, m = 1;
    private int score = 0;
    private long startTime;
    private int points;
    private Random random = new Random();
    private static final String SAVE_FILE = "pacman_save.txt";

    public PacmanEngine(int k, int c) {
        this.k = k;
        this.c = c;
        if (!loadGame()) {
            initializeNewGame();
        }
        startTime = System.currentTimeMillis();
    }

    private boolean loadGame() {
        try (Scanner scanner = new Scanner(new File(SAVE_FILE))) {
            this.k = scanner.nextInt();
            this.points = scanner.nextInt();
            this.score = scanner.nextInt();
            String[] pos = scanner.nextLine().trim().split(" ");
            this.n = Integer.parseInt(pos[0]);
            this.m = Integer.parseInt(pos[1]);
            Arr = new char[k+2][k+2];
            for (int i = 0; i < k + 2; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < k + 2; j++) {
                    Arr[i][j] = line.charAt(j);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void initializeNewGame() {
        Arr = new char[k+2][k+2];
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
        points = c;
        int emptySpace = k * k;
        while (c > 0) {
            int row = random.nextInt(k) + 1;
            int column = random.nextInt(k) + 1;
            if (Arr[row][column] == ' ') {
                Arr[row][column] = '.';
                c--;
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.println();
        }
    }

    public void printScore() {
        System.out.println("Score: " + score + "/" + points);
    }

    public void printRemainTime() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = (currentTime - startTime) / 1000;
        System.out.println("Time elapsed: " + timeElapsed + " seconds");
    }

    public void move(int direction) {
        int prevN = n;
        int prevM = m;
        Arr[n][m] = ' ';
        switch (direction) {
            case 0: n--; break;
            case 1: m++; break;
            case 2: n++; break;
            case 3: m--; break;
        }
        if (Arr[n][m] == '*') {
            n = prevN;
            m = prevM;
        }
        else if (Arr[n][m] == '.') {
            score++;
        }
        Arr[n][m] = 'X';
        if (score == points) {
            System.out.println("Congratulations! You collected all points!");
            printRemainTime();
            System.exit(0);
        }
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(k);
            writer.println(points);
            writer.println(score);
            writer.println(n + " " + m);
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    writer.print(Arr[i][j]);
                }
                writer.println();
            }
        } catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
}