public class Main_Jaden_Smith {
    public static void main(String[] args) {
        String string = "How can mirrors be real if our eyes aren't real";
        System.out.println(" String in : " + string);
        System.out.println(" String out: " + toJadenCase(string));
    }
    public static String toJadenCase(String phrase) {
        // TODO put your code below this comment
        if((phrase == null)||(phrase.isEmpty())){
            return null;
        }
        String[] tabString = phrase.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : tabString) {
            stringBuilder.append(s.substring(0,1).toUpperCase() + s.substring(1)+" ");
        }
        return stringBuilder.toString().trim();
    }

}
