public class Two_to_One {
    public static void main(String[] args) {
//        a = "xyaabbbccccdefww"
//        b = "xxxxyyyyabklmopq"
//        longest(a, b) -> "abcdefklmopqwxy"
//
//        a = "abcdefghijklmnopqrstuvwxyz"
//        longest(a, a) -> "abcdefghijklmnopqrstuvwxyz"
        String a = "xyaabbbccccdefww";
        String b = "xxxxyyyyabklmopq";
        System.out.println(a + " i " + b + " => " + longest(a,b));
        if(longest(a,b).equals("abcdefklmopqwxy")){
            System.out.println("OK");
        }
        a = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(a + " i " + a + " => " + longest(a,a));
        if(longest(a,a).equals("abcdefghijklmnopqrstuvwxyz")){
            System.out.println("OK");
        }
    }
    public static String longest (String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        for (char i = 'a'; i <= 'z' ; i++) {
            if((s1.indexOf(i) != -1)||(s2.indexOf(i) != -1)){
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
