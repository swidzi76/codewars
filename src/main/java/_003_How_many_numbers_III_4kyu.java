import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _003_How_many_numbers_III_4kyu {
    public static void main(String[] args) {
        sampleTests();
//        findAll(10,3);
    }

    public static List<Long> findAll(final int sumDigits, final int numDigits) {
        // Your code here!!
        // number = a2 *10^2 + a1*10^1 + a0  // for 3 digits
        // show all 3 digits numbers
        List<Long> result = new ArrayList<>();
        int[] digits = new int[numDigits];
        digits[0] = 1;
        for (int i = 1; i < digits.length; i++) {
            digits[i] = 1;
        }
        int d = digits.length-1;
//        showDigits(digits, sumDigits);
        while(d >= 0){
            if(digits[d] < 9){
                digits[d]++;
                if(d < digits.length -1){

                    d = digits.length-1;
                }
                if(checkSum(digits, sumDigits)) result.add(getNumber2(digits));
//                showDigits(digits, sumDigits);
            }else{
                if(d!=0){
                    for (int i = d; i < digits.length; i++) {
                        digits[i] = digits[d-1] + 1;
                    }
                }
                d--;
            }
        }
//        System.out.println(result);
//        System.out.println(result.size());
//        System.out.println(result.get(0));
//        System.out.println(result.get(result.size()-1));
        if (result.isEmpty()) return new ArrayList<Long>();
        return Arrays.asList(Long.valueOf(result.size()), Long.valueOf(result.get(0)), Long.valueOf(result.get(result.size()-1)));
    }
    private static boolean checkSum(int[] digits, int sumDigits){
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        if(sum == sumDigits){
            return true;
        }else{
            return false;
        }
    }

    private static Long getNumber(int[] digits){
        Long result = 0L;
        for (int i = 0; i < digits.length; i++) {
            result += digits[i]*pow10(digits.length-1-i);
        }
        return result;
    }
    private static Long getNumber2(int[] digits){
        Long result = 0L;
        for (int i = 0; i < digits.length; i++) {
            result += digits[i]* (long) Math.pow(10, digits.length - 1 - i);
        }
        return result;
    }
    private static Long getNumber1(int[] digits){
        Long result = 0L;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        return Long.valueOf(sb.toString());
    }
    private static Long pow10(int exp){
        Long result = 1L;
        for (int i = 1; i <= exp; i++) {
            result *= 10;
        }
        return result;
    }
    private static boolean checkOrder(int[] digits){
        for (int i = 1; i < digits.length; i++) {
            for (int j = 0; j < i; j++) {
                if (digits[i] < digits[j]) return false;
            }
        }
        return true;
    }
    private static void showDigits(int[] digits, int sumDigits){
        String s = "";
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
            s = s + String.valueOf(digits[i]);
        }
//        System.out.println(s + " -> " + sum + " ----- " + getNumber(digits));
        if(sum == sumDigits) {
            System.out.println(s + " -> " + sum + " ----- " + getNumber(digits));
        }
    }

    public static void sampleTests() {
        test1(0, Arrays.asList(8L, 118L, 334L),10, 3);
        test1(0, Arrays.asList(1L, 999L, 999L),27, 3);
        test1(0, new ArrayList<Long>(), 84, 4);
        test1(0, Arrays.asList(123L, 116999L, 566666L), 35, 6);
////Test #72
////Test for sum_digits = 33 and amount of digits = 8
////Actual: [409, 11112999, 44444445]
////Expected: [409, 11112999, 44444445]
        // Test #72
        test1(72, Arrays.asList(409L, 11112999L, 44444445L), 33, 8);
        // Test #100 5819ms -> 2032
        test1(100, Arrays.asList(738L, 111499999L, 556666666L), 52, 9);
        // 10 testów poniżej 16000 ms
    }
    private  static void test1(int nrTest, List<Long> expected, int sumDigits, int numDigits){
        // pomiar czasu
        long start = System.currentTimeMillis();
        List<Long> result = findAll(sumDigits, numDigits);
        long time = System.currentTimeMillis() - start;
        String test = test(expected, result);
        System.out.println("Test nr " + nrTest + " sumD: " + sumDigits + " numD: " + numDigits + " wynik: "+ test + " czas: "+ time + " ms");
    }
    private static String test(List<Long> expected, List<Long> answer){
        if(expected.isEmpty() && answer.isEmpty()){
//            System.out.println("OK");
            return "OK";
        }else{
            if((answer != null) && (expected.size() == answer.size()) && (expected.get(0).equals(answer.get(0)))
                    && (expected.get(1).equals(answer.get(1))) && (expected.get(2).equals(answer.get(2)))){
//                System.out.println("OK");
                return "OK";
            }else {
//                System.out.println("FAIL");
                return "FAIL";
            }
        }

    }
    //        test(Arrays.asList(123L, 116999L, 566666L), findAll(35, 6));
    //Test for sum_digits = 52 and amount of digits = 9
