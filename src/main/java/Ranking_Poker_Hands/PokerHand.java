//A famous casino is suddenly faced with a sharp decline of their revenues. They decide to offer Texas hold'em also online. Can you help them by writing an algorithm that can rank poker hands?
//
//Task
//Create a poker hand that has a method to compare itself to another poker hand:
//
//Result PokerHand.compareWith(PokerHand hand);
//A poker hand has a constructor that accepts a string containing 5 cards:
//
//PokerHand hand = new PokerHand("KS 2H 5C JD TD");
//The characteristics of the string of cards are:
//
//Each card consists of two characters, where
//The first character is the value of the card: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)
//The second character represents the suit: S(pades), H(earts), D(iamonds), C(lubs)
//A space is used as card separator between cards
//The result of your poker hand compare can be one of these 3 options:
//
//public enum Result
//{
//    WIN,
//    LOSS,
//    TIE
//}
//Notes
//Apply the Texas Hold'em rules for ranking the cards.
//Low aces are NOT valid in this kata.
//There is no ranking for the suits.
//If you finished this kata, you might want to continue with Sortable Poker Hands
package Ranking_Poker_Hands;

//Apply the Texas Hold'em rules for ranking the cards.
//Low aces are NOT valid in this kata.
//There is no ranking for the suits.
// value -> 2,3,4,5,6,7,8,9,10(T),11(J),12(Q),13(K),14(A)
//  suit -> S(spades - pik), H(heards - serce), D(diamonds - karo), C(clubs - trefl)

public class PokerHand {
    public enum HandValues{ NONE, P1, P2, C3, STR, FLUSH, FULL, C4, STR_FLUSH, ROYAL_FLUSH }
    public enum Result { TIE, WIN, LOSS }
    public String[] values = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    private HandValues handValue;
    private int[] handValues1;
    private String[] cards;
    private String[] cardsWithoutSuit;
    private int[] freqValues;

