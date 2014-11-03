import java.util.Arrays;

public class SubBoard{

    private int[][] board;

    public SubBoard(){
        this.board = new int[3][3];
        for ( int row = 0; row < 3; row++){
            Arrays.fill(this.board[row], 0);
        }
    }

    public void putX(int row, int col){
        checkValid(row, col);
        this.board[row][col] = 1;
    }

    public void putO(int row, int col){
        checkValid(row, col);
        this.board[row][col] = -1;
    } 
    private void resetCell(int row, int col){
        this.board[row][col] = 0;
    }

    
    public void printBoard(){
        System.out.println("    0   1   2  ");
        System.out.println(String.format("0 |%2d |%2d |%2d |", 
                                        board[0][0], board[0][1], board[0][2])); 
    
    }



    // return 1 if X won the game, -1 if O won the game, 0 otherwise
    public int evaluate(){
        // check diagonals
        int diag = checkDiags();
        if ( diag != 0){
            return diag;
        }
        int rowResult = 0;
        for ( int col = 0; col < 3; col++){
            rowResult = checkRow(col);
            if ( rowResult != 0 ) return rowResult;
        }
        int colResult = 0;
        for ( int row = 0; row < 3; row++){
            colResult = checkCol(row);
            if ( colResult != 0 ) return colResult;
        }
        return 0; // draw or undetermined 
    }

    private int checkDiags(){
        if ((( this.board[0][0] == this.board[1][1] )&&
             (this.board[0][0] == this.board[2][2]) ) || 
           (( this.board[0][2] == this.board[1][1] )&&
             (this.board[0][2] == this.board[2][0] ))){
            return this.board[1][1];
        }
        return 0;
    }
    private int checkRow(int col){
        if ( this.board[0][col] == this.board[1][col] &&
             this.board[0][col] == this.board[2][col] ){
            return this.board[0][col];
        }
        return 0;
    }
    private int checkCol(int row){
        if ( this.board[row][0] == this.board[row][1] &&
             this.board[row][0] == this.board[row][2] ){
            return this.board[row][0];
        }
        return 0;
    }
       
 

    // check if given row and col are valid un-played cell in the board
    // and if not throw BoardPlayException  
    private void checkValid(int row, int col){
        if ( row < 0 || row > 2 || col < 0 || col > 2 ){
            throw new BoardPlayException("Board index out of range");
        }
        else if ( this.board[row][col] != 0 ){
            throw new BoardPlayException("Already occupied cell");
        }
    }

}