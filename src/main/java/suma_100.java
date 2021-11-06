public class suma_100 {
    public static void main(String[] args) {
        int n = 3;
        int znalezionaLiczba = 0;
        long liczba = 199_999_999_998L;
        while(znalezionaLiczba < n){
            if(sumaCyfr(liczba) == 100){
                znalezionaLiczba++;
                System.out.println(liczba);
            }
            liczba++;
        }


    }
    public static long sumaCyfr(long liczba){
        long suma = 0;
        while(liczba >0){
            suma = suma + liczba%10;
            liczba = liczba / 10;
        }
        return suma;
    }
}
