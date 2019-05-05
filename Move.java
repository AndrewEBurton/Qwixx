// -------------------------------------------------------
// Assignment  4
// Written by: Andrew Burton - 40077555
// For COMP 248 Section W – Winter 2018
// -----
//Move class for Qwixx game. Move contains the colour of the move and the number to be crossed. 
public class Move {
	private  char colour;               //instance variable declaration
	private int number;
	
	public Move(char col , int num) {  //constructor based on given colour and number
		this.colour = col;
		this.number = num;
	}
	
	public char getColour() {			//Accessor for colour variable
		return this.colour;
	}
	
	public int getNumber() {			//Acessor for number variable
		return this.number;
	}
	
	public void setColour(char c) {   //Mutator for colour variable
		this.colour = c;
	}
	
	public void setNumber (int x) {   //Mutator for number variable
		this.number = x;
	}
	
	public static int convertColourToNum(char colour) { //Converts the colour to it's corresponding gameboard array index
		int number = 0;
		switch (colour) {
			case 'R':
				number = 0;
				break;
			case 'Y':
				number = 1;
				break;
			case 'G':
				number = 2;
				break;
			case 'B':
				number = 3;
				break;
		}
		return number;
	}

}
