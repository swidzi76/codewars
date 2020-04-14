import java.math.BigInteger;
import java.util.function.BiFunction;

public class Perimeter_of_squares_in_a_rectangle {
    public static void main(String[] args) {
        System.out.println(" n = 5  -> perimeter(80) = " + perimeter(BigInteger.valueOf(5)).toString());
        System.out.println(" n = 7  -> perimeter(216) = " + perimeter(BigInteger.valueOf(7)).toString());
        System.out.println(" n = 30  -> perimeter(14098308) = " + perimeter(BigInteger.valueOf(30)).toString());
    }

    public static BigInteger perimeter(BigInteger n) {
        if(n == BigInteger.ZERO){
            return BigInteger.ZERO;
        }
        if(n == BigInteger.ONE){
            return BigInteger.valueOf(4);
        }
        if(n == BigInteger.valueOf(2)){
            return BigInteger.valueOf(8);
        }
        BigInteger sum = BigInteger.valueOf(2);
        BigInteger a1 = BigInteger.ONE;
        BigInteger a2 = BigInteger.ONE;
        BigInteger a3;
        BigInteger i = BigInteger.valueOf(3);
        while(i.compareTo(n.add(BigInteger.ONE)) <= 0 ){
            a3 = a1.add(a2);
//            System.out.println("a[" + i.toString() + "] = " + a3.toString());
            sum = sum.add(a3);
            a1 = a2;
            a2 = a3;
            i = i.add(BigInteger.ONE);
        }
        return sum.multiply(BigInteger.valueOf(4));
    }
}