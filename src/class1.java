import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.sound.midi.Sequence;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter the size of array:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        System.out.println("please enter the floating-point numbers:");
        Scanner s = new Scanner(System.in);
        float[] floatNum = new float[size];
        for (int i = 0; i < floatNum.length; i++) {
            floatNum[i] = s.nextFloat();
        }
        float avarage = 0, min = 0, max = 0, range = 0;
        avarage = getAverage(floatNum);
        System.out.println("the average: " + avarage);
        min = getSmallest(floatNum);
        System.out.println("the smallest: " + min);
        max = getlargest(floatNum);
        System.out.println("the largest: " + max);
        range = getRange(max , min);
        System.out.println("the range: " + range);
    }
    public static float getAverage (float[] numbers)
    {
        float sum = 0;
        float average = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        average = sum / numbers.length;
        return average;
    }
    public static float getSmallest (float[] numbers)
    {
        float min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (min > numbers[i])
            {
                min = numbers[i];
            }

        }
        return min;
    }
    public static float getlargest (float[] numbers)
    {
        float max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (max < numbers[i])
            {
                max = numbers[i];
            }

        }
        return max;
    }
    public static  float getRange(float max, float min){
        float range = 0;
        range = max - min;
        return range;
    }

}
