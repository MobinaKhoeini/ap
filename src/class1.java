
import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
       Scanner S = new Scanner(System.in);
       int n = S.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == n - 1  || j == 0  || j == n - 1)
                {
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.print(" ");
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }


    }
}