import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 06 - 11 - 2021
// 4 kyu 685
public class _005_Insane_Coloured_Triangles_2kyu {
    public static void main(String[] args) {
        tests();
        genTest(100, 100); // 100 -100 -> STRING 141 ms, StringBuilder - >46
        genTest(100000, 1); // 100 000  - 1 ->  70720 ms 10 000 - 1  -> 891 ms
//                                                                                    ver 2 -> 750
//                                                             ver3 -> 43323,18999          ver 3 -> 540
//        System.out.println(triangle4("RBBBG"));
//        System.out.println(triangle3("RBRGBRB"));
//        System.out.println(triangle3("RBRGBRBGGRRRBGBBBGG"));
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
        char a, b;
        char[] oldLine;
        StringBuilder sb = new StringBuilder(firstRow);
        while(sb.length() > 1){
            oldLine = sb.toString().toCharArray();
            sb = new StringBuilder();
            for (int i = 0; i < oldLine.length - 1; i++) {
                a = oldLine[i];
                b = oldLine[i+1];
                if(a == b) sb.append(a);                //r = a;
                if(a =='R' && b == 'G') sb.append('B'); // r = 'B';
                if(a =='R' && b == 'B') sb.append('G'); // r = 'G';
                if(a =='G' && b == 'B') sb.append('R'); // r = 'R';
                if(a =='G' && b == 'R') sb.append('B'); // r = 'B';
                if(a =='B' && b == 'R') sb.append('G'); // r = 'G';
                if(a =='B' && b == 'G') sb.append('R'); // r = 'R';
            }
        }
        return sb.charAt(0);
    }
    public static char triangle2(String firstRow){
        char a, b;
        char[][] tab = new char[firstRow.length()][firstRow.length()];
        tab[0] = firstRow.toCharArray();

        for (int r = 1; r < tab.length; r++) {
            for (int c = 0; c < tab[r].length; c++) {

                tab[r][c] = '0';
            }
        }
        for (int r = 1; r < tab.length; r++) {
            for (int c = 0; c < tab[r].length - r; c++) {
                a = tab[r-1][c]; b = tab[r-1][c+1];
                if(a == b) tab[r][c] = a;
                if(a =='R' && b == 'G') tab[r][c] = 'B';
                if(a =='R' && b == 'B') tab[r][c] = 'G';
                if(a =='G' && b == 'B') tab[r][c] = 'R';
                if(a =='G' && b == 'R') tab[r][c] = 'B';
                if(a =='B' && b == 'R') tab[r][c] = 'G';
                if(a =='B' && b == 'G') tab[r][c] = 'R';
            }
        }

        for (int r = 0; r < tab.length; r++) {
            for (int c = 0; c < tab[r].length; c++) {
                System.out.print(tab[r][c]);
            }
            System.out.println(" " + tab[r].length);
        }
        return tab[tab.length-1][0];
    }
    public static char triangle3(String firstRow){
        StringBuilder sb = new StringBuilder(firstRow);
//        System.out.println(sb.toString());
        int c = 0;
        while(c < sb.length()-1){
            if(sb.charAt(c) == sb.charAt(c+1)){
                sb.deleteCharAt(c);
            }else c++;
        }
//        System.out.println(sb.toString());
        char[] tab = sb.toString().toCharArray();

//        char[] tab = firstRow.toCharArray();
        int k = 1; int length = tab.length;
        char a, b;
        while(k < length){
            for (int i = 0; i < length - k; i++) {
                a = tab[i]; b = tab[i+1];
                if(a == b) {
                    tab[i] = a;
//                    continue;
                }
                if(a =='R' && b == 'G') tab[i] = 'B';
                if(a =='R' && b == 'B') tab[i] = 'G';
                if(a =='G' && b == 'B') tab[i] = 'R';
                if(a =='G' && b == 'R') tab[i] = 'B';
                if(a =='B' && b == 'R') tab[i] = 'G';
                if(a =='B' && b == 'G') tab[i] = 'R';
            }
            k++;
        }
        return tab[0];
    }
    public static char triangle4(String firsRow){
        char[] tab = firsRow.toCharArray();
        int index = 0;
        int newIndex = 0;
        int length = tab.length;
        char a, b;
//        showTab(tab);
        while (length > 1){
            while(index < length - 1 ){
                a = tab[index]; b = tab[index + 1];
                if(index == 0 && a == b) {tab[newIndex] = a; newIndex++;}
                if(a == b && index > 0 && a != tab[index-1]) { tab[newIndex] = a; newIndex++;}
//                if(a == b) { tab[newIndex] = a; newIndex++;}
                if(a =='R' && b == 'G') { tab[newIndex] = 'B'; newIndex++;}
                if(a =='R' && b == 'B') { tab[newIndex] = 'G'; newIndex++;}
                if(a =='G' && b == 'B') { tab[newIndex] = 'R'; newIndex++;}
                if(a =='G' && b == 'R') { tab[newIndex] = 'B'; newIndex++;}
                if(a =='B' && b == 'R') { tab[newIndex] = 'G'; newIndex++;}
                if(a =='B' && b == 'G') { tab[newIndex] = 'R'; newIndex++;}
                index++;
            }
//            int i =newIndex;
//            while(i < length){
//                tab[i] = '0';
//                i++;
//            }
//            showTab(tab);
            length = newIndex;
            index = 0;
            newIndex = 0;

        }

        return tab[0];
    }
    private static void showTab(char[] tab){
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i]);
        }
        System.out.println();
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
        char res = triangle4(firsRow);
        long time = System.currentTimeMillis() - start;
        System.out.println(testNumber + ") First row (n=" + firsRow.length()+") " +firsRow + " -> " + res + "  TEST -> " + test1(result, res) + "   time : " + time + " ms");
    }
    public static String test1(char expected, char result){
        if(expected == result) return "OK";
        else
            return "FAIL";
    }

}
