import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Human_Readable_Time {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0,5,60,86399,359999);
        for (Integer time : list) {
            System.out.println(" time: "+time + " is in human redable : " + makeReadable(time));
        }
    }
    //Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)
    //
    //HH = hours, padded to 2 digits, range: 00 - 99
    //MM = minutes, padded to 2 digits, range: 00 - 59
    //SS = seconds, padded to 2 digits, range: 00 - 59
    //The maximum time never exceeds 359999 (99:59:59)
    public static String makeReadable(int seconds) {
        // Do something
        DecimalFormat df = new DecimalFormat("00");
        return df.format(seconds / 3600)+":"+df.format((seconds % 3600) / 60)+
                ":"+df.format((seconds % 3600) % 60);
    }
}
