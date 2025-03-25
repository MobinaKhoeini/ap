package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_4 {
    public static void main(String[] args) {
        do {
            System.out.println("please input your required letter in lower case:");
            Scanner scanner = new Scanner(System.in);
            String ch = scanner.nextLine();
            switch (ch)
            {
                case "w":{
                    System.out.println("UP"); break;
                }
                case "a":{
                    System.out.println("LEFT"); break;
                }
                case "s":{
                    System.out.println("DOWN"); break;
                }
                case "d":{
                    System.out.println("RIGHT"); break;
                }
                case "q":{
                    System.out.println("EXIT"); break;
                }
                default:
                    System.out.println("WARNING"); break;
            }
        }while (true);

    }
}
