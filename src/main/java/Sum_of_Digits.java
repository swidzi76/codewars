public class Sum_of_Digits {
    public static void main(String[] args) {
//        digital_root(16)
//                => 1 + 6
//                => 7
//
//        digital_root(942)
//                => 9 + 4 + 2
//                => 15 ...
//                => 1 + 5
//                => 6
//
//        digital_root(132189)
//                => 1 + 3 + 2 + 1 + 8 + 9
//                => 24 ...
//                => 2 + 4
//                => 6
//
//        digital_root(493193)
//                => 4 + 9 + 3 + 1 + 9 + 3
//                => 29 ...
//                => 2 + 9
//                => 11 ...
//                => 1 + 1
//                => 2
        int a = 16;
        System.out.println(a + " => " + digital_root(a) +" => "+ (digital_root(a)==7?"OK":"NO"));
        a = 942;
        System.out.println(a + " => " + digital_root(a) +" => "+ (digital_root(a)==6?"OK":"NO"));
        a = 132189;
        System.out.println(a + " => " + digital_root(a) +" => "+ (digital_root(a)==6?"OK":"NO"));
        a = 493193;
        System.out.println(a + " => " + digital_root(a) +" => "+ (digital_root(a)==2?"OK":"NO"));
    }
    public static int digital_root(int n) {
        String string = String.valueOf(n);
        if(string.length() == 1){
            return Integer.valueOf(string);
        }
        int sum = 0;
        for (int i =0; i < string.length(); i++) {
            sum += Integer.valueOf(string.substring(i,i+1));
        }
        return digital_root(sum);
    }
}
