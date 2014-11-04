import java.util.*;


public class TestSubBoard{
    public static void main(String[] args){

        SubBoard board = new SubBoard();
        Scanner sc = new Scanner(System.in);
        while (true){
            String[] tokens = sc.nextLine().trim().split(",");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            board.putX(row, col);
            board.printBoard();
            System.out.println( board.evaluate());
        }

    }
}
