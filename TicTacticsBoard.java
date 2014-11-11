/**
 * Class representing a board of Tic-Tactics(or double-nested tic-tac-toe) game.
 * This class object contains internal SubBoard double-array, and methods to
 * evaluate winnings.
 *
 * @author Eunsong Choi (eunsong.choi@gmail.com)
 * @version 1.0
 */

public class TicTacticsBoard{

    private SubBoard[][] board;

    public TicTacticsBoard(){
        this.board = new SubBoard[3][3];
        for ( int row = 0; row < 3; row++){
            for ( int col = 0; col < 3; col++){
                this.board[row][col] = new SubBoard();
            }
        }
    }

    public SubBoard getSubBoard(int bigRow, int bigCol){
        return this.board[bigRow][bigCol];
    }

    public void putX(int bigRow, int bigCol, int subRow, int subCol){
        this.board[bigRow][bigCol].putX(subRow, subCol);
    }    

    public void putO(int bigRow, int bigCol, int subRow, int subCol){
        this.board[bigRow][bigCol].putO(subRow, subCol);
    }    

    /***************************************************************
     *                      Evaluation methods                     *
     ***************************************************************/
    public int evaluate(){
        int totalState = 0;
        // check diagonals
        int diagResult = checkDiags();
        totalState += diagResult;
        if ( diagResult != 0 && diagResult != -3 ){
            return diagResult;
        }
        int rowResult = 0;
        for ( int col = 0; col < 3; col++){
            rowResult = checkRow(col);
            totalState += rowResult;
            if ( rowResult != 0 && rowResult != -3 ) return rowResult;
        }
        int colResult = 0;
        for ( int row = 0; row < 3; row++){
            colResult = checkCol(row);
            totalState += colResult;
            if ( colResult != 0 && colResult != -3 ) return colResult;
        }
        if ( totalState == -21 ) return -3; // draw (but the board is not fully occupied)
        return 0; // draw or undetermined        
    }
    private int checkDiags(){
        if ((( this.board[0][0].evaluate() == this.board[1][1].evaluate() )&&
             (this.board[0][0].evaluate() == this.board[2][2].evaluate()) ) ||
           (( this.board[0][2].evaluate() == this.board[1][1].evaluate() )&&
             (this.board[0][2].evaluate() == this.board[2][0].evaluate() ))){
            return this.board[1][1].evaluate();
        }
        return -3;
    }
    private int checkRow(int col){
        if ( this.board[0][col].evaluate() == this.board[1][col].evaluate() &&
             this.board[0][col].evaluate() == this.board[2][col].evaluate() ){
            return this.board[0][col].evaluate();
        }
        return -3;
    }
    private int checkCol(int row){
        if ( this.board[row][0].evaluate() == this.board[row][1].evaluate() &&
             this.board[row][0].evaluate() == this.board[row][2].evaluate() ){
            return this.board[row][0].evaluate();
        }
        return -3;
    }
    public int evaluateSubBoard(int row, int col){
        return this.board[row][col].evaluate();
    }
    /***************************************************************/



}
