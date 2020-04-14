public class Snakes_and_Ladders {
    public static void main(String[] args) {
        SnakesLadders snakesLadders = new SnakesLadders();
//        assertEquals("Player 1 is on square 38", game.play(1, 1));
//        assertEquals("Player 1 is on square 44", game.play(1, 5));
//        assertEquals("Player 2 is on square 31", game.play(6, 2));
//        assertEquals("Player 1 is on square 25", game.play(1, 1));
        System.out.println("Player 1 is on square 38 -> " + snakesLadders.play(1, 1));
        System.out.println("Player 1 is on square 44 -> " + snakesLadders.play(1, 5));
        System.out.println("Player 2 is on square 31 -> " + snakesLadders.play(6, 2));
        System.out.println("Player 1 is on square 25 -> " + snakesLadders.play(1, 1));
    }

}



class SnakesLadders {
    private int[] board;
    private int[] playersScore;
    private int actualPlayer, nextPlayer;
    private boolean gameOver;

    public SnakesLadders() {
        board = new int[100];
        for (int i = 0; i < 100; i++) {
            board[i] = 0;
        }
        //ladders
        board[2-1] = 36;     board[7-1] = 7;    board[8-1] = 23;    board[15-1] = 11;   board[21-1] = 21;
        board[28-1] = 56;    board[36-1] = 8;   board[51-1] = 16;   board[71-1] = 20;   board[78-1] = 20;
        board[87-1] = 7;
        //snakes
        board[16-1] = -10;  board[46-1] = -21;  board[49-1] = -38;  board[62-1] = -43;  board[64-1] = -4;
        board[74-1] = -21;  board[89-1] = -21;  board[92-1] = -4;   board[95-1] = -20;  board[99-1] = -19;
        playersScore = new int[]{0, 0};
        actualPlayer = 1;
        nextPlayer = 1;
        gameOver = false;
    }
    public String play(int die1, int die2) {
        if(gameOver == true){
            return "Game over!";
        }
        actualPlayer = nextPlayer;
        if(die1 != die2){
            if (nextPlayer == 1){
                nextPlayer = 2;
            }else{
                nextPlayer = 1;
            }
        }
        playersScore[actualPlayer-1] += (die1 + die2);
        if(playersScore[actualPlayer-1] == 100){
            gameOver = true;
            return "Player "+actualPlayer+" Wins!";
        }
        if(playersScore[actualPlayer-1] > 100){
            playersScore[actualPlayer-1] = 200 - playersScore[actualPlayer-1];
        }
        int delta = board[playersScore[actualPlayer-1]-1];
        playersScore[actualPlayer-1] += delta;
        return "Player "+actualPlayer+" is on square "+playersScore[actualPlayer-1];
    }
}