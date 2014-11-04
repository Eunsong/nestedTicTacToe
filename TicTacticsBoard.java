public class TicTacticsBoard{

    private SubBoard[][] board;
    private static final String horizontalLine = getHorizontalLine(45);
    private static final String horizontalSubLine = getHorizontalSubLine(11, 5); 

    public TicTacticsBoard(){
        this.board = new SubBoard[3][3];
        for ( int row = 0; row < 3; row++){
            for ( int col = 0; col < 3; col++){
                this.board[row][col] = new SubBoard();
            }
        }
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
        // check diagonals
        int diagResult = checkDiags();
        if ( diagResult != 0){
            return diagResult;
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
        if ((( this.board[0][0].evaluate() == this.board[1][1].evaluate() )&&
             (this.board[0][0].evaluate() == this.board[2][2].evaluate()) ) ||
           (( this.board[0][2].evaluate() == this.board[1][1].evaluate() )&&
             (this.board[0][2].evaluate() == this.board[2][0].evaluate() ))){
            return this.board[1][1].evaluate();
        }
        return 0;
    }
    private int checkRow(int col){
        if ( this.board[0][col].evaluate() == this.board[1][col].evaluate() &&
             this.board[0][col].evaluate() == this.board[2][col].evaluate() ){
            return this.board[0][col].evaluate();
        }
        return 0;
    }
    private int checkCol(int row){
        if ( this.board[row][0].evaluate() == this.board[row][1].evaluate() &&
             this.board[row][0].evaluate() == this.board[row][2].evaluate() ){
            return this.board[row][0].evaluate();
        }
        return 0;
    }
    /***************************************************************/




    /***************************************************************
     *                  board representations                      *
     ***************************************************************/
    public void printBoard(){
        System.out.println(horizontalLine);            
        for ( int bigRow = 0; bigRow < 3; bigRow++){
            for ( int subRow = 0; subRow < 3; subRow++){
                System.out.println(getRowString(bigRow, subRow));
                if ( subRow != 2 ) System.out.println(horizontalSubLine);
            }
            System.out.println(horizontalLine);
        }
    }
    public String getRowString(int bigRow, int subRow){
        String retval = "";
        for ( int bigCol = 0; bigCol < 3; bigCol++){
            SubBoard subBoard = this.board[bigRow][bigCol];
            retval += subBoard.getRowString(subRow);
            if ( bigCol != 2 ) retval += " | ";
        }
        return retval;
    }
    private static String getHorizontalLine(int size){
        return new String(new char[size]).replace("\0", "-");
    }
    private static String getHorizontalSubLine(int subsize, int spacingsize){
        String spacing = new String(new char[spacingsize]).replace("\0", " ");
        String retval = " ";
        for ( int col = 0; col < 3; col++){
            retval += getHorizontalLine(subsize);
            if ( col != 2 ) retval += spacing;
        }
        return retval;
    }
    /***************************************************************/


}
