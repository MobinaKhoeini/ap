
import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter the side length of the diamond:");
        Scanner n = new Scanner(System.in);
        int sLength = n.nextInt();
        for (int i = 0 ; i < sLength; i++) {
            for (int j = 0; j < sLength - i - 1 ; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (2 * i + 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = sLength - 2; i >= 0 ; i--) {
            for (int j = 0; j <  sLength - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= 2 * i ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
}