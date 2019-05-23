package main;

import processing.core.PApplet;

public class Cell {
	int i;
	int j;
	boolean occupied;
	PApplet parent;

	Cell(int i, int j, boolean occupied, PApplet p) {
		this.i = i;
		this.j = j;
		this.occupied = occupied;
		parent = p;
	}
	
	void setPiece(boolean piece) {
		this.occupied = piece;
	}
}





/*
j-|
  |
  |
  |
  |
  |
j+|______________
  i-           i+
  */
  
