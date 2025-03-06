import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class class1 {
    public static void main(String[] args) {
        System.out.println("please enter a string:");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String upperCase = printUppercaseLetter(str);
        System.out.println("upper case letters: " + upperCase);
        String secondLetters = printSecondLetter(str);
        System.out.println("second letters: " + secondLetters);
        String replaceVowels = replaceVowels(str);
        System.out.println(replaceVowels);
        printVowelsWithIndex(str);
    }

    public static String printSecondLetter(String s) {
        StringBuilder result = new StringBuilder();
        String[] words = s.split(" ");
        for (String word : words)
            if (word.length() > 1)
            {
                result.append(word.charAt(1));
            }
        return result.toString();

    }

    public static String printUppercaseLetter(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c))
            {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String replaceVowels(String s) {
        return s.replaceAll("[aeiouAEIOU]", "_");
    }

    public static void printVowelsWithIndex(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ("aeiouAEIOU".indexOf(c) != -1)
            {
                System.out.println(c + " " + "index:" + i);
            }
        }
    }
}
