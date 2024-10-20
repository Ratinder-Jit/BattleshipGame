import java.util.Scanner;
import java.util.Arrays;

public class Battleship {
    Scanner input = new Scanner(System.in);
    // This method initializes all empty spaces of the 2D array to '-'
    public static void initializeBoard(int boardSize, char[][] P1, char[][] P2) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                P1[i][j] = '-';
                P2[i][j] = '-';

            }
        }
    }
    // This method determines who won by processing user information
    public static void determineWinner(char[][] P1, char[][] P2) {
        boolean player1HasBoats = false;
        boolean player2HasBoats = false;

        for (int i = 0; i < P1.length; i++) {
            for (int j = 0; j < P1[i].length; j++) {
                if (P1[i][j] == 'B') {
                    player1HasBoats = true;
                }
                if (P2[i][j] == 'B') {
                    player2HasBoats = true;
                }
            }
        }

        if (player1HasBoats && player2HasBoats) {
            System.out.println("Draw!");
        } else if (player1HasBoats) {
            System.out.println( "P1 Won!");
        } else if (player2HasBoats) {
            System.out.println("P2 Won!");
        } else {
            System.out.println("All destroyed");
        }
    }

    // This method prints the game board
    public static void printBoard(char[][] P1, char[][] P2) {
        for (int a = 0; a < P1.length; a++) {
            for (int b = 0; b < P1.length; b++) {
                System.out.print(P1[a][b]);
            }
            System.out.print("\t");
            for (int b = 0; b < P2.length; b++) {
                System.out.print(P2[a][b]);
            }
            System.out.println();
        }
        System.out.println();
    }
    // This method marks the coordiantes where the fired shots hit as 'X'
    public static void fireShot(char[][] board, Scanner input) {
        int shotX = input.nextInt();
        int shotY = input.nextInt();
        if (board[shotX][shotY] == 'B' || board[shotX][shotY] == '-') {
            board[shotX][shotY] = 'X';
        }
    }
    // This method adds boats to the game board
    public static void addBoats(char[][] board, Scanner input) {
        ;

        int xCoordinate = input.nextInt();
        int yCoordinate = input.nextInt();
        int boatSize = input.nextInt();
        int boatOrientation = input.nextInt();

        if (xCoordinate + boatSize > board.length && boatOrientation == 1) {
            boatSize = board.length  - xCoordinate;

        }
        if (yCoordinate + boatSize > board.length && boatOrientation ==0) {
            boatSize = board.length  - yCoordinate;

        }

        for (int j = 0; j < boatSize; j++) {
            if (boatOrientation == 0) {
                board[xCoordinate][yCoordinate + j] = 'B';
            } else {
                board[xCoordinate + j][yCoordinate] = 'B';

            }
        }
    }



    public static void main (String[]Args){
        Scanner input = new Scanner(System.in);

        int boardSize = input.nextInt();
        char[][] P1 = new char[boardSize][boardSize];
        char[][] P2 = new char[boardSize][boardSize];
        initializeBoard(boardSize, P1, P2);
        int noOfBoats = input.nextInt();
        // Adding boats for both players
        for (int i = 0; i < noOfBoats; i++) {
            addBoats(P1, input);
            addBoats(P2, input);
        }
        //Printing both boards
        printBoard(P1, P2);
        //Inputing number of shots
        int noOfShots = input.nextInt();
        //Firing all shots
        for (int k = 0; k < noOfShots; k++) {
            fireShot(P2, input);
            fireShot(P1, input);
        }
        printBoard(P1, P2);
        //Declaring the winner
        determineWinner(P1, P2);
    }
}
