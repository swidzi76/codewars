public class IsPrime {
    public static void main(String[] args) {
        System.out.println("czy 1 jest pierwsze : " + isPrime(1));
        System.out.println("czy -2 jest pierwsze : " + isPrime(-2));
        System.out.println("czy 2 jest pierwsze : " + isPrime(2));
        System.out.println("czy 6 jest pierwsze : " + isPrime(6));
        System.out.println("czy 4 jest pierwsze : " + isPrime(4));
        System.out.println("czy 13 jest pierwsze : " + isPrime(13));
        System.out.println("czy 5099 jest pierwsze : " + isPrime(5099));
    }
    public static boolean isPrime(int number){
        if(number <= 1 ){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number) ; i++) {
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
