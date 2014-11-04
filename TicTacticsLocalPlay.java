import java.util.*;

/**
 * Basic local two-person play version of doubly nested tic-tac-toe
 * (a.k.a tic-tactics) game.
 *
 * @author Eunsong Choi
 * @version 1.0
 */

public class TicTacticsLocalPlay{

    private TicTacticsBoard board;
    private Scanner sc;
    private int whosTurn;
    private int nextBigRow, nextBigCol;

    private TicTacticsLocalPlay(){
        this.board = new TicTacticsBoard(); 
        this.sc = new Scanner(System.in);
        this.whosTurn = 1; // X always goes first 
    }
    public TicTacticsBoard getBoard() {
        return this.board;
    } 
    public void playNextTurn(){
        chooseSubBoard();
        int bigRow = this.nextBigRow;
        int bigCol = this.nextBigCol;
        if ( this.whosTurn == 1 ) playX(bigRow, bigCol);
        else playO(bigRow, bigCol);
    }
    public String readLine(){
        return this.sc.nextLine();
    }
    public void close(){
        this.sc.close();
    }
    
    private void playX(int bigRow, int bigCol){
        assert this.whosTurn == 1;
        while (true){
            try{
                System.out.println(String.format("X Playing cell %1d-%1d", bigRow, bigCol));
                System.out.println("Enter row, col to place X in the sub-board");
                String[] tokens = this.readLine().trim().split(",");
                int subRow =  Integer.parseInt(tokens[0].trim());
                int subCol =  Integer.parseInt(tokens[1].trim());
                this.nextBigRow = subRow;
                this.nextBigCol = subCol;
                board.putX(bigRow, bigCol, subRow, subCol);
                this.whosTurn = -1;
                break;
            }
            catch ( Exception e){
                System.out.println("invalid selection. Try again.");
            }
        }
    }
    private void playO(int bigRow, int bigCol){
        assert this.whosTurn == -1;
        while (true){
            try{
                System.out.println(String.format("O Playing cell %1d-%1d", bigRow, bigCol));
                System.out.println("Enter row, col to place O in the sub-board");
                String[] tokens = this.sc.nextLine().trim().split(",");
                int subRow =  Integer.parseInt(tokens[0].trim());
                int subCol =  Integer.parseInt(tokens[1].trim());
                this.nextBigRow = subRow;
                this.nextBigCol = subCol;
                board.putO(bigRow, bigCol, subRow, subCol);
                this.whosTurn = 1;
                break;
            }
            catch ( Exception e){
                System.out.println("invalid selection. Try again.");
            }
        }
    }
    private void chooseSubBoard(){
        String whom = (this.whosTurn == 1? "X" : "O" );
        while ( this.board.evaluateSubBoard(this.nextBigRow, this.nextBigCol) != 0 ){
            System.out.println(String.format("sub-board %d-%d is already closed.", 
                                             this.nextBigRow, this.nextBigCol)); 
            System.out.println(whom + " choose new sub-board(row, col) to play : ");
            String[] tokens = this.readLine().trim().split(",");
            this.nextBigRow = Integer.parseInt(tokens[0].trim());
            this.nextBigCol = Integer.parseInt(tokens[1].trim());
        }
    }


    public static void main(String[] args){
        
        TicTacticsLocalPlay game = new TicTacticsLocalPlay();
        TicTacticsBoard board = game.getBoard();
        board.printBoard();
        System.out.println("X goes first. Choose sub-board(row, col) to play :");
        while (true){
            try{
                String[] tokens = game.readLine().trim().split(",");
                int bigRow = Integer.parseInt(tokens[0].trim());
                int bigCol = Integer.parseInt(tokens[1].trim());
                game.playX(bigRow, bigCol);
                board.printBoard();
                break;
            }
            catch ( Exception e){
                System.out.println("invalid selection. Try again.");
            }
        }
        while (true){
            game.playNextTurn();
            board.printBoard();
            int result = board.evaluate();
            System.out.println(result);
            if ( result != 0 ){
                String whom = ( result == 1 ? "X" : "O");
                System.out.println(whom + " won the game!\n");
                game.close();
                System.exit(0);
            }
        }
    }

}
