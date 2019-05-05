// -------------------------------------------------------
// Assignment  4
// Written by: Andrew Burton - 40077555
// For COMP 248 Section W – Winter 2018
// -----
//Dice class for Qwixx game. Dice contains the colour of the die and the value it is landded on. 
import java.util.Random;

public class Dice {

	private int currentSide;               	//instance variable declaration  
	private String colour;
	
	
	public Dice() {							//Default constructor
		this.colour = "white";
		this.currentSide = rollDice();
	}
	
	public Dice(String colour) {			//Constructor taking in colour as input
		this.colour = colour;
		this.currentSide = rollDice();
				
	}
	
	public void setColour(String colour) { //Mutator for colour variable
		this.colour = colour;
	}
	
	public String getColour() {          //Accessor for colour variable
		return this.colour;
	}
	
	public int getCurrentSide() {       //Accessor for currentSide variable
		return this.currentSide;
	}
	public String toString() {         //toString method
		return (" " + this.colour + " dice: " + this.currentSide + " " + "| ");
	
	}
	
	public int rollDice() {			//generates random die roll
		Random rand = new Random();
		int x = rand.nextInt(6) + 1;
		return x;
	}
	
}
