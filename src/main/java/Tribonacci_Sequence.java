public class Tribonacci_Sequence {
    public static void main(String[] args) {
        System.out.println("[1,1,1,3,5,9,17,31,57,105]");
        showTable(tribonacci(new double []{1,1,1},10));
        System.out.println("[0,0,1,1,2,4,7,13,24,44]");
        showTable(tribonacci(new double []{0,0,1},10));
        System.out.println("[0,1,1,2,4,7,13,24,44,81]");
        showTable(tribonacci(new double []{0,1,1},10));
        System.out.println("[0]");
        showTable(tribonacci(new double []{0,8,9},1));
    }

    public static double[] tribonacci(double[] s, int n) {
        // hackonacci me
        double[] tri = new double[n];
        for(int i = 0; (i< 3 && i < n ); i++){
            tri[i] = s[i];
        }
        for (int i = 3; i <n ; i++) {
            tri[i] = tri[i-1] + tri[i-2] + tri[i-3];
        }
        return tri;
    }

    public static void showTable(double[] t){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (double d : t) {
            sb.append(Math.round(d)+", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
    }
}
