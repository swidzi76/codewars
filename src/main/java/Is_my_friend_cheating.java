import java.util.ArrayList;
import java.util.List;

public class Is_my_friend_cheating {
    public static void main(String[] args) {
        System.out.println("for 26 - > " + showListLong(removNb(26)));
        //removNb(26);
    }

    public static List<long[]> removNb(long n) {
        // your code
        long sum = ((n+1)*n) / 2;
        List<long[]> list = new ArrayList<long[]>();
        for (int a = 1; a <= n ; a++) {
            double b = (double)(sum - a)/(double)(a+1);
            long bLong = Math.round(b);
            if(((b - bLong) == 0)&&( b < n)){
                long[] longTab = new long[]{a,bLong};
                list.add(longTab);
            }
        }
        return list;
    }

    private static String showListLong(List<long[]> removNb) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (long[] longs : removNb) {
            sb.append("[");
            for (long aLong : longs) {
                sb.append(aLong+", ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
            sb.append("], ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
}
