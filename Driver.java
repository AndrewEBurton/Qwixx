// -------------------------------------------------------
// Assignment  4
// Written by: Andrew Burton - 40077555
// For COMP 248 Section W – Winter 2018
// -----
//Driver class for Qwixx game. Validates number of users playing then creates a player for each user and then runs the game. 
import java.util.Scanner;
public class Driver {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Amazing Qwixx Game!");                   //Intro message
		System.out.println();
		
		int numPlayers = 0;											//Validating number of players
		while (numPlayers < 2 || numPlayers > 5) {
		System.out.print("Please enter a number of players (2-5): ");
		numPlayers = kb.nextInt();
		}
		Player[] players = new Player[numPlayers]; 
		for (int i=0;i<numPlayers;i++) {
			System.out.print("Please enter name of player" + (i+1) + ": ");  //Getting name of each player and creating a Player object for them
			String name = kb.next();
			players[i] = new Player(name);
			players[i].printGameBoard();
			System.out.println();
		}
		
		Qwixx game = new Qwixx(players); //Creating new Qwixx object
		game.Play();                    //Running the game
		
		System.out.println();
		System.out.println("Thank you for playing!"); //Termination message
		kb.close();
		
		
		
		
		
		
		
	}

}
