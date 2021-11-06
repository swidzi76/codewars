
/// 4 kyu 556

public class Challenge_Fun_10_Integer_Square_Root_2kyu {
    public static void main(String[] args) {
        tests();
    }
    // number is integer 0 < number < 10^100
    // output -> string out * out <= number

    public static String squareRoot2(String number){
        System.out.println("-------------");
        System.out.println(number);
        String result = "";
        if (number.length() % 2 !=0 ){
            number = "0"+number;
        }
        System.out.println(number);
        long c, r, p, y, x;

        r = 0;
        p = 0;
        // bieżemy pary liczb od lewej
        for (int d = 0; d < number.length(); d+=2) {
            System.out.println(" --- ");
            c = r * 100 + Long.valueOf(number.substring(d, d+2));
            System.out.println(" c = " + c);
            System.out.println(" p = " + p);
            x = getX(c, p);
            System.out.println(" x = " + x);
            result = result + String.valueOf(x);
            System.out.println(" y = " + getY(p,x));
            r = c - getY(p, x);
            System.out.println("sqrt = " + result);
            p = Long.valueOf(result);
        }
        return result;
    }
    public static String squareRoot(String number){
        String result = "";
        if (number.length() % 2 !=0 ){
            number = "0"+number;
        }
        String c = "";
        String r = "";
        String p = "0";
        int x;
        // we take two digits from number form left side
        for (int d = 0; d < number.length(); d+=2) {
            c = delFirstZero(r + number.substring(d, d+2));
            x = calcX(c, p);
            r = subInt(c, calcY(p,x));
            result = result + String.valueOf(x);
            p = result;
        }
        return result;
    }
    private static long getX(long c, long p){
        long x;
        if(p == 0){
            x = 1;
            while( x * x <= c){
                x++;
            }
            x--;
            return x;
        }else {
            x = c /(20 * p);
            x--;
            while (getY(p, x) <= c){
                x++;
            }
            x--;
            return x;
        }
    }
    private static long getY(long p, long x){
        return (20 * p + x) * x;
    }
    private static int calcX(String c, String p){
        int x = 0;
        while(compare(calcY(p,x), c) <= 0 ){
            x++;
        }
        x--;
        return x;
    }
    // if a == b return 0
    // if a < b return -1
    // if a > b return 1
    private static int compare(String a, String b){
        if (a.length() > b.length()) return 1;
        if (a.length() < b.length()) return -1;
        // length is equal
        for (int i = 0; i < a.length(); i++) {
            if((a.charAt(i) - 48) > (b.charAt(i) - 48)) return 1;
            if((a.charAt(i) - 48) < (b.charAt(i) - 48)) return -1;
        }
        return 0;
     }
    private static String calcY(String p, int x){
        // (20 * p + x) * x
        return mulX(delFirstZero(mul2(p) + String.valueOf(x)), x);
    }
    // multiplication  - x - one digit -> 0...9
    private static String mulX(String a, int x){
        if(x == 0 ) return "0";
        if(a.equals("0")) return "0";
        String result ="";
        int w;
        int r = 0;
        for (int i = 0; i < a.length() ; i++) {
            w = r + (a.charAt(a.length() - 1 - i)- 48) * x;
            if(w < 10){
                result = String.valueOf(w) + result;
                r = 0;
            }else{
                result = String.valueOf(w).charAt(1) + result;
                r = String.valueOf(w).charAt(0) - 48;
            }
        }
        if(r > 0 ) result = String.valueOf(r) + result;
        return result;
    }
    private static String addInt(String a, String b){
        String result ="";
        if(a.length() < b.length()){
            while(a.length() < b.length()){
                a = "0" + a;
            }
        }
        if(a.length() > b.length()){
            while (b.length()< a.length()){
                b = "0" + b;
            }
        }
        int r = 0;
        int x,y,s;
        String str ="";
        for (int i = 0; i < a.length(); i++) {
            x = a.charAt(a.length() - 1 - i) - 48;
            y = b.charAt(b.length() - 1 - i) - 48;
            s = r + x + y;
            if(s < 10){
                result = String.valueOf(s) + result;
                r = 0;
            }else{
                str = String.valueOf(s);
                result = str.charAt(1) + result;
                r = str.charAt(0) - 48;
            }
        }
        if(r > 0 ) result = String.valueOf(r) + result;
        return result;
    }
    private static String subInt(String a, String b){
        String result ="";
        // a - b -> length b <= length a
        if(a.length() > b.length()){
            while (b.length()< a.length()){
                b = "0" + b;
            }
        }
        int r = 0;
        int x,y,w;
        String str ="";
        for (int i = 0; i < a.length(); i++) {
            x = a.charAt(a.length() - 1 - i) - 48;
            y = b.charAt(b.length() - 1 - i) - 48;
            if(x+r < y){
                w = r + x + 10 - y;
                r = -1;
            }else{
                w = r + x - y;
                r = 0 ;
            }
            result = String.valueOf(w) + result;
        }
        return delFirstZero(result);
    }
    private static String delFirstZero(String a){
        while(a.length() > 1 && a.charAt(0) == '0'){
            a = a.substring(1);
        }
        return a;
    }
    private static String mul2(String a){
        if(a.equals("0")) return "0";
        String result ="";
        int x;
        int r = 0;
        for (int i = 0; i < a.length() ; i++) {
            x = r + (a.charAt(a.length() - 1 - i)- 48) * 2;
            if(x < 10){
                result = String.valueOf(x) + result;
                r = 0;
            }else{
                result = String.valueOf(x).charAt(1) + result;
                r = String.valueOf(x).charAt(0) - 48;
            }
        }
        if(r > 0 ) result = String.valueOf(r) + result;
        return result;
    }
    public static void tests(){
        test(1, "4", "2");
        test(2, "17", "4");
        test(3, "101", "10");
        test(4, "23232328323215435345345345343458098856756556809400840980980980980809092343243243243243098799634", "152421548093487868711992623730429930751178496967");
        test(5, "12323309809809534545458098709854808654685688665486860956865654654654324238000980980980", "3510457208086937291253621317073222057793129");
    }
    public static void test(int testNumber, String number, String sqrt) {
        long start = System.currentTimeMillis();
        String result = squareRoot(number);
        long time = System.currentTimeMillis() - start;
        System.out.println(testNumber + ") sqrt(" + number + ") = " + result + "  TEST -> " + test1(sqrt, result) + "   time : " + time + " ms");
    }
    public static String test1(String expected, String result){
        if(expected.equals(result)) return "OK";
        else
            return "FAIL";
    }
}
