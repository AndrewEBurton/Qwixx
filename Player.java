// -------------------------------------------------------
// Assignment  4
// Written by: Andrew Burton - 40077555
// For COMP 248 Section W – Winter 2018
// -----
//Player class for Qwixx game. Contains the players name, gameboard, and the last number they crossed out. 
public class Player {
	private String name;												//Instance variable declaration
	private String[][] gameBoard = new String[4][11];					
	private int redNum, yellowNum, greenNum, blueNum, negativePoints;
	
	public Player() {                  									//Default constructor				
		this.name = "";
		this.redNum = 0;
		this.yellowNum =0;
		this.greenNum = 13;
		this.blueNum = 13;
		this.negativePoints = 0;
		initializeGameboard();
				
	}
	public Player (String s) {											//Constructor if given name
		this.name = s;	
		this.redNum = 0;
		this.yellowNum =0;
		this.greenNum = 13;
		this.blueNum = 13;
		this.negativePoints = 0;
		initializeGameboard();
	}
	
																			//Accessor methods
	public String getName() {
		return this.name;
	}
	public int getRedNum() {
		return this.redNum;
	}
	public int getGreenNum() {
		return this.greenNum;
	}
	public int getYellowNum() {
		return this.yellowNum;
	}
	public int getBlueNum() {
		return this.blueNum;
	}
	public int getNegativePoints() {
		return this.negativePoints;
	}
	
	public void addNegativePoints(int pts) {  							//Adds given number of points to negative points   
		this.negativePoints += pts;
	}
	
	public void initializeGameboard() {								   //Fills the gameboard with the default numbers
		
		for (int i=0; i < 2; i++) {
			int num = 2;
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = num + "";
				num++;
			}
		}
		for (int i=2; i < gameBoard.length; i++) {
			int num = 12;
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = num + "";
				num--;
			}
		}
		
	}
	public void printGameBoard() {							//Prints the gameboard
		System.out.println(this.name + "'s gameboard");
		for (int i =0; i < gameBoard.length; i++) {
			switch(i) {
			case 0: 
				System.out.print("Red:    ");
				break;
			case 1:
				System.out.print("Yellow: ");
				break;
			case 2:
				System.out.print("Green:  ");
				break;
			case 3:
				System.out.print("Blue:   ");
				break;
			}
			for (int j=0; j < gameBoard[i].length; j++) {
				System.out.print(gameBoard[i][j] + " ");				
			}
			System.out.println();
		}
	}
	
	public void makeMove(Move m) {                       //Crosses out a spot on the gameboard based on a given move object
		int row = Move.convertColourToNum(m.getColour());
		 
		if (row == 0) {
			redNum = m.getNumber();
			gameBoard[row][m.getNumber() - 2] = "x";					
		}
		else if (row == 1) {
			yellowNum = m.getNumber();
			gameBoard[row][m.getNumber() - 2] = "x";	
			
		}
		else if (row == 2){
			greenNum = m.getNumber();
			gameBoard[row][12 - m.getNumber()] = "x";
		}
		else if (row == 3) {
			blueNum = m.getNumber();
			gameBoard[row][12 - m.getNumber()] = "x";
			
		}
	}
	
	public int getBoardTotal() {                       //Calculates the score of the Players gameboard and subtracts their amount of negative points
		int count=0;
		int[] values = {1,3,6,10,15,21,28,36,45,55,66,78};
		for (int i =0; i < gameBoard.length; i++) {
			for (int j=0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j].equals("x")) {
					count++;
				}
			}
		}
		
	  int points = values[count - 1] + this.negativePoints;
	  return points;
		
	}
	
	
	
	
	

}
