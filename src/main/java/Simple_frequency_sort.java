import java.util.*;
import java.util.stream.Collectors;

public class Simple_frequency_sort {
    public static void main(String[] args) {

        test(new int[]{2, 3, 5, 3, 7, 9, 5, 3, 7}, new int[]{3, 3, 3, 5, 5, 7, 7, 2, 9});
        test(new int[]{1, 2, 3, 0, 5, 0, 1, 6, 8, 8, 6, 9, 1}, new int[]{1, 1, 1, 0, 0, 6, 6, 8, 8, 2, 3, 5, 9});
        test(new int[]{5, 9, 6, 9, 6, 5, 9, 9, 4, 4}, new int[]{9, 9, 9, 9, 4, 4, 5, 5, 6, 6});
    }



//    OPIS PROGAMU
//    Solution.sortByFrequency(new int[]{2, 3, 5, 3, 7, 9, 5, 3, 7});
// Returns {3, 3, 3, 5, 5, 7, 7, 2, 9}
// We sort by highest frequency to lowest frequency.
// If two elements have same frequency, we sort by increasing value.
    private static void test(int[] array, int[] expected){
        System.out.println("input array");
        showArray(array);
        System.out.println("sortByFrequrecyArray ");
        showArray(sortByFrequency(array));
        System.out.println("expected response");
        showArray(expected);

    }
    private static int [] sortByFrequency(int[] array){
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            if(map.containsKey(array[i])){
                Integer count = map.get(array[i]);
                map.put(array[i], count+1);
            }else{
                map.put(array[i], 1);
            }
        }
        LinkedHashMap<Integer, Integer> sortedByValueMap = map.entrySet().stream().
                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        List<Integer> result = new ArrayList<>();
        Set<Integer> keys = sortedByValueMap.keySet();
        for (Integer key : keys) {
            for (int i = 0; i < sortedByValueMap.get(key); i++) {
                result.add(key);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }


    private static void showArray(int[] a) {
        String s = "[";
        for (int i = 0; i < a.length - 1 ; i++){
            s = s + a[i] + ", ";
        }
        s = s + a[a.length-1] + "]";
        System.out.println(s);
    }
}

