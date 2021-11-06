// 5 kyu 422

// input array
//APP_PASSIVE_OPEN, APP_ACTIVE_OPEN, APP_SEND, APP_CLOSE, APP_TIMEOUT, RCV_SYN, RCV_ACK, RCV_SYN_ACK, RCV_FIN, RCV_FIN_ACK

// states
// CLOSED, LISTEN, SYN_SENT, SYN_RCVD, ESTABLISHED, CLOSE_WAIT, LAST_ACK, FIN_WAIT_1, FIN_WAIT_2, CLOSING, TIME_WAIT

//INITIAL_STATE: EVENT -> NEW_STATE
//CLOSED: APP_PASSIVE_OPEN -> LISTEN
//CLOSED: APP_ACTIVE_OPEN  -> SYN_SENT
//LISTEN: RCV_SYN          -> SYN_RCVD
//LISTEN: APP_SEND         -> SYN_SENT
//LISTEN: APP_CLOSE        -> CLOSED
//SYN_RCVD: APP_CLOSE      -> FIN_WAIT_1
//SYN_RCVD: RCV_ACK        -> ESTABLISHED
//SYN_SENT: RCV_SYN        -> SYN_RCVD
//SYN_SENT: RCV_SYN_ACK    -> ESTABLISHED
//SYN_SENT: APP_CLOSE      -> CLOSED
//ESTABLISHED: APP_CLOSE   -> FIN_WAIT_1
//ESTABLISHED: RCV_FIN     -> CLOSE_WAIT
//FIN_WAIT_1: RCV_FIN      -> CLOSING
//FIN_WAIT_1: RCV_FIN_ACK  -> TIME_WAIT
//FIN_WAIT_1: RCV_ACK      -> FIN_WAIT_2
//CLOSING: RCV_ACK         -> TIME_WAIT
//FIN_WAIT_2: RCV_FIN      -> TIME_WAIT
//TIME_WAIT: APP_TIMEOUT   -> CLOSED
//CLOSE_WAIT: APP_CLOSE    -> LAST_ACK
//LAST_ACK: RCV_ACK        -> CLOSED

public class A_Simplistic_TCP_Finite_State_Machine_FSM_4kyu {
    public static void main(String[] args) {
        SampleTests();
    }

    public static String traverseStates(String[] events) {
        States state = States.CLOSED;
        String[][] fsm = new String[States.values().length][Input.values().length];
        setFsmArray(fsm);
        String newState;
        for (String event : events) {
            newState = fsm[state.ordinal()][Input.valueOf(event).ordinal()];
            if(newState.equals("ERROR")) return "ERROR";
            state = States.valueOf(newState);
        }
        return state.name();
    }
    private enum Input {
        APP_PASSIVE_OPEN, APP_ACTIVE_OPEN, APP_SEND, APP_CLOSE, APP_TIMEOUT,
        RCV_SYN, RCV_ACK, RCV_SYN_ACK, RCV_FIN, RCV_FIN_ACK;
    }
    private enum States {
        CLOSED, LISTEN, SYN_SENT, SYN_RCVD, ESTABLISHED, CLOSE_WAIT, LAST_ACK,
        FIN_WAIT_1, FIN_WAIT_2, CLOSING, TIME_WAIT;
    }

