package SearchPath;

import java.util.ArrayList;
import java.util.List;

public class Path {
    public static void main(String[] args) {
        // program szuka ścieżki w grafie - macieży wierzchołków zawierających wszystkie wierzchołki o zadanej długości
        // dane n, m, k
        // n- liczba wierszy
        // m - liczba kolumn
        // k - długość scieżki
        // liczba wierzchołków n*m - numeracja wierszami od 0 do nm -1
        // nr wierzchołka o wsp. i, j i(1..n) j(1..m)
        // w = (i-1)*m + (j-1)
        // w drógą stronę
        // i = (w / m) + 1
        // j = w - (i-1)*m + 1

     }

     public static void path(int n, int m, int k){
         List<Integer> list = new ArrayList<Integer>();
         int startPoint = 0;
         searchPath(list, startPoint);
     }
     public static void searchPath(List<Integer> list, int point){
        // w liscie mamy poprzednie wierzchołki i następny do point

     }
}
