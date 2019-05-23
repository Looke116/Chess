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
  
  
  [0][0],[0][1],[0][2],[0][3],[0][4],[0][5],[0][6],[0][7]
  [1][0],[1][1],[1][2],[1][3],[1][4],[1][5],[1][6],[1][7]
  [2][0],[2][1],[2][2],[2][3],[2][4],[2][5],[2][6],[2][7]
  [3][0],[3][1],[3][2],[3][3],[3][4],[3][5],[3][6],[3][7]
  */
  
