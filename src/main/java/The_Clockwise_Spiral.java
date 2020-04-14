public class The_Clockwise_Spiral {
    public static void main(String[] args) {
        showArray(createSpiral(1));
        showArray(createSpiral(2));
        showArray(createSpiral(3));
        showArray(createSpiral(4));
        showArray(createSpiral(5));
        showArray(createSpiral(6));
    }

    public static int[][] createSpiral(int N) {
        // your code here
        if (N < 1) return new int[0][0];
        if (N == 1) return new int[][]{{1}};
        int[][] array = new int[N][N];
        int number = 0;
        int i = 0;
        int j = -1;
        int[] delta_i_step = new int[]{0,1,0,-1};
        int[] delta_j_step = new int[]{1,0,-1,0};
        int stepDelta = 0;
        int k_max = 1;
        int step = 0;
        int stepMax = N;
        while(stepMax > 0){
            for (int k = 0; k < k_max; k++) {
                while (step < stepMax ){
                    step++;
                    number++;
                    i += delta_i_step[stepDelta];
                    j += delta_j_step[stepDelta];
                    array[i][j] = number;
                }
                step = 0;
                // zmiana kierunku
                stepDelta++;
                if(stepDelta > 3) stepDelta = 0;
            }
            k_max = 2;
            stepMax--;
        }
        return array;
    }
    public static void showArray(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr[i].length ; j++) {
//                System.out.print(arr[i][j]+ " ");
                System.out.format("%-3d", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }

}
