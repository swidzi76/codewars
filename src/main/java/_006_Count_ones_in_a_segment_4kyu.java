// 24 - 11 - 2021
// 4 kyu 813 -> 845

import java.math.BigInteger;

//Given two numbers: 'left' and 'right' (1 <= 'left' <= 'right' <= 200000000000000)
// return sum of all '1' occurencies in binary representations of numbers between 'left' and 'right' (including both)
//
//        Example:
//        countOnes 4 7 should return 8, because:
//        4(dec) = 100(bin), which adds 1 to the result.
//        5(dec) = 101(bin), which adds 2 to the result.
//        6(dec) = 110(bin), which adds 2 to the result.
//        7(dec) = 111(bin), which adds 3 to the result.
//        So finally result equals 8.
public class _006_Count_ones_in_a_segment_4kyu {
    public static void main(String[] args) {
//        for (int i = 0; i < 1200; i++) {
//
//            System.out.println(" suma jedynek dla i = " + i  + " -> " + oneCounter2_from0(i));
//        }
        test(200l, 2_000_000_000_000_000_000l);
//        System.out.println("200_000_000_000_000");
//        for (int i = 0; i < 100; i++) {
//            System.out.println((1l<<i));
//        }
//        System.out.println(Long.MAX_VALUE);
//        System.out.println("200000000000000");
//        long a = Long.MAX_VALUE;
//        long b = Long.parseLong("200000000000000");
//        long c = 200000000000000L;
//        if(a > b) System.out.println("a większe");
//
//        System.out.println( "a = " + a + " b = " + b);
    }

    public static void test(long left, long right){
        if(right > Long.MAX_VALUE){
            System.out.println("ZA DUŻA LICZBA");
            return;
        }
        System.out.println(" left = " + left + " right = " + right);
        long start = System.currentTimeMillis();
        long  result = 0;
//        System.out.println(" oneCounter -> " + oneCounter(left, right) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            result = oneCounter1(left, right);
        }
        System.out.println(" oneCounter1 -> " + result + "  " + (System.currentTimeMillis() - start) + " ms" );
//        System.out.println(" oneCounter1 -> " + oneCounter1(left, right) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            result = oneCounter2(left, right);
        }
        System.out.println(" oneCounter2 -> " + result + "  " + (System.currentTimeMillis() - start) + " ms" );
//        System.out.println(" oneCounter2 -> " + oneCounter2(left, right) + "  " + (System.currentTimeMillis() - start) + " ms" );
    }
    public static long oneCounter2(long left, long right){
        return oneCounter2_from0(right) - oneCounter2_from0(left - 1);
    }

    private static long oneCounter2_from0(long number) {
        long result = 0;
        long temp1 = 0;
        long temp3 = 0; long temp4 = 0;
        long pow = 0; //0 ,1, 2, 3, 4, 5 ....
        number++;
        while (number > (1l<<pow)){
            temp1 = (1l << pow);                // 2^pow
            temp3 = (number >> (pow + 1));      //number / 2^pow+1
            result += temp3 * temp1;
            temp4 = number - temp3 * (1l << (pow+1));    // rest of division
            if(temp4 > temp1) result+= (temp4-temp1);
            pow++;
        }
        return result;
    }

    public static long oneCounter1(long left, long right){
        return oneCounterFrom0(right) - oneCounterFrom0(left-1);
    }
    public static long oneCounterFrom0(long number){
        long result = 0;
        long p, p2;
        while(number!=0){
            p = Long.toBinaryString(number).length() - 1;
//            p = BigInteger.valueOf(number).toString(2).length() -1;
            p2 = 1l << p; //new BigInteger("1").shiftLeft(p.intValue());
            number -= p2;
            result += p * (p2 >> 1l) + 1 + number;
        }
        return result;
    }
    public static long oneCounter(long left, long right){
        long result = 0;
        for (long i = left; i <=right ; i++) {
//            System.out.println(i + " (dec) = " + decToBin(i) + " (bin) one's is " + oneCounter(i));
            result+=oneCounter(i);
        }
        return result;
    }
    public static long oneCounter(BigInteger left, BigInteger right){
        BigInteger number = left;
        right = right.add(BigInteger.ONE);
        long result = 0;
        while(!number.equals(right)){
//            result += oneCounter(number);
            result += number.bitCount();
//            System.out.println(number.toString() + " one is "+ result);
            number = number.add(BigInteger.ONE);
        }
        return result;
    }
    public static long oneCounter(BigInteger number){
        long result = 0;
        BigInteger two = BigInteger.valueOf(2l);
        while(number.compareTo(BigInteger.ZERO) > 0 ){
            if(number.mod(two) != BigInteger.ZERO) result++;
            number = number.divide(two);
        }
        return result;
    }
    public static String bigIntegerToBin(BigInteger number){
        return number.toString(2);
    }
    public static long oneCounter(long decNumber){
        long result = 0;
        while(decNumber > 0){
            if((decNumber % 2) != 0) result++;
            decNumber /= 2;
        }
        return result;
    }
    public static String decToBin(long decNumber){
        String result = "";
        if(decNumber == 0) return "0";
        while(decNumber > 0){
            if((decNumber % 2) == 0){
                result = "0" + result;
            }
            else {
                result = "1" + result;
            }
            decNumber /= 2;
        }

        return result;
    }
}