    PokerHand(String hand) {
        calculate(hand);    // initial setings -> cards, cardsWithoutSuit, freqValues,
        setHandValue();
    }
    private void setHandValue() {
        // ustalanie wartości HAND CARDS
        // ROYAL_FLUSH
        if(isFlush() && isStraight() && (cardsWithoutSuit[0].equals("A"))){
            handValue = HandValues.ROYAL_FLUSH;
            handValues1 = new int[1];
            handValues1[0] = getCardValue(cardsWithoutSuit[0]);
            return;
        }
        // STRAIGHT FLUSH
        if(isFlush() && isStraight()){
            handValue = HandValues.STR_FLUSH;
            handValues1 = new int[1];
            handValues1[0] = getCardValue(cardsWithoutSuit[0]);    // wartość karty max
            return;
        }
        // FOUR CARDS
        if(isFourCard()){
            handValue = HandValues.C4;
            handValues1 = new int[2];
            handValues1[0] = getCardFromFreqCards(4);
            handValues1[1] = getCardFromFreqCards(1);
            return;
        }
        // FULL
        if(isFull()){
            handValue = HandValues.FULL;
            handValues1 = new int[2];
            handValues1[0] = getCardFromFreqCards(3);
            handValues1[1] = getCardFromFreqCards(2);
            return;
        }
        // FLUSH
        if(isFlush()){
            handValue = HandValues.FLUSH;
            handValues1 = new int[5];
            for (int i = 0; i < cardsWithoutSuit.length; i++) {
                handValues1[i] = getCardValue(cardsWithoutSuit[i]);
            }
            return;
        }
        // STRAIGHT
        if(isStraight()){
            handValue = HandValues.STR;
            handValues1 = new int[1];
            handValues1[0] = getCardValue(cardsWithoutSuit[0]);    // max value card
            return;
        }
        // TREE CARDS
        if(isTreeCard()){
            handValue = HandValues.C3;
            handValues1 = new  int[3];  //3 + 1 + 1
            handValues1[0] = getCardFromFreqCards(3);
            int j = 1;
            for (int i = freqValues.length-1; i >=0; i--) {
                if(freqValues[i] == 1){
                    handValues1[j] = i + 2;
                    j++;
                }
            }
            return;
        }
        // TWO PAIRS
        if(isTwoPairs()){
            handValue = HandValues.P2;
            handValues1 = new int[3];   //2 + 2 + 1
            int j = 0;
            for (int i = freqValues.length - 1; i >= 0 ; i--) {
                if(freqValues[i] == 2){
                    handValues1[j] = i + 2;
                    j++;
                }
            }
            handValues1[2] = getCardFromFreqCards(1);
            return;
        }
        // ONE PAIR
        if(isOnePairs()){
            handValue = HandValues.P1;  //2 + 1 + 1 + 1
            handValues1 = new int[4];
            handValues1[0] = getCardFromFreqCards(2);
            int j = 1;
            for (int i = freqValues.length -1; i >=0 ; i--) {
                if(freqValues[i] == 1){
                    handValues1[j] = i + 2;
                    j++;
                }
            }
            return;
        }

        // none - highest card
        if (handValue == null){
            handValue = HandValues.NONE;
            handValues1 = new int[5];
            for (int i = 0; i < cardsWithoutSuit.length; i++) {
                handValues1[i] = getCardValue(cardsWithoutSuit[i]);
            }

        }

    }
    private void calculate(String hand){
        cards = hand.split(" ");
        cardsWithoutSuit = new String[5];
        for (int i = 0 ; i < 5; i++) {
            cardsWithoutSuit[i] = cards[i].substring(0,1);
        }
        sortCard();
        freqValues  = freqCards();
    }
    private void sortCard() {
        boolean change = false;
        String temp;
        do{
            change = false;
            for (int i = 0; i < cardsWithoutSuit.length-1; i++) {
                if(getCardValue(cardsWithoutSuit[i]) < getCardValue(cardsWithoutSuit[i+1])){
                    change = true;
                    temp = cardsWithoutSuit[i];
                    cardsWithoutSuit[i] = cardsWithoutSuit[i+1];
                    cardsWithoutSuit[i+1] = temp;
                }
            }

        }while(change);
    }
    public Result compareWith(PokerHand hand) {
        if(handValue.compareTo(hand.getHandValue()) > 0){
            return Result.WIN;
        }
        if(handValue.compareTo(hand.getHandValue()) < 0){
            return Result.LOSS;
        }
        if(handValue.compareTo(hand.getHandValue()) == 0){
            for (int i = 0; i < handValues1.length; i++) {
                if(handValues1[i] > hand.getHandValues1()[i]){
                    return Result.WIN;
                }
                if(handValues1[i] < hand.getHandValues1()[i]){
                    return Result.LOSS;
                }
            }
//            return Result.TIE;
        }
        return Result.TIE;
    }
    private int getCardValue(String card){
        for (int i = 0; i < values.length; i++) {
            if(values[i].equals(card)){
                return i +2;
            }
        }
        return 0;
    }
    // zwraca kartę która wystapiła w freqValues numberFraq razy - jeżeli nie ma to zwraca 0
    private int getCardFromFreqCards(int numberFreq){
        for (int i = 0; i < freqValues.length; i++) {
            if(freqValues[i] == numberFreq){
                return i + 2;
            }
        }
        return 0;
    }
    private int[] freqCards(){
        int[] freqValues = new int[13];

        for (String s : cardsWithoutSuit) {
            if(Character.isDigit(s.charAt(0))){
                freqValues[Integer.valueOf(s) - 2]++;
            }
            if(s.equals("T")){
                freqValues[10-2]++;
            }
            if(s.equals("J")){
                freqValues[11-2]++;
            }
            if(s.equals("Q")){
                freqValues[12-2]++;
            }
            if(s.equals("K")){
                freqValues[13-2]++;
            }
            if(s.equals("A")){
                freqValues[14-2]++;
            }
        }
        return freqValues;
    }
    private boolean isFourCard(){
        for (int i = 0; i < freqValues.length; i++) {
            if(freqValues[i] == 4){
                return true;
            }
        }
        return false;
    }
    private boolean isTreeCard(){
        for (int i = 0; i < freqValues.length; i++) {
            if(freqValues[i] == 3){
                return true;
            }
        }
        return false;
    }
    private boolean isTwoPairs(){
        boolean one = false;
        boolean two = false;
        for (int i = 0; i < freqValues.length; i++) {
            if((freqValues[i] == 2) && (one == false)){
                one = true;
                continue;
            }
            if((freqValues[i] == 2) && (two == false)){
                two = true;
            }
        }
        return one && two;
    }
    private boolean isOnePairs(){
        boolean one = false;
        boolean two = false;
        for (int i = 0; i < freqValues.length; i++) {
            if((freqValues[i] == 2) && (one == false)){
                one = true;
                continue;
            }
            if((freqValues[i] == 2) && (two == false)){
                two = true;
            }
        }

        return one && !two;
    }
    private boolean isFull(){
        return isTreeCard() && isOnePairs();
    }
    private boolean isStraight(){
        //card sort max -> min
        for (int i = 0; i < cardsWithoutSuit.length-1 ; i++) {
            if((getCardValue(cardsWithoutSuit[i]) - getCardValue(cardsWithoutSuit[i+1])) != 1){
                return false;
            }
        }
        return true;
    }
    private boolean isFlush(){
        for (int i = 0; i < cards.length-1; i++) {
            if(!cards[i].substring(1,2).equals(cards[i+1].substring(1,2))){
                return false;
            }
        }
        return true;
    }
    public HandValues getHandValue(){
        return handValue;
    }
    public int[] getHandValues1(){
        return handValues1;
    }
}
