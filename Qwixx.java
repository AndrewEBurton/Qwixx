// -------------------------------------------------------
// Assignment  4
// Written by: Andrew Burton - 40077555
// For COMP 248 Section W – Winter 2018
// -----
//Player class for Qwixx game. Contains arrays of players and dice. 
import java.util.Scanner;
public class Qwixx {
	private Dice[] dice = new Dice[6];                                    //instance variable declaration
	private Player[] players;
	private boolean redLocked, yellowLocked, greenLocked, blueLocked;
	private static int NEGPTS = -5;
	
	public Qwixx(Player[] playersInput) {								//Constructor filling players array with player array inputed and initalizing the dice
		int n = playersInput.length;
		players = new Player[n];
		for (int i = 0; i<players.length;i++) {
			players[i] = playersInput[i];
		}
		
		dice[0] = new Dice("White1");
		dice[1] = new Dice("White2");
		dice[2] = new Dice("Red");
		dice[3] = new Dice("Yellow");
		dice[4] = new Dice("Green");
	    dice[5] = new Dice("Blue");		
	}
	
	public void rollDice() {									//Rolls all 6 dice
		for (int i=0;i < dice.length;i++) {
			dice[i].rollDice();
		}
	}
	
	public void printRolledDice() {							  //Prints result of dice roll
		for (int i=0;i < dice.length;i++) {
		System.out.print(dice[i]);
		}
		
	}
	
	public int getWhiteDiceTotal() {						//Calculates sum of the white dice
		int x = dice[0].getCurrentSide() + dice[1].getCurrentSide();
		return x;
	}

	public boolean checkValidMove(Player p, Move m) {    //Checks if a move is after the last one crossed and if the colour is locked
		boolean valid = false;	
	   switch(m.getColour()) {
	   case 'R': 
		   if (m.getNumber() > p.getRedNum()) {
			   valid = true;
		   }
		   if (redLocked) {
			   valid = false;
		   }
		   break;
	   case 'Y': 
		   if (m.getNumber() > p.getYellowNum()) {
			   valid = true;
		   }
		   if (yellowLocked) {
			   valid = false;
		   }
		   break;
	   case 'G': 
		   if (m.getNumber() < p.getGreenNum()) {
			   valid = true;
		   }
		   if (greenLocked) {
			   valid = false;
		   }
		   break;
	   case 'B': 
		   if (m.getNumber() < p.getBlueNum()) {
			   valid = true;
		   }
		   if (blueLocked) {
			   valid = false;
		   }
		   break;
			   
	   }		
		return valid;
	}
	
	public boolean checkColourFinished(Player p, char colour) {  //checks if a colour has been locked
		boolean finished = false;
		switch(colour) {
		   case 'R': 
			   if (p.getRedNum() == 12) {
				   finished = true;
				   redLocked = true;
				   System.out.println("Red is locked");
			   }
			   
			   break;
		   case 'Y': 
			   if (p.getYellowNum() == 12) {
				   finished = true;
				   yellowLocked = true;
				   System.out.println("Yellow is locked");
			   }
			
			   break;
		   case 'G': 
			   if (p.getGreenNum() == 2) {
				   finished = true;
				   greenLocked = true;
				   System.out.println("Green is locked");
			   }
			
			   break;
		   case 'B': 
			   if (p.getBlueNum() == 2) {
				   finished = true;
				   blueLocked = true;
				   System.out.println("Blue is locked");
			   }
			 
			   break;
				   
		   }	
		return finished;
		
	}
	public boolean checkGameFinished() {     //Checks if 2 or more colours have been locked or if someone has passed 4 times or more.
		boolean finished = false;
		int count = 0;
		if (redLocked) {
			count++;
		}
		if (yellowLocked) {
			count++;
		}
		if (greenLocked) {
			count++;
		}
		if (blueLocked) {
			count++;
		}
		
		if (count >= 2) {
			finished = true;
		}
		
		for (int i =0;i < players.length; i++)
		if (players[i].getNegativePoints() <= -20) {
			finished = true;
		}
		return finished;
	}
	
