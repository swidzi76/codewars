import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Reverse_or_rotate {
    public static void main(String[] args) {
        System.out.println(revRot("123456987654", 6) + " --> 234561876549");
        System.out.println(revRot("123456987653", 6) + " --> 234561356789");
        System.out.println(revRot("66443875", 4) + " --> 44668753");
        System.out.println(revRot("66443875", 8) + " --> 64438756");
        System.out.println(revRot("664438769", 8) + " --> 67834466");
        System.out.println(revRot("123456779", 8) + " --> 23456771");
        System.out.println(revRot("", 8) + " --> ");
        System.out.println(revRot("123456779", 0) + " --> ");
        System.out.println(revRot("563000655734469485", 4) + " --> 0365065073456944");
    }

    public static String revRot(String strng, int sz) {
        // your code
        System.out.println("strng = " + strng + " sz = " + sz);
        if (strng.equals("") || sz == 0) return "";
        //List<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int i = 0;

        String s;
        while ((i+sz) <= strng.length()) {
            s = strng.substring(i, i + sz);
            //if (s.length() == sz) {
                System.out.println("s = " + s);
//                BigInteger bigInteger = new BigInteger(s);
//                // suma cyfr liczby l
//                long sum = 0;
//                while (bigInteger != 0) {
//                    sum = sum + l % 10;
//                    l = l / 10;
//                }
                int sum = 0;
            for (int j = 0; j < s.length() ; j++) {
                sum += Integer.valueOf(s.substring(j,j+1));
            }
                System.out.println(" suma cyfr = "+sum);

                if((sum % 2)==0){
                    // odwracamy (reverse) s
                    sb.append(new StringBuilder(s).reverse().toString());
                }else {
                    // przesuwamy (rotate) s o 1 w lewo
                    sb.append(s.substring(1));
                    sb.append(s.charAt(0));
                }
                System.out.println("sb = " + sb.toString());
            //}
            i += sz;
        }
        return sb.toString();
    }
}
