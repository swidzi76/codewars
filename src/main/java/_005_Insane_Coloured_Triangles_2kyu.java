import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 06 - 11 - 2021
// 4 kyu 685
public class _005_Insane_Coloured_Triangles_2kyu {
    public static void main(String[] args) {
        tests();
//        System.out.println(genFirstRow(100));
        genTest(100, 100); // 141 ms
//        System.out.println(triangle("RRR"));
    }
    public static char triangle1(String firstRow){
        Map<String, String> map = new HashMap<>();
        map.put("RR", "R"); map.put("RG", "B"); map.put("RB", "G");
        map.put("GG", "G"); map.put("GB", "R"); map.put("GR", "B");
        map.put("BB", "B"); map.put("BR", "G"); map.put("BG", "R");
        String newStr = firstRow;
        String oldStr = "";
        String s1, s2;
        while(newStr.length() > 1){
            oldStr = newStr;
            newStr = "";
            for (int i = 0; i < oldStr.length()-1; i++) {
//                s1 = oldStr.substring(i, i+2);
//                s2 = map.get(s1);
//                newStr = newStr + s2;
                newStr = newStr + map.get(oldStr.substring(i,i+2));
            }
        }
        return newStr.charAt(0);

    }
    public static char triangle(String firstRow){
        char a, b, r = ' ';
        String newStr = firstRow;
        String oldStr = "";
        while(newStr.length() > 1){
            oldStr = newStr;
            newStr = "";
            for (int i = 0; i < oldStr.length()-1; i++) {
                a = oldStr.charAt(i);
                b = oldStr.charAt(i+1);
                if(a == b) r = a;
                if(a =='R' && b == 'G') r = 'B';
                if(a =='R' && b == 'B') r = 'G';
                if(a =='G' && b == 'B') r = 'R';
                if(a =='G' && b == 'R') r = 'B';
                if(a =='B' && b == 'R') r = 'G';
                if(a =='B' && b == 'G') r = 'R';
                newStr = newStr + String.valueOf(r);
            }
        }
        return newStr.charAt(0);
    }
    public static void genTest(int lenghtRow, int testNumbers){
        String[] tests = new String[testNumbers];
        // generowanie testów
        for (int i = 0; i < testNumbers; i++) {
            tests[i] = genFirstRow(lenghtRow);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < testNumbers; i++) {
            test(i+1,tests[i], 'B');
        }
        System.out.println("Time ALL test's : " + (System.currentTimeMillis()-start) + " ms");
    }
    private static String genFirstRow(int lengthRow){
        String[] color = new String[]{"R", "G", "B"};
        String result = "";
        Random random = new Random();
        for (int i = 0; i < lengthRow; i++) {
            result = result + color[random.nextInt(3)] ;
        }
        return result;
    }
    public static void tests(){
        test(1, "B", 'B');
        test(2, "RRR", 'R');
        test(3, "RGBG", 'B');
        test(4, "RBRGBRB", 'G');
        test(5, "RBRGBRBGGRRRBGBBBGG", 'G');
    }
    public static void test(int testNumber, String firsRow, char result) {
        long start = System.currentTimeMillis();
        char res = triangle(firsRow);
        long time = System.currentTimeMillis() - start;
        System.out.println(testNumber + ") First row " +firsRow + " -> " + res + "  TEST -> " + test1(result, res) + "   time : " + time + " ms");
    }
    public static String test1(char expected, char result){
        if(expected == result) return "OK";
        else
            return "FAIL";
    }

}
