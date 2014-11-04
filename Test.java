import java.util.*;


public class Test{
    public static void main(String[] args){

        TicTacticsBoard board = new TicTacticsBoard(); 
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                String[] tokens = sc.nextLine().trim().split(",");
                int bigRow = Integer.parseInt(tokens[0].trim());
                int bigCol = Integer.parseInt(tokens[1].trim());
                int subRow =  Integer.parseInt(tokens[2].trim());
                int subCol =  Integer.parseInt(tokens[3].trim());
                board.putX(bigRow, bigCol, subRow, subCol);
            }
            catch ( Exception e){
                System.out.println("invalid selection. Try again.");
            }
            board.printBoard();
            int result = board.evaluate();
            System.out.println(result);
            if ( result != 0 ){
                String whom = ( result == 1 ? "X" : "O");
                System.out.println(whom + " won the game!\n");
                System.exit(0);
            }
        }

    }
}
