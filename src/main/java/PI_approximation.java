import java.text.DecimalFormat;

public class PI_approximation {
    public static void main(String[] args) {
        System.out.println("Dla 0.1 -> [10, 3.0418396189]");
        System.out.println(PiApprox.iterPi2String(0.1));

        System.out.println("Dla 0.001 -> [1000, 3.1405926538]");
        System.out.println(PiApprox.iterPi2String(0.001));

        System.out.println("Dla 0.01 -> [100, 3.1315929036]");
        System.out.println(PiApprox.iterPi2String(0.01));
    }

}
// PI / 4 = 1 - 1/3 + 1/5 - 1/7 + ... which gives an approximation of PI / 4.
class PiApprox {

    public static String iterPi2String(Double epsilon) {
        // your code
        double pi4 = 0;
        double pi = 0;
        int iter = 0;
        do{
            iter++;

            if(iter % 2 == 0 ){
                pi4-= 1/(double) (2*iter - 1);
            }
            else{
                pi4+= 1/(double)(2*iter - 1);
            }
            pi = pi4 *4;
        }while(Math.abs(Math.PI - pi) > epsilon);
        DecimalFormat df = new DecimalFormat("#.##########");

        return "[" + iter+ ", " + df.format(pi) +"]";
    }
}