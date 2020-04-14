import java.util.*;
import java.util.stream.Collectors;

public class Permutations {
    public static void main(String[] args) {
//        Permutations.singlePermutations("a") `shouldBe`["a"]
//        Permutations.singlePermutations("ab") `shouldBe`["ab", "ba"]
//        Permutations.singlePermutations("aabb") `shouldBe`["aabb", "abab", "abba", "baab", "baba", "bbaa"]
        System.out.println(singlePermutations("a"));
        System.out.println(singlePermutations("ab"));
        System.out.println(singlePermutations("aabb"));
    }

    public static List<String> singlePermutations(String s) {
        Set<String> permutateSet = new TreeSet<String>();
        List<Character> list = s.chars().mapToObj(e -> (char)e)
                .collect(Collectors.toList());
        permutate(permutateSet, list, 0);
        return new ArrayList<>(permutateSet);
    }
    public static void permutate(Set<String> set, List<Character> arr, int k ){
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permutate(set, arr, k+1);
            Collections.swap(arr, k ,i);
        }
        if(k == arr.size()-1){
            set.add(arr.stream().map(String::valueOf).collect(Collectors.joining()));
        }

    }
}
