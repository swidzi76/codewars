import java.util.*;
import java.util.stream.Collectors;

// 5 kyu - 390
//        top_3_words("In a village of La Mancha, the name of which I have no desire to call to
//        mind, there lived not long since one of those gentlemen that keep a lance
//        in the lance-rack, an old buckler, a lean hack, and a greyhound for
//        coursing. An olla of rather more beef than mutton, a salad on most
//        nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
//        on Sundays, made away with three-quarters of his income.")
//        # => ["a", "of", "on"]
//
//        top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
//        # => ["e", "ddd", "aa"]
//
//        top_3_words("  //wont won't won't")
//        # => ["won't", "wont"]
public class _001_Most_frequently_used_words_in_a_text_4kyu {
    public static void main(String[] args) {
//    assertEquals(Arrays.asList("e", "d", "a"),    TopWords.top3("a a a  b  c c  d d d d  e e e e e"));
//    assertEquals(Arrays.asList("e", "ddd", "aa"), TopWords.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
//    assertEquals(Arrays.asList("won't", "wont"),  TopWords.top3("  //wont won't won't "));
//    assertEquals(Arrays.asList("e"),              TopWords.top3("  , e   .. "));
//    assertEquals(Arrays.asList(),                 TopWords.top3("  ...  "));
//    assertEquals(Arrays.asList(),                 TopWords.top3("  '  "));
//    assertEquals(Arrays.asList(),                 TopWords.top3("  '''  "));
//    assertEquals(Arrays.asList("a", "of", "on"),  TopWords.top3(Stream
        System.out.println("Powinno być   ->  jest  ");
        System.out.println("\"e\", \"d\", \"a\"" + " -> " + top3("a a a  b  c c  d d d d  e e e e e"));
        System.out.println("\"e\", \"ddd\", \"aa\"" + " -> " + top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
        System.out.println("\"won't\", \"wont\"" + " -> " + top3("  //wont won't won't "));
        System.out.println("\"e\"" + " -> " + top3("  , e   .. "));
        System.out.println("" + " -> " + top3("  ...  "));
        System.out.println("" + " -> " + top3("  '  "));
        System.out.println("" + " -> " + top3("  '''  "));
        System.out.println("\"a\", \"of\", \"on\"" + " -> " + top3("In a village of La Mancha, the name of which I have no desire to call to " +
                "mind, there lived not long since one of those gentlemen that keep a lance " +
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for " +
                "coursing. An olla of rather more beef than mutton, a salad on most " +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra " +
                "on Sundays, made away with three-quarters of his income."));
    }
    public static List<String> top3(String s) {
//        System.out.println("/////////////");
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z']", " ");
        String[] words = s.split(" ");
//        System.out.println("words -> " + Arrays.toString(words));
        Map<String, Integer> map = new TreeMap<>();
        for (String word : words) {
            // clean word
            word = word.toLowerCase().trim();
//            System.out.println(word + "lenght="+word.length() );
//            word = word.replaceAll("[^a-z']", "");
//            String[] clearChar = new String[]{"\\.", "\\,","\\:","\\\\","\\/"};
//            for (String strReg: clearChar) {
//                word = word.replaceAll(strReg,"");
//            }
            //count ' ' '
            if(word.indexOf('\\')!= -1){
                int counter = 0;
                for (int i = 0; i < word.length(); i++) {
                    if(word.charAt(i) == '\''){
                        counter++;
                    }
                }
                if(word.length() == counter)
                    word = "";
            }

            if(word.length()>0){
                if(map.get(word) == null ){
                    map.put(word,1);
                }else{
                    map.replace(word,map.get(word)+1 );
                }
            }
        }
//        System.out.println(map);
        // pobranie wartości
        Map sortedMapByValue = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//        System.out.println(sortedMapByValue);
        List list = Arrays.asList(sortedMapByValue.keySet().toArray());
//        System.out.println(list);
//        Map.Entry<String, Integer> actualValue = sortedMapByValue.entrySet()
//                .stream()
//                .findFirst()
//                .get();
//        System.out.println("/////////////");

        return list;
    }
}