    private static void setFsmArray(String[][] fsm){
        // set values in table to "ERROR"
        for (int w = 0; w < fsm.length; w++) {
            for (int k = 0; k < fsm[w].length; k++) {
                fsm[w][k] = "ERROR";
            }
        }
        //INITIAL_STATE: EVENT -> NEW_STATE
        //CLOSED: APP_PASSIVE_OPEN -> LISTEN
        fsm[States.CLOSED.ordinal()][Input.APP_PASSIVE_OPEN.ordinal()] = States.LISTEN.name();
        //CLOSED: APP_ACTIVE_OPEN  -> SYN_SENT
        fsm[States.CLOSED.ordinal()][Input.APP_ACTIVE_OPEN.ordinal()] = States.SYN_SENT.name();
        //LISTEN: RCV_SYN          -> SYN_RCVD
        fsm[States.LISTEN.ordinal()][Input.RCV_SYN.ordinal()] = States.SYN_RCVD.name();
        //LISTEN: APP_SEND         -> SYN_SENT
        fsm[States.LISTEN.ordinal()][Input.APP_SEND.ordinal()] = States.SYN_SENT.name();
        //LISTEN: APP_CLOSE        -> CLOSED
        fsm[States.LISTEN.ordinal()][Input.APP_CLOSE.ordinal()] = States.CLOSED.name();
        //SYN_RCVD: APP_CLOSE      -> FIN_WAIT_1
        fsm[States.SYN_RCVD.ordinal()][Input.APP_CLOSE.ordinal()] = States.FIN_WAIT_1.name();
        //SYN_RCVD: RCV_ACK        -> ESTABLISHED
        fsm[States.SYN_RCVD.ordinal()][Input.RCV_ACK.ordinal()] = States.ESTABLISHED.name();
        //SYN_SENT: RCV_SYN        -> SYN_RCVD
        fsm[States.SYN_SENT.ordinal()][Input.RCV_SYN.ordinal()] = States.SYN_RCVD.name();
        //SYN_SENT: RCV_SYN_ACK    -> ESTABLISHED
        fsm[States.SYN_SENT.ordinal()][Input.RCV_SYN_ACK.ordinal()] = States.ESTABLISHED.name();
        //SYN_SENT: APP_CLOSE      -> CLOSED
        fsm[States.SYN_SENT.ordinal()][Input.APP_CLOSE.ordinal()] = States.CLOSED.name();
        //ESTABLISHED: APP_CLOSE   -> FIN_WAIT_1
        fsm[States.ESTABLISHED.ordinal()][Input.APP_CLOSE.ordinal()] = States.FIN_WAIT_1.name();
        //ESTABLISHED: RCV_FIN     -> CLOSE_WAIT
        fsm[States.ESTABLISHED.ordinal()][Input.RCV_FIN.ordinal()] = States.CLOSE_WAIT.name();
        //FIN_WAIT_1: RCV_FIN      -> CLOSING
        fsm[States.FIN_WAIT_1.ordinal()][Input.RCV_FIN.ordinal()] = States.CLOSING.name();
        //FIN_WAIT_1: RCV_FIN_ACK  -> TIME_WAIT
        fsm[States.FIN_WAIT_1.ordinal()][Input.RCV_FIN_ACK.ordinal()] = States.TIME_WAIT.name();
        //FIN_WAIT_1: RCV_ACK      -> FIN_WAIT_2
        fsm[States.FIN_WAIT_1.ordinal()][Input.RCV_ACK.ordinal()] = States.FIN_WAIT_2.name();
        //CLOSING: RCV_ACK         -> TIME_WAIT
        fsm[States.CLOSING.ordinal()][Input.RCV_ACK.ordinal()] = States.TIME_WAIT.name();
        //FIN_WAIT_2: RCV_FIN      -> TIME_WAIT
        fsm[States.FIN_WAIT_2.ordinal()][Input.RCV_FIN.ordinal()] = States.TIME_WAIT.name();
        //TIME_WAIT: APP_TIMEOUT   -> CLOSED
        fsm[States.TIME_WAIT.ordinal()][Input.APP_TIMEOUT.ordinal()] = States.CLOSED.name();
        //CLOSE_WAIT: APP_CLOSE    -> LAST_ACK
        fsm[States.CLOSE_WAIT.ordinal()][Input.APP_CLOSE.ordinal()] = States.LAST_ACK.name();
        //LAST_ACK: RCV_ACK        -> CLOSED
        fsm[States.LAST_ACK.ordinal()][Input.RCV_ACK.ordinal()] = States.CLOSED.name();
    }

    public static void SampleTests() {
        test("CLOSE_WAIT", new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN"});
        test("ESTABLISHED", new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK"});
        test("LAST_ACK", new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN", "APP_CLOSE"});
        test("SYN_SENT", new String[]{"APP_ACTIVE_OPEN"});
        test("ERROR", new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK", "APP_CLOSE", "APP_SEND"});
    }
    public static void test(String answer, String[] events) {
        if (answer == traverseStates(events)) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL!!");
        }
    }
    private static void showFsmArray(String[][] fsm){
        for (int w = 0; w < fsm.length; w++) {
            for (int k = 0; k < fsm[w].length; k++) {
                System.out.print(fsm[w][k]+" ,");
            }
            System.out.println("");
        }
        System.out.println("");

    }
}
