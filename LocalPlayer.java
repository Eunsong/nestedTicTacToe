public class LocalPlayer implements GamePlayer{
   
    private TicTactics game;
    private final int side; // 1 if X, -1 if O
    private final String symbol;   
 
    public LocalPlayer(int side){
        assert ( side == 1 || side == -1 );
        this.side = side;
        this.symbol = ( side == 1? "X" : "O"); 
    }
    public void addToGame(TicTactics game){
        this.game = game;
    }

    public void play(){
        chooseSubBoard();
        int bigRow = this.game.getNextBigRow();
        int bigCol = this.game.getNextBigCol();
        play(bigRow, bigCol);
    }

    public void play(int bigRow, int bigCol){
        while (true){
            try{    
                chooseSubBoard();
                String whom = this.symbol;
                System.out.println(String.format(
                                   "%s Playing cell %1d-%1d", whom, bigRow, bigCol));
                System.out.println(String.format(
                                   "Enter row, col to place %s in the sub-board", whom));
                String[] tokens = this.game.readLine().trim().split(",");
                int subRow =  Integer.parseInt(tokens[0].trim());
                int subCol =  Integer.parseInt(tokens[1].trim());
                this.game.setNextBigRow(subRow);
                this.game.setNextBigCol(subCol);
                if ( this.side == 1 ) {
                    this.game.getBoard().putX(bigRow, bigCol, subRow, subCol);
                }
                else {
                    this.game.getBoard().putO(bigRow, bigCol, subRow, subCol);
                }
                this.game.setNextTurn();
                break;
            }
            catch ( Exception e){
                System.out.println("invalid selection. Try again.");
            }
        }
    }

    private void chooseSubBoard(){
        String whom = this.symbol; 
        TicTacticsBoard board = this.game.getBoard();
        int nextBigRow = this.game.getNextBigRow();
        int nextBigCol = this.game.getNextBigCol();
        while ( !isValid(nextBigRow, nextBigCol) ){
            System.out.println("Choose sub-board(row, col) to play : ");
            String[] tokens = this.game.readLine().trim().split(",");
            nextBigRow = Integer.parseInt(tokens[0].trim());
            nextBigCol = Integer.parseInt(tokens[1].trim());
        } 
        while ( board.evaluateSubBoard(nextBigRow, nextBigCol) != 0 ){
            System.out.println(String.format("sub-board %d-%d is already closed.",
                                             nextBigRow, nextBigCol));
            System.out.println(whom + " choose new sub-board(row, col) to play : ");
            String[] tokens = this.game.readLine().trim().split(",");
            nextBigRow = Integer.parseInt(tokens[0].trim());
            nextBigCol = Integer.parseInt(tokens[1].trim());
        }
        this.game.setNextBigRow(nextBigRow);
        this.game.setNextBigCol(nextBigCol);
    }

    private static boolean isValid(int row, int col){
        return (row >= 0 && row < 3 && col >= 0 && col < 3);
    }

}
