package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Piece {
	int i;
	int j;
	int x;
	int y;
	int length;
	boolean isWhite;
	boolean moving;
	boolean taken;
	PApplet parent;
	PImage black;
	PImage white;

	public Piece(int i, int j, int length, boolean isWhite, PApplet p) {
		this.i = i;
		this.j = j;
		this.length = length;
		x = i * length;
		y = j * length;
		this.isWhite = isWhite;
		moving = false;
		taken = false;
		parent = p;
	}

	public Piece() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	void setIndex(int i, int j) {
		this.i = i;
		this.j = j;
		x = i * length;
		y = j * length;
	}

	boolean checkMouseLocation(int x, int y) {
		return (x > this.x && x < this.x + length && y > this.y && y < this.y + length);
	}

	public void draw() {
		if (moving) {
			if (isWhite) {
				parent.image(white, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(black, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (isWhite) {
				parent.image(white, x, y, length, length);
			} else {
				parent.image(black, x, y, length, length);
			}
		}
	}
}

class Pawn extends Piece {

	boolean canMoveTwo = true;

	public Pawn(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Pawn.png");
		white = parent.loadImage("Image\\White\\Pawn.png");
	}

	public Pawn() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkValidMove(int i, int j) {
		System.out.println(super.isWhite);
		return false;
	}
}

class Rook extends Piece {

	public Rook(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Rook.png");
		white = parent.loadImage("Image\\White\\Rook.png");
	}

	public Rook() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkValidMove(int i, int j) {
		System.out.println(super.isWhite);
		return false;
	}
}

class Knight extends Piece {

	public Knight(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Knight.png");
		white = parent.loadImage("Image\\White\\Knight.png");
	}

	public Knight() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}
}

class Bishop extends Piece {

	public Bishop(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Bishop.png");
		white = parent.loadImage("Image\\White\\Bishop.png");
	}

	public Bishop() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}
}

class Queen extends Piece {

	public Queen(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Queen.png");
		white = parent.loadImage("Image\\White\\Queen.png");
	}

	public Queen() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}
}

class King extends Piece {

	public King(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\King.png");
		white = parent.loadImage("Image\\White\\King.png");
	}

	public King() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}
}