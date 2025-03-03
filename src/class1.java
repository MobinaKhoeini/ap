
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
       String[] str = new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("please enter string " + (i+1) + ":");
            str[i] = S.nextLine();
        }
        Arrays.sort(str);
        for (int i = 0; i < 3; i++) {
            System.out.println(str[i]);
        }


    }
}