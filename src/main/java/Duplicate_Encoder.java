public class Duplicate_Encoder {
    public static void main(String[] args) {
//        "din"      =>  "((("
//        "recede"   =>  "()()()"
//        "Success"  =>  ")())())"
//        "(( @"     =>  "))(("
        System.out.println("din => "+encode("din"));
        System.out.println("recede => "+encode("recede"));
        System.out.println("Success => "+encode("Success"));
        System.out.println("(( @ => "+encode("(( @"));
    }
    static String encode(String word){
        word = word.toLowerCase();
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if(word.indexOf(word.charAt(i)) == word.lastIndexOf(word.charAt(i))){
                sb.append("(");
            }else{
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
