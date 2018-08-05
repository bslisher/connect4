package connect4;
import java.util.Scanner;

class Connect4Program {

    public static void main ( String [] arguments ) {
    	
    	GameState currentGameState;
    	Grid theGrid = new Grid();
    	
    	do {
    		
    		printGrid(theGrid);
    		
    		if (theGrid.currentDiscColor == DiscColor.Black) {
            	System.out.println("Hey, black player! Which column?");
    		}
    		else if (theGrid.currentDiscColor == DiscColor.Red) {
    			System.out.println("It's your turn, red player! Which column?");
    		}
    		        	
        	Scanner sc = new Scanner(System.in);
        	int column = sc.nextInt();
        	
        	theGrid.dropDisc(column);
    	} while ((currentGameState = theGrid.getGameState()) == GameState.InProgress);
    	
    	if (currentGameState == GameState.BlackWins) {
        	System.out.println("Black wins!");
    	}
    	else if (currentGameState == GameState.RedWins) {
        	System.out.println("Red wins!");
		}
		else if (currentGameState == GameState.Tie) {
        	System.out.println("It's a tie!");
		}
    }
    
    public static void printGrid(Grid grid) {

    	System.out.println("-----------------------------");

    	for (int i = 0; i < grid.discs[i].length; i++) {
    		
        	String line = "| ";
    		
        	for (int j = 0; j < grid.discs.length; j++) {
        		        		
        		if (grid.discs[j][i] != null) {
            		switch (grid.discs[j][i].getColor()) {
        				case Black:
        					line += "B";
        					break;
        				case Red:
        					line += "R";
        					break;
        				default:
        					line += "-";
            		}		
        		}
        		else {
					line += " ";
        		}
        		
        		line += " | ";
        	}
        	
        	System.out.println(line);
        	System.out.println("-----------------------------");
    	}
    		
    	System.out.println("");
    }
}