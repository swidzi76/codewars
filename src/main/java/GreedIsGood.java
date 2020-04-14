
public class GreedIsGood {
    public static void main(String[] args) {
        // test
        System.out.println("Score for [5,1,3,4,1] must be 250:" + greedy(new int[]{5,1,3,4,1}));
        System.out.println("Score for [1,1,1,3,1] must be 1100:" + greedy(new int[]{1,1,1,3,1}));
        System.out.println("Score for [2,4,4,5,4] must be 450:" + greedy(new int[]{2,4,4,5,4}));
        System.out.println("Score for [2,2,2,2,2] must be 200:" + greedy(new int[]{2,2,2,2,2}));
    }
    public static int greedy(int[] dice){
        // Three 1's => 1000 points
        // Three 6's =>  600 points
        // Three 5's =>  500 points
        // Three 4's =>  400 points
        // Three 3's =>  300 points
        // Three 2's =>  200 points
        // One   1   =>  100 points
        // One   5   =>   50 point
        //code here
        int score = 0;
        int[] arrayFrequency = new int[]{0,0,0,0,0,0};
        for (int die : dice) {
            arrayFrequency[die-1]++;
        }
        // "1"
        if(arrayFrequency[0] == 1){
            score += 100;
        }
        if(arrayFrequency[0] == 2){
            score += 200;
        }
        if(arrayFrequency[0] == 3){
            score += 1000;
        }
        if(arrayFrequency[0] == 4){
            score += 1100;
        }
        if(arrayFrequency[0] == 5){
            score += 1200;
        }
        // "5"
        if(arrayFrequency[4] == 1){
            score += 50;
        }
        if(arrayFrequency[4] == 2){
            score += 100;
        }
        if(arrayFrequency[4] == 3){
            score += 500;
        }
        if(arrayFrequency[4] == 4){
            score += 550;
        }
        if(arrayFrequency[4] == 5){
            score += 600;
        }
        // --------------------
        if(arrayFrequency[1] >= 3){
            score += 200;
        }
        if(arrayFrequency[2] >= 3){
            score += 300;
        }
        if(arrayFrequency[3] >= 3){
            score += 400;
        }
        if(arrayFrequency[5] >= 3){
            score += 600;
        }



        return score;
    }
}
