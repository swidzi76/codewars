import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Number_of_Proper_Fractions_with_Denominator_d {

    public static void main(String[] args) {
        System.out.println("proper fractions = 1 - > "+ properFractions(1) + "(0)");
        System.out.println("proper fractionsV2 = 1 - > "+ properFractionsV2(1) + "(0)");
        System.out.println("proper fractions = 2 - > "+ properFractions(2) + "(1)");
        System.out.println("proper fractionsV2 = 2 - > "+ properFractionsV2(2) + "(1)");
        System.out.println("proper fractions = 5 - > "+ properFractions(5) + "(4)");
        System.out.println("proper fractionsV2 = 5 - > "+ properFractionsV2(5) + "(4)");
        System.out.println("proper fractions = 15 - > "+ properFractions(15) + "(8)");
        System.out.println("proper fractionsV2 = 15 - > "+ properFractionsV2(15) + "(8)");
        System.out.println("proper fractions = 25 - > "+ properFractions(25) + "(20)");
        System.out.println("proper fractionsV2 = 25 - > "+ properFractionsV2(25) + "(20)");
        System.out.println("proper fractions = 30 - > "+ properFractions(30) + "(8)");
        System.out.println("proper fractionsV2 = 30 - > "+ properFractionsV2(30) + "(8)");
        System.out.println("proper fractions = 36 - > "+ properFractions(36) + "(12)");
        System.out.println("proper fractionsV2 = 36 - > "+ properFractionsV2(36) + "(12)");
        System.out.println("proper fractions = 46 - > "+ properFractions(46) + "(22)");
        System.out.println("proper fractionsV2 = 46 - > "+ properFractionsV2(46) + "(22)");
        System.out.println("proper fractions = 72 - > "+ properFractions(72) + "(?)");
        System.out.println("proper fractionsV2 = 72 - > "+ properFractionsV2(72) + "(?)");
        System.out.println("proper fractions = 180 - > "+ properFractions(180) + "(48)");
        System.out.println("proper fractionsV2 = 180 - > "+ properFractionsV2(180) + "(48)");
        System.out.println("proper fractions = 230 - > "+ properFractions(230) + "(88)");
        System.out.println("proper fractionsV2 = 230 - > "+ properFractionsV2(230) + "(88)");
        System.out.println("proper fractions = 1520 - > "+ properFractions(1520) + "(576)");
        System.out.println("proper fractionsV2 = 1520 - > "+ properFractionsV2(1520) + "(576)");
        System.out.println("proper fractions = 60 - > "+ properFractions(60) + "(16)");
        System.out.println("proper fractionsV2 = 60 - > "+ properFractionsV2(60) + "(16)");

        long start  = System.currentTimeMillis();
        System.out.println("proper fractions = 9999999 [2238ms] - > "+ properFractions(9999999));
        long stop  = System.currentTimeMillis();
        System.out.println("czas wykonania : " + (stop - start) + "[ms]");

        start  = System.currentTimeMillis();
        System.out.println("proper fractionsV2 = 9999999 [2238ms] - > "+ properFractionsV2(9999999));
        stop  = System.currentTimeMillis();
        System.out.println("czas wykonania : " + (stop - start) + "[ms]");


        start  = System.currentTimeMillis();
        System.out.println("proper fractions = 999999999 [494005ms] - > "+ properFractionsV2(999999999));
        stop  = System.currentTimeMillis();
        System.out.println("czas wykonania : " + (stop - start) + "[ms]");
    }// main




    public static long properFractionsV2(long n){
        if(n == 1) return 0;
        Set<Long> dividers = dividers(n);
        // product k1*k2*k3*k4*...*kn
        long ratio1 = 1;
        for (Long divider : dividers) {
            ratio1 *= divider;
        }
        long factor = 0;
        long ratio3 = 0;
        long sum = 0;
        long[] arrDiv = new long[dividers.size()];
        int i = 0;
        for (Long divider : dividers) {
            arrDiv[i++] = divider.longValue();
        }
        Combinantion comb = new Combinantion(arrDiv, dividers.size());
        for (int j = 0; j <= dividers.size(); j++) {
            if(j != dividers.size()){
                comb.calculate(arrDiv,arrDiv.length - j);
                List<long[]> combinations = comb.getCombinations();
                factor = 0;
                for (long[] combination : combinations) {
                    ratio3 = 1; // iloczyn k1*k2...
                    for (long l : combination) {
                        ratio3 *= l;
                    }
                    factor += ratio3;
                }

            }else {
                factor =1;
            }
            factor *= (long) Math.pow(-1,j);
            sum += factor;
        }

        return (n/ratio1) * sum;
    }
    public static class Combinantion{
        private long[] data;
        private long[] result;
        private List<long[]> results;
        private long n;
        private int k;

        public List<long[]> getCombinations(){
            return results;
        }
        public Combinantion(long[] arr, int k) {
            calculate(arr, k);
        }

        public void calculate(long[] arr, int k){
            this.data = arr;
            this.k = k;
            this.n = arr.length;
            this.result = new long[k];
            this.results = new ArrayList<>();
            combinationsTmp(1,k);
        }
        public long numberCombinations(){
            return factorial(n) / (factorial(k) * factorial(n-k));
        }
        private long factorial(long n){
            if(n == 0 || n == 1) return 1;
            return n * factorial(n-1);
        }
        private void combinationsTmp(int i, int p){
            for (int j = i ; j <= n - p + 1 ; j++) {
                result[(k-p)] = data[j-1];
                if(p == 1){
                    long[] tmp = result.clone();
//                    tmp = result.clone();
                    results.add(tmp);
                }
                else combinationsTmp(j + 1, p -1);
            }
        }

    }
    public static Set<Long> dividers(long n){
        long d = 2;
        Set<Long> data = new HashSet<>();
        while (n > 0 && d <= Math.sqrt(n)){
            while(n % d == 0){
                n /= d;
//                System.out.println(d);
                data.add(d);
            }
            d++;
        }
        if(n > 1){
//            System.out.println(n);
            data.add(n);
        }
        return data;
    }

    public static long gcd(long a, long b) {
        if (b != 0) {
            return gcd(b, a % b);
        }
        return a;
    }
    public static long properFractions(long n) {
        // good luck
        long count = 0;
        for (long i = 1; i < n; i++) {
            if(gcd(i,n) == 1){
                count++;
//                System.out.println(i+"/"+n+", ");
            }
        }
        return count;
    }
}

