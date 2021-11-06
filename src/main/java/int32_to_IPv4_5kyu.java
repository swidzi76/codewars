//2149583361 ==> "128.32.10.1"
//        32         ==> "0.0.0.32"
//        0          ==> "0.0.0.0"

public class int32_to_IPv4_5kyu {
    public static void main(String[] args) {
        long l1 = 2149583361L;
        long l2 = 32L;
        long l3 = 0L;

        System.out.println(l1 + " -> " + longToIP(l1));
        System.out.println(l2 + " -> " + longToIP(l2));
        System.out.println(l3 + " -> " + longToIP(l3));
    }

    public static String longToIP(long ip) {
        // Java doesn't have uint32, so here we use long to represent int32
        // zamiana liczby long na bin
        String s = getBin(ip);
        String s3 = getDec(s.substring(0,8)) + "." +getDec(s.substring(8,16))+"."+getDec(s.substring(16,24))+"."+getDec(s.substring(24,32));
        return s3; // do it!
    }
    public static String getDec(String value){
        int result = 0;
        int pot = 1;
        for (int i = value.length()-1; i >=0; i--) {
            if (value.charAt(i)=='1'){
                result+= pot;
            }
            pot*=2;
        }

        return String.valueOf(result);
    }
    public static String getBin(long value)
    {
        long dec = value;
        String binary="";

        while(dec > 0)
        {
            long rest=dec%2;
            dec/=2;

            binary=rest+binary;
        }
        while (binary.length()<32){
            binary = "0" + binary;
        }
        return binary;
    }
}