	public void playWhiteDiceMove() {                       //Gathers all required user input for the white dice moves and makes the corresponding move
		Scanner kb = new Scanner(System.in);				
		System.out.println();
		System.out.println("*****Move on white dice*****");
		System.out.println("The total for the white dice is " + getWhiteDiceTotal());
		System.out.println();
		
		for (int i = 0; i < players.length;i++) {
			System.out.println(players[i].getName() + " It's your turn...");
			players[i].printGameBoard();
			System.out.println();
			
			System.out.print("Would you like to cross off a number on the game board using the white dice total? (anything other than 'yes' is taken to mean no): ");
			String answer = kb.next();
			
			if(answer.equalsIgnoreCase("yes")) {
				System.out.print("What colour would you like to cross out? (R = red, Y = yellow, G = green, B = Blue): ");
				char colour = kb.next().charAt(0);
				Move m = new Move(colour, getWhiteDiceTotal());
				
				if(checkValidMove(players[i], m)) {
					players[i].makeMove(m);
					players[i].printGameBoard();
										
				}
			}
			System.out.println();
			
		}
	
	}
	public void playColouredDiceMove(Player p) {  //Gathers all required user input for the coloured dice moves then makes the corresponding move
		Scanner kb = new Scanner(System.in);
		System.out.println();
		System.out.println(p.getName() + "'s turn...");
		System.out.println();
		System.out.println("*****Moves on coloured dice*****");
		System.out.print("Would you like to cross off a number on the game board using one of the coloured dice and a white dice? ( anything other than 'yes' is taken to mean no): ");
		String reply = kb.next();
		
		if(reply.equalsIgnoreCase("yes")) {
			System.out.print("Which white dice would you like to you use? (White1 = 1, White2 = 2): ");
			int diceChoice = kb.nextInt();
			
			System.out.print("What colour would you like to cross out? (R = red, Y = yellow, G = green, B = Blue): ");
			char colourChoice = kb.next().charAt(0);
			int dieValue = 0;
			
			switch(colourChoice) {
			case 'R':
				dieValue = dice[2].getCurrentSide();
				break;
			case 'Y':
				dieValue = dice[3].getCurrentSide();
				break;
			case 'G':
				dieValue = dice[4].getCurrentSide();
				break;
			case 'B':
				dieValue = dice[5].getCurrentSide();
				
			}

			int value = dice[diceChoice - 1].getCurrentSide() +dieValue; ;
			
			Move m = new Move(colourChoice, value);
			
			if(checkValidMove(p, m)) {
				p.makeMove(m);
				p.printGameBoard();
			}
			
			
		}
		else {
			p.addNegativePoints(NEGPTS);
		}
		System.out.println();
				
	}
	public Player DetermineWinner(Player[] players) {  //Determines who has the most  points at the end of the game
		int maxValue = 0;
		int value;
		int index = 0;
		for (int i = 0; i < players.length; i++) {
			value = players[i].getBoardTotal();
			if (value > maxValue) {
				maxValue = value;
				index = i;
			}
			
		}
		return players[index];
		
	}
	
	public void Play() {                       //Loops the die rolling and user move methods while the game is not finished then prints the players scores and the winners after the game
		while(!checkGameFinished()) {
			System.out.println("New round!");
			System.out.println();
			for (int i=0;i<players.length;i++) {
			rollDice();
			printRolledDice();
			playWhiteDiceMove();
			playColouredDiceMove(players[i]);
			}
		}
		Player winner = DetermineWinner(players);
		for (int i=0;i<players.length;i++) {
			System.out.println(players[i].getName() + " scored " + players[i].getBoardTotal() + " points");
		}
		System.out.println("The winner is " + winner.getName());
		
	}
	
	
	

}