//Actual: [738, 111499999, 556666666]
//Expected: [738, 111499999, 556666666]

//-------
//Test #0
//Test for sum_digits = 24 and amount of digits = 7
//Actual: [137, 1111299, 3333444]
//Expected: [137, 1111299, 3333444]
//-------
//Test #1
//Test for sum_digits = 22 and amount of digits = 8
//Actual: [97, 11111179, 22333333]
//Expected: [97, 11111179, 22333333]
//-------
//Test #2
//Test for sum_digits = 28 and amount of digits = 6
//Actual: [146, 111799, 445555]
//Expected: [146, 111799, 445555]
//-------
//Test #3
//Test for sum_digits = 34 and amount of digits = 4
//Actual: [2, 7999, 8899]
//Expected: [2, 7999, 8899]
//-------
//Test #4
//Test for sum_digits = 22 and amount of digits = 6
//Actual: [94, 111199, 334444]
//Expected: [94, 111199, 334444]
//-------
//Test #5
//Test for sum_digits = 36 and amount of digits = 4
//Actual: [1, 9999, 9999]
//Expected: [1, 9999, 9999]
//-------
//Test #6
//Test for sum_digits = 24 and amount of digits = 7
//Actual: [137, 1111299, 3333444]
//Expected: [137, 1111299, 3333444]
//-------
//Test #7
//Test for sum_digits = 37 and amount of digits = 6
//Actual: [103, 118999, 666667]
//Expected: [103, 118999, 666667]
//-------
//Test #8
//Test for sum_digits = 39 and amount of digits = 8
//Actual: [519, 11118999, 45555555]
//Expected: [519, 11118999, 45555555]
//-------
//Test #9
//Test for sum_digits = 33 and amount of digits = 4
//Actual: [3, 6999, 8889]
//Expected: [3, 6999, 8889]
//-------
//Test #10
//Test for sum_digits = 20 and amount of digits = 6
//Actual: [71, 111179, 333344]
//Expected: [71, 111179, 333344]
//-------
//Test #11
//Test for sum_digits = 29 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #12
//Test for sum_digits = 28 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #13
//Test for sum_digits = 28 and amount of digits = 6
//Actual: [146, 111799, 445555]
//Expected: [146, 111799, 445555]
//-------
//Test #14
//Test for sum_digits = 30 and amount of digits = 5
//Actual: [57, 12999, 66666]
//Expected: [57, 12999, 66666]
//-------
//Test #15
//Test for sum_digits = 23 and amount of digits = 6
//Actual: [103, 111299, 344444]
//Expected: [103, 111299, 344444]
//-------
//Test #16
//Test for sum_digits = 25 and amount of digits = 3
//Actual: [2, 799, 889]
//Expected: [2, 799, 889]
//-------
//Test #17
//Test for sum_digits = 24 and amount of digits = 7
//Actual: [137, 1111299, 3333444]
//Expected: [137, 1111299, 3333444]
//-------
//Test #18
//Test for sum_digits = 36 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #19
//Test for sum_digits = 35 and amount of digits = 5
//Actual: [28, 17999, 77777]
//Expected: [28, 17999, 77777]
//-------
//Test #20
//Test for sum_digits = 21 and amount of digits = 8
//Actual: [77, 11111169, 22233333]
//Expected: [77, 11111169, 22233333]
//-------
//Test #21
//Test for sum_digits = 30 and amount of digits = 7
//Actual: [247, 1111899, 4444455]
//Expected: [247, 1111899, 4444455]
//-------
//Test #22
//Test for sum_digits = 21 and amount of digits = 6
//Actual: [81, 111189, 333444]
//Expected: [81, 111189, 333444]
//-------
//Test #23
//Test for sum_digits = 38 and amount of digits = 6
//Actual: [94, 119999, 666677]
//Expected: [94, 119999, 666677]
//-------
//Test #24
//Test for sum_digits = 30 and amount of digits = 6
//Actual: [151, 111999, 555555]
//Expected: [151, 111999, 555555]
//-------
//Test #25
//Test for sum_digits = 38 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #26
//Test for sum_digits = 20 and amount of digits = 4
//Actual: [33, 1199, 5555]
//Expected: [33, 1199, 5555]
//-------
//Test #27
//Test for sum_digits = 36 and amount of digits = 5
//Actual: [22, 18999, 77778]
//Expected: [22, 18999, 77778]
//-------
//Test #28
//Test for sum_digits = 23 and amount of digits = 4
//Actual: [28, 1499, 5666]
//Expected: [28, 1499, 5666]
//-------
//Test #29
//Test for sum_digits = 29 and amount of digits = 7
//Actual: [233, 1111799, 4444445]
//Expected: [233, 1111799, 4444445]
//-------
//Test #30
//Test for sum_digits = 34 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #31
//Test for sum_digits = 40 and amount of digits = 6
//Actual: [71, 139999, 667777]
//Expected: [71, 139999, 667777]
//-------
//Test #32
//Test for sum_digits = 39 and amount of digits = 5
//Actual: [10, 39999, 78888]
//Expected: [10, 39999, 78888]
//-------
//Test #33
//Test for sum_digits = 30 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #34
//Test for sum_digits = 21 and amount of digits = 4
//Actual: [31, 1299, 5556]
//Expected: [31, 1299, 5556]
//-------
//Test #35
//Test for sum_digits = 29 and amount of digits = 7
//Actual: [233, 1111799, 4444445]
//Expected: [233, 1111799, 4444445]
//-------
//Test #36
//Test for sum_digits = 24 and amount of digits = 7
//Actual: [137, 1111299, 3333444]
//Expected: [137, 1111299, 3333444]
//-------
//Test #37
//Test for sum_digits = 24 and amount of digits = 7
//Actual: [137, 1111299, 3333444]
//Expected: [137, 1111299, 3333444]
//-------
//Test #38
//Test for sum_digits = 39 and amount of digits = 4
//Actual: []
//Expected: []
//-------
//Test #39
//Test for sum_digits = 35 and amount of digits = 8
//Actual: [461, 11114999, 44444555]
//Expected: [461, 11114999, 44444555]
//-------
//Test #40
//Test for sum_digits = 21 and amount of digits = 3
//Actual: [7, 399, 777]
//Expected: [7, 399, 777]
//-------
//Test #41
//Test for sum_digits = 33 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #42
//Test for sum_digits = 33 and amount of digits = 7
//Actual: [282, 1113999, 4455555]
//Expected: [282, 1113999, 4455555]
//-------
//Test #43
//Test for sum_digits = 26 and amount of digits = 7
//Actual: [176, 1111499, 3344444]
//Expected: [176, 1111499, 3344444]
//-------
//Test #44
//Test for sum_digits = 28 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #45
//Test for sum_digits = 35 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #46
//Test for sum_digits = 40 and amount of digits = 5
//Actual: [7, 49999, 88888]
//Expected: [7, 49999, 88888]
//-------
//Test #47
//Test for sum_digits = 20 and amount of digits = 6
//Actual: [71, 111179, 333344]
//Expected: [71, 111179, 333344]
//-------
//Test #48
//Test for sum_digits = 29 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #49
//Test for sum_digits = 30 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #50
//Test for sum_digits = 33 and amount of digits = 4
//Actual: [3, 6999, 8889]
//Expected: [3, 6999, 8889]
//-------
//Test #51
//Test for sum_digits = 22 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #52
//Test for sum_digits = 39 and amount of digits = 4
//Actual: []
//Expected: []
//-------
//Test #53
//Test for sum_digits = 36 and amount of digits = 5
//Actual: [22, 18999, 77778]
//Expected: [22, 18999, 77778]
//-------
//Test #54
//Test for sum_digits = 32 and amount of digits = 7
//Actual: [272, 1112999, 4445555]
//Expected: [272, 1112999, 4445555]
//-------
//Test #55
//Test for sum_digits = 28 and amount of digits = 4
//Actual: [15, 1999, 7777]
//Expected: [15, 1999, 7777]
//-------
//Test #56
//Test for sum_digits = 23 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #57
//Test for sum_digits = 27 and amount of digits = 5
//Actual: [70, 11799, 55566]
//Expected: [70, 11799, 55566]
//-------
//Test #58
//Test for sum_digits = 30 and amount of digits = 7
//Actual: [247, 1111899, 4444455]
//Expected: [247, 1111899, 4444455]
//-------
//Test #59
//Test for sum_digits = 39 and amount of digits = 8
//Actual: [519, 11118999, 45555555]
//Expected: [519, 11118999, 45555555]
//-------
//Test #60
//Test for sum_digits = 20 and amount of digits = 8
//Actual: [63, 11111159, 22223333]
//Expected: [63, 11111159, 22223333]
//-------
//Test #61
//Test for sum_digits = 28 and amount of digits = 5
//Actual: [66, 11899, 55666]
//Expected: [66, 11899, 55666]
//-------
//Test #62
//Test for sum_digits = 33 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #63
//Test for sum_digits = 36 and amount of digits = 8
//Actual: [486, 11115999, 44445555]
//Expected: [486, 11115999, 44445555]
//-------
//Test #64
//Test for sum_digits = 24 and amount of digits = 4
//Actual: [27, 1599, 6666]
//Expected: [27, 1599, 6666]
//-------
//Test #65
//Test for sum_digits = 25 and amount of digits = 4
//Actual: [23, 1699, 6667]
//Expected: [23, 1699, 6667]
//-------
//Test #66
//Test for sum_digits = 36 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #67
//Test for sum_digits = 39 and amount of digits = 7
//Actual: [263, 1119999, 5556666]
//Expected: [263, 1119999, 5556666]
//-------
//Test #68
//Test for sum_digits = 21 and amount of digits = 8
//Actual: [77, 11111169, 22233333]
//Expected: [77, 11111169, 22233333]
//-------
//Test #69
//Test for sum_digits = 23 and amount of digits = 5
//Actual: [70, 11399, 44555]
//Expected: [70, 11399, 44555]
//-------
//Test #70
//Test for sum_digits = 28 and amount of digits = 8
//Actual: [255, 11111599, 33334444]
//Expected: [255, 11111599, 33334444]
//-------
//Test #71
//Test for sum_digits = 34 and amount of digits = 7
//Actual: [285, 1114999, 4555555]
//Expected: [285, 1114999, 4555555]
//-------
//Test #72
//Test for sum_digits = 33 and amount of digits = 8
//Actual: [409, 11112999, 44444445]
//Expected: [409, 11112999, 44444445]
//-------
//Test #73
//Test for sum_digits = 27 and amount of digits = 3
//Actual: [1, 999, 999]
//Expected: [1, 999, 999]
//-------
//Test #74
//Test for sum_digits = 29 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #75
//Test for sum_digits = 38 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #76
//Test for sum_digits = 27 and amount of digits = 6
//Actual: [139, 111699, 444555]
//Expected: [139, 111699, 444555]
//-------
//Test #77
//Test for sum_digits = 28 and amount of digits = 5
//Actual: [66, 11899, 55666]
//Expected: [66, 11899, 55666]
//-------
//Test #78
//Test for sum_digits = 35 and amount of digits = 4
//Actual: [1, 8999, 8999]
//Expected: [1, 8999, 8999]
//-------
//Test #79
//Test for sum_digits = 36 and amount of digits = 7
//Actual: [285, 1116999, 5555556]
//Expected: [285, 1116999, 5555556]
//-------
//Test #80
//Test for sum_digits = 40 and amount of digits = 3
//Actual: []
//Expected: []
//-------
//Test #81
//Test for sum_digits = 36 and amount of digits = 4
//Actual: [1, 9999, 9999]
//Expected: [1, 9999, 9999]
//-------
//Test #82
//Test for sum_digits = 31 and amount of digits = 6
//Actual: [147, 112999, 555556]
//Expected: [147, 112999, 555556]
//-------
//Test #83
//Test for sum_digits = 22 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #84
//Test for sum_digits = 25 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #85
//Test for sum_digits = 26 and amount of digits = 6
//Actual: [134, 111599, 444455]
//Expected: [134, 111599, 444455]
//-------
//Test #86
//Test for sum_digits = 35 and amount of digits = 7
//Actual: [289, 1115999, 5555555]
//Expected: [289, 1115999, 5555555]
//-------
//Test #87
//Test for sum_digits = 26 and amount of digits = 6
//Actual: [134, 111599, 444455]
//Expected: [134, 111599, 444455]
//-------
//Test #88
//Test for sum_digits = 35 and amount of digits = 8
//Actual: [461, 11114999, 44444555]
//Expected: [461, 11114999, 44444555]
//-------
//Test #89
//Test for sum_digits = 31 and amount of digits = 6
//Actual: [147, 112999, 555556]
//Expected: [147, 112999, 555556]
//-------
//Test #90
//Test for sum_digits = 25 and amount of digits = 8
//Actual: [164, 11111299, 33333334]
//Expected: [164, 11111299, 33333334]
//-------
//Test #91
//Test for sum_digits = 38 and amount of digits = 6
//Actual: [94, 119999, 666677]
//Expected: [94, 119999, 666677]
//-------
//Test #92
//Test for sum_digits = 30 and amount of digits = 5
//Actual: [57, 12999, 66666]
//Expected: [57, 12999, 66666]
//-------
//Test #93
//Test for sum_digits = 30 and amount of digits = 2
//Actual: []
//Expected: []
//-------
//Test #94
//Test for sum_digits = 29 and amount of digits = 6
//Actual: [147, 111899, 455555]
//Expected: [147, 111899, 455555]
//-------
//Test #95
//Test for sum_digits = 30 and amount of digits = 6
//Actual: [151, 111999, 555555]
//Expected: [151, 111999, 555555]
//-------
//Test #96
//Test for sum_digits = 26 and amount of digits = 8
//Actual: [194, 11111399, 33333344]
//Expected: [194, 11111399, 33333344]
//-------
//Test #97
//Test for sum_digits = 21 and amount of digits = 5
//Actual: [63, 11199, 44445]
//Expected: [63, 11199, 44445]
//-------
//Test #98
//Test for sum_digits = 21 and amount of digits = 7
//Actual: [86, 1111179, 3333333]
//Expected: [86, 1111179, 3333333]
//-------
//Test #99
//Test for sum_digits = 23 and amount of digits = 3
//Actual: [4, 599, 788]
//Expected: [4, 599, 788]
//*
//*
//***********************************
//        Large random tests
//***********************************
//*
//-------
//Test #100
//Test for sum_digits = 52 and amount of digits = 9
//Actual: [738, 111499999, 556666666]
//Expected: [738, 111499999, 556666666]

}
