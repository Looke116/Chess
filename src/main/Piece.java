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
		parent = p;
	}

	void setIndex(int i, int j) {
		this.i = i;
		this.j = j;
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

	public Pawn(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Pawn.png");
		white = parent.loadImage("Image\\White\\Pawn.png");
	}
}

class Rook extends Piece {

	public Rook(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Rook.png");
		white = parent.loadImage("Image\\White\\Rook.png");
	}
}

class Knight extends Piece {

	public Knight(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Knight.png");
		white = parent.loadImage("Image\\White\\Knight.png");
	}
}

class Bishop extends Piece {

	public Bishop(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Bishop.png");
		white = parent.loadImage("Image\\White\\Bishop.png");
	}
}

class Queen extends Piece {

	public Queen(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\Queen.png");
		white = parent.loadImage("Image\\White\\Queen.png");
	}
}

class King extends Piece {

	public King(int i, int j, int length, boolean isWhite, PApplet p) {
		super(i, j, length, isWhite, p);
		black = parent.loadImage("Image\\Black\\King.png");
		white = parent.loadImage("Image\\White\\King.png");
	}
}