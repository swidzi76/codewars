import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("test", "testing", "middle", "A");
        for (String s : list) {
            System.out.println(s+ " -> " + getMiddle(s));
        }

//        Kata.getMiddle("test") should return "es"
//
//        Kata.getMiddle("testing") should return "t"
//
//        Kata.getMiddle("middle") should return "dd"
//
//        Kata.getMiddle("A") should return "A"
    }
    public static String getMiddle(String word) {
        if(word.length() % 2 == 0){
            return String.valueOf(word.charAt(word.length()/2 - 1)) + word.charAt(word.length()/2);
        }else{
            return String.valueOf(word.charAt(word.length()/2));
        }
    }
//    public static void main(String[] args) {
//        int a = 21445;
//        List<Integer> list = Arrays.asList(21445, 145263);
//
//        for (Integer integer : list) {
//            System.out.println(integer + " -> " + sortDesc(integer));
//        }
//    }
    public static int sortDesc(final int num){
        String string = String.valueOf(num);
        byte[] bytes = string.getBytes();
        int[] numberArray = new int[10];
        for (byte b : bytes) {
            numberArray[b-'0']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < numberArray[i]; j++) {
                char ch = (char) ('0' + i);
                sb.append(ch);
            }
        }
        return Integer.valueOf(sb.toString());
    }
}
