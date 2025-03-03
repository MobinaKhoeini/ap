
import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please input a sequence of integers:");
       Scanner n = new Scanner(System.in);
       int largest = n.nextInt();
       int smallest = largest;
       int Cumulative = largest;
        List<Integer> adjDup = new ArrayList<>();
        int preNum = largest;
        int i = 0, j = 0;
        if (largest % 2 == 0)
        {
            i++;
        }
        else {
            j++;
        }
        System.out.println("type done to continue");
        System.out.println("cumulative totals:");
        System.out.print(largest + " ");
        while (n.hasNextInt())
       {
           //int currentNum = n.nextInt();
           int Sequence = n.nextInt();
           if (largest < Sequence)
           {
               largest = Sequence;;
           }
           if (smallest > Sequence)
           {
               smallest = Sequence;
           }
           if (Sequence % 2 == 0)
           {
               i++;
           }
           else
           {
               j++;
           }
           Cumulative += Sequence;
           System.out.print( Cumulative + " ");
           if (Sequence == preNum && !adjDup.contains(Sequence))
           {
               adjDup.add(Sequence);
           }
           preNum = Sequence;

       }
        System.out.println("the largest: " + largest);
        System.out.println("the smallest: " + smallest);
        System.out.println("the number of even inputs: " + i);
        System.out.println("the number of odd inputs: " + j);
        if (!adjDup.isEmpty()) {
            System.out.print("adjacent duplicates:");

            for (int num : adjDup) {
                System.out.print(num + " ");
            }
        }
        else System.out.println("no adjacent dupliates");




    }
}