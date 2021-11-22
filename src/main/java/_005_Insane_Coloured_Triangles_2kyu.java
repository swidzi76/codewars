import java.util.*;

// 06 - 11 - 2021
// 4 kyu 685 -> 813
public class _005_Insane_Coloured_Triangles_2kyu {
    public static void main(String[] args) {
//        String[][] sts = new String[][]{};
//        tests();

//        test2(100);
//        List<String> result = new ArrayList<>();
//        showAllPaoiblilites(7, new char[]{'R', 'G', 'B'},result);
////        System.out.println("lista result");
////        System.out.println(result);
////        System.out.println(" liczba możliwości : " + result.size());
//        for (int i = 0; i < result.size(); i++) {
//            String s = result.get(i);
//            System.out.println(i+1 + "/"+result.size() + ") " + s + " -> " + triangle7(s));
//        }
//

//        searchBestResult();
//        showAll(7);

        test2(100000);
//        searchN(2200);

//        tests();
        //searchBestResult();

//        System.out.println(triangle4("RRGBBGBRGR"));
//        triangle4("GGGGRBRB");
//        genTest(100, 100); // 100 -100 -> STRING 141 ms, StringBuilder - >46
//        genTest(100000, 1); // 100 000  - 1 ->  70720 ms 10 000 - 1  -> 891 ms
//                                                                                    ver 2 -> 750
//                                                             ver3 -> 43323,18999          ver 3 -> 540
//        System.out.println(triangle4("RBBBG"));
//        System.out.println(triangle3("RBRGBRB"));
//        System.out.println(triangle3("RBRGBRBGGRRRBGBBBGG"));
    }
    public static void showAll(int n){
        List<String> result = new ArrayList<>();
        showAllPaoiblilites(n, new char[]{'R', 'G', 'B'}, result);
        for (int i = 0; i < result.size(); i++) {
            String s = result.get(i);
            System.out.println(i+1 + "/"+result.size() + ") " + s + " -> " + triangle7(s));
        }
    }
    public static void searchBestResult(){
        for (int n = 2; n < 11; n++) {
            List<String> result = new ArrayList<>();
            char a1,b1,c1,a2,b2,c2;
            showAllPaoiblilites(n,new char[]{'R','G','B'}, result);
            a1 = triangle7(result.get(0));
            b1 = triangle7(result.get(1));
            c1 = triangle7(result.get(2));

            a2 = triangle7(result.get(3));
            b2 = triangle7(result.get(4));
            c2 = triangle7(result.get(5));
            if(a1==a2 && b1==b2 && c1==c2){
                System.out.println(" schemat dla n = "+ n);
                System.out.println(result.get(0) + " -> " + a1);
                System.out.println(result.get(1) + " -> " + b1);
                System.out.println(result.get(2) + " -> " + c1);
                for (int i = 3; i < result.size(); i+=3) {
                    a2 = triangle7(result.get(i));
                    b2 = triangle7(result.get(i+1));
                    c2 = triangle7(result.get(i+2));
                    if(a1!=a2 || b1!=b2 || c1!=c2){
                        System.out.println(result.get(i) + " -> " + a2);
                        System.out.println(result.get(i+1) + " -> " + b2);
                        System.out.println(result.get(i+2) + " -> " + c2);
                        a1 = a2; b1 = b2; c1 = c2;
                    }
                }
            }

        }
    }
    public static void showAllPaoiblilites(int positions, char[] posibilites, List<String> result){
        String s = "";
        showAllPosibilitesTemp(positions+1, posibilites, s, result);
    }
    private static void showAllPosibilitesTemp(int pos, char[] posi, String s, List<String> result){
        String s1;
        pos--;
        for (int i = 0; i < posi.length; i++) {
            s1 = s + posi[i];
            if(pos > 1){
                showAllPosibilitesTemp(pos, posi, s1, result);
            }else{
//                System.out.println(s1);
                result.add(s1);
            }
        }
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

//        for (int r = 0; r < tab.length; r++) {
//            for (int c = 0; c < tab[r].length; c++) {
//                System.out.print(tab[r][c]);
//            }
//            System.out.println(" " + tab[r].length);
//        }
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
    // medoda wykorzystuje 2 znaki
    public static char triangle4(String firsRow){
        char[] tab = firsRow.toCharArray();
        int index = 0;
        int newIndex = 0;
        int length = tab.length;
        char a, b;
        char ch;
        int counter, tempIndex;
//        showTab(tab);
        while (length > 1){

//            // zamiana  3,4,5,6 ... ->1
//            while(index < length){
//                ch = tab[index];
//                counter = 1;
//                tempIndex = index +1;
//                while((tempIndex < length) && ch == tab[tempIndex]){
//                    counter++;
//                    tempIndex++;
//                }
//                if(counter > 2){
//                    tab[newIndex++] = ch;
//                    index+=counter;
//                }else{
//                    tab[newIndex++] = ch;
//                    index++;
//                }
//            }
//            length = newIndex;
//            index = 0;
//            newIndex = 0;

            while(index < length - 1 ){
                a = tab[index]; b = tab[index + 1];
//                if(index == 0 && a == b) {tab[newIndex] = a; newIndex++;}
//                if(a == b && index > 0 && a != tab[index-1]) { tab[newIndex] = a; newIndex++;}
                if(a == b) { tab[newIndex++] = a;index++;continue;}
                if(a =='R' && b == 'G') { tab[newIndex++] = 'B';index++;continue;}
                if(a =='R' && b == 'B') { tab[newIndex++] = 'G';index++;continue;}
                if(a =='G' && b == 'B') { tab[newIndex++] = 'R';index++;continue;}
                if(a =='G' && b == 'R') { tab[newIndex++] = 'B';index++;continue;}
                if(a =='B' && b == 'R') { tab[newIndex++] = 'G';index++;continue;}
                if(a =='B' && b == 'G') { tab[newIndex++] = 'R';index++;continue;}
//                index++;
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
    // metoda oparta na trzech znakach
    public static char triangle5(String firstRow){
        char[] tab = firstRow.toCharArray();
        int length = tab.length;
        int index = 0;
        int newIndex = 0;
        char a,b,c;
        while(length > 1 ){
            if(length > 3){
                while(index < length - 2){
                    a = tab[index]; b = tab[index+1]; c = tab[index+2];
                    if(a == b){tab[newIndex++] = c;index++;continue;}   // 1
                    if(b == c){tab[newIndex++] = a;index++;continue;}   // 2
                    if(a == c){if(a=='R' && b=='G'){tab[newIndex++]='B';index++;continue;}
                                if(a=='R' && b=='B'){tab[newIndex++]='G';index++;continue;}
                                if(a=='G' && b=='B'){tab[newIndex++]='R';index++;continue;}
                                if(a=='G' && b=='R'){tab[newIndex++]='B';index++;continue;}
                                if(a=='B' && b=='R'){tab[newIndex++]='G';index++;continue;}
                                if(a=='B' && b=='G'){tab[newIndex++]='R';index++;continue;}}
                    tab[newIndex++] = b;
                    index++;
                }
            }else{
                while(index < length - 1){
                    a = tab[index]; b = tab[index + 1];
                    if(a == b) { tab[newIndex++] = a;}
                    if(a =='R' && b == 'G') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='R' && b == 'B') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='G' && b == 'B') { tab[newIndex++] = 'R';index++;continue;}
                    if(a =='G' && b == 'R') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='B' && b == 'R') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='B' && b == 'G') { tab[newIndex++] = 'R';index++;continue;}
                    index++;
                }
            }
            length = newIndex;
            index = 0;
            newIndex = 0;
        }
        return tab[0];
    }
    // metoda oparta na działaniu z dwóch konców
    public static char triangle6(String firsRow){
        char[] tab = firsRow.toCharArray();
        int length = tab.length;
        int index = 0;
        char a,b,c,d;
//        showTab(tab);
        while(length > 1){
            c = tab[length-1];
            while(index < length / 2){
                a = tab[index]; b = tab[index+1]; d = c; c = tab[length - index -2]; //d = tab[length - index - 1];
                if(a == b) { tab[index] = a;}
                if(a =='R' && b == 'G') { tab[index] = 'B';}
                if(a =='R' && b == 'B') { tab[index] = 'G';}
                if(a =='G' && b == 'B') { tab[index] = 'R';}
                if(a =='G' && b == 'R') { tab[index] = 'B';}
                if(a =='B' && b == 'R') { tab[index] = 'G';}
                if(a =='B' && b == 'G') { tab[index] = 'R';}

                if(c == d) { tab[length - index - 2] = c;}
                if(c =='R' && d == 'G') { tab[length-index-2] = 'B';}
                if(c =='R' && d == 'B') { tab[length-index-2] = 'G';}
                if(c =='G' && d == 'B') { tab[length-index-2] = 'R';}
                if(c =='G' && d == 'R') { tab[length-index-2] = 'B';}
                if(c =='B' && d == 'R') { tab[length-index-2] = 'G';}
                if(c =='B' && d == 'G') { tab[length-index-2] = 'R';}
                index++;
            }
//            while(index < length -1){
//                tab[index] = tab[index+1];
//                index++;
//            }
//            tab[length-1]='0';
            length--;
            index = 0;
//            showTab(tab);
        }
        return tab[0];
    }
    // metoda oparta na 4 wartościach
    public static char triangle7(String firstRow){
        char[] tab = firstRow.toCharArray();
        int length = tab.length;
        int index = 0;
        int newIndex = 0;
        char a,b,d;
        while(length > 1 ){
            if(length > 3){
                while(index < length - 3){
                    a = tab[index]; d = tab[index+3];
                    if(a == 'R'){
                        if(d == 'R'){tab[newIndex++] = 'R';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'B';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'G';index++;continue;}
                    }
                    if(a == 'G'){
                        if(d == 'R'){tab[newIndex++] = 'B';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'G';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'R';index++;continue;}
                    }
//                    if(a == 'B'){
                        if(d == 'R'){tab[newIndex++] = 'G';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'R';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'B';index++;continue;}
//                    }
                }
            }else{
                while(index < length - 1){
                    a = tab[index]; b = tab[index + 1];
                    if(a == b) { tab[newIndex++] = a;}
                    if(a =='R' && b == 'G') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='R' && b == 'B') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='G' && b == 'B') { tab[newIndex++] = 'R';index++;continue;}
                    if(a =='G' && b == 'R') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='B' && b == 'R') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='B' && b == 'G') { tab[newIndex++] = 'R';index++;continue;}
                    index++;
                }
            }
            length = newIndex;
            index = 0;
            newIndex = 0;
        }

        return tab[0];
    }
    public static char triangle8(String firstRow){
        char a;
        int countR = 0; int countG = 0; int countB = 0;
        for (int i = 0; i < firstRow.length(); i++) {
            a = firstRow.charAt(i);
            if(a == 'R') countR++;
            if(a == 'G') countG++;
            if(a == 'B') countB++;
        }
        String s = "";
        if(countR%2 != 0){
            s = s + 'R';
        }
        if(countG%2 != 0){
            s = s + 'G';
        }
        if(countB%2 != 0){
            s = s + 'B';
        }

        System.out.println("countR = " + countR + " countG =  " + countG + " countB = " + countB);
        System.out.println(" s = " + s);
        return 'B';
    }

    // metoda oparta na 10 wartościach
    public static char triangle10(String firstRow){
        char[] tab = firstRow.toCharArray();
        int length = tab.length;
        int index = 0;
        int newIndex = 0;
        char a,b,d;
        while(length > 1 ){
            if(length > 10){//10
                while(index < length - 9){ //9
                    a = tab[index]; d = tab[index + 9];//9
                    if(a == 'R'){
                        if(d == 'R'){tab[newIndex++] = 'R';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'B';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'G';index++;continue;}
                    }
                    if(a == 'G'){
                        if(d == 'R'){tab[newIndex++] = 'B';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'G';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'R';index++;continue;}
                    }
//                    if(a == 'B'){
                    if(d == 'R'){tab[newIndex++] = 'G';index++;continue;}
                    if(d == 'G'){tab[newIndex++] = 'R';index++;continue;}
                    if(d == 'B'){tab[newIndex++] = 'B';index++;continue;}
//                    }
                }
            }else{
                while(index < length - 1){
                    a = tab[index]; b = tab[index + 1];
                    if(a == b) { tab[newIndex++] = a;}
                    if(a =='R' && b == 'G') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='R' && b == 'B') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='G' && b == 'B') { tab[newIndex++] = 'R';index++;continue;}
                    if(a =='G' && b == 'R') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='B' && b == 'R') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='B' && b == 'G') { tab[newIndex++] = 'R';index++;continue;}
                    index++;
                }
            }
            length = newIndex;
            index = 0;
            newIndex = 0;
        }

        return tab[0];
    }

    // metoda oparta na N wartościach
    public static char triangleN(String firstRow, int n){
        char[] tab = firstRow.toCharArray();
        int length = tab.length;
        int index = 0;
        int newIndex = 0;
        char a,b,d;
        while(length > 1 ){
            if(length > n){//10
                while(index < length - (n-1)){ //9
                    a = tab[index]; d = tab[index + (n-1)];//9
                    if(a == 'R'){
                        if(d == 'R'){tab[newIndex++] = 'R';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'B';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'G';index++;continue;}
                    }
                    if(a == 'G'){
                        if(d == 'R'){tab[newIndex++] = 'B';index++;continue;}
                        if(d == 'G'){tab[newIndex++] = 'G';index++;continue;}
                        if(d == 'B'){tab[newIndex++] = 'R';index++;continue;}
                    }
//                    if(a == 'B'){
                    if(d == 'R'){tab[newIndex++] = 'G';index++;continue;}
                    if(d == 'G'){tab[newIndex++] = 'R';index++;continue;}
                    if(d == 'B'){tab[newIndex++] = 'B';index++;continue;}
//                    }
                }
            }else{
                while(index < length - 1){
                    a = tab[index]; b = tab[index + 1];
                    if(a == b) { tab[newIndex++] = a;}
                    if(a =='R' && b == 'G') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='R' && b == 'B') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='G' && b == 'B') { tab[newIndex++] = 'R';index++;continue;}
                    if(a =='G' && b == 'R') { tab[newIndex++] = 'B';index++;continue;}
                    if(a =='B' && b == 'R') { tab[newIndex++] = 'G';index++;continue;}
                    if(a =='B' && b == 'G') { tab[newIndex++] = 'R';index++;continue;}
                    index++;
                }
            }
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
    public static void test2(int length){
        String s = genFirstRow(length);
//        String s= "RRGBBGBRGR";
        System.out.println(s);
        System.out.println(" first line -> " + length);
        long start = System.currentTimeMillis();
//        System.out.println("Trinangle -> " + triangle(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
//        start = System.currentTimeMillis();
//        System.out.println("Trinangle1 -> " + triangle1(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
//        start = System.currentTimeMillis();
//        System.out.println("Trinangle2 -> " + triangle2(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("Trinangle4 2 znaki(37s)-> " + triangle4(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("Trinangle5 3 znaki (20s)-> " + triangle5(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
//        start = System.currentTimeMillis();
//        System.out.println("Trinangle6 -> " + triangle6(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("Trinangle7 4 znaki (13s)-> " + triangle7(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
//        start = System.currentTimeMillis();
//        System.out.println("Trinangle8 -> " + triangle8(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("Trinangle10 10 znaków (6s)-> " + triangle10(s) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("TrinangleN N = 28 (2s)-> " + triangleN(s,28) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("TrinangleN N = 82 (0.7 s)-> " + triangleN(s,82) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("TrinangleN N = 244 (0.2 s)-> " + triangleN(s,244) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("TrinangleN N = 730 (0.07)-> " + triangleN(s,730) + "  " + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        System.out.println("TrinangleN N = 2188 (0.03)-> " + triangleN(s,2188) + "  " + (System.currentTimeMillis() - start) + " ms" );
    }
    public static void searchN(int maxN){
        for (int n = 3; n < maxN; n++) {
            boolean equals = true;
            for (int i = 0; i < 100; i++) {
                String s = genFirstRow(5000);
                char triangleN = triangleN(s, n);
                char triangle10 = triangle10(s);
                if(triangleN != triangle10){
                    equals = false;
                    break;
                }
            }
            if(equals){
                System.out.println(" n = " + n);
            }
        }
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
