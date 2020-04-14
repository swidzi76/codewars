import java.util.Arrays;

public class Create_Phone_Number {
    public static void main(String[] args) {
//        Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println("tablica : " + Arrays.toString(array));
        System.out.println("nr tel: " + createPhoneNumber(array));

    }
    public static String createPhoneNumber(int[] numbers) {
        // Your code here!
        return "("+numbers[0]+numbers[1]+numbers[2]+")"+" "+
                numbers[3]+numbers[4]+numbers[5]+"-"+
                numbers[6]+numbers[7]+numbers[8]+numbers[9];
    }
}
