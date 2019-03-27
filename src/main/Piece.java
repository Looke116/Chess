package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Piece {
	int i;
	int j;
	int x;
	int y;
	int length;
	boolean white;
	boolean moving;
	PApplet parent;

	public Piece(int i, int j, int length, boolean white, PApplet p) {
		this.i = i;
		this.j = j;
		this.length = length;
		x = i * length;
		y = j * length;
		this.white = white;
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
}

class Pawn extends Piece {

	PImage blackPawn;
	PImage whitePawn;

	public Pawn(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackPawn = parent.loadImage("Image\\Black\\Pawn.png");
		whitePawn = parent.loadImage("Image\\White\\Pawn.png");
	}

	public void draw() {
		if (moving) {
			if (white) {
				parent.image(whitePawn, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(blackPawn, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (white) {
				parent.image(whitePawn, x, y, length, length);
			} else {
				parent.image(blackPawn, x, y, length, length);
			}
		}
	}
}

class Rook extends Piece {

	PImage blackRook;
	PImage whiteRook;

	public Rook(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackRook = parent.loadImage("Image\\Black\\Rook.png");
		whiteRook = parent.loadImage("Image\\White\\Rook.png");
	}

	public void draw() {
		if (moving) {
			if (white) {
				parent.image(whiteRook, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(blackRook, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (white) {
				parent.image(whiteRook, x, y, length, length);
			} else {
				parent.image(blackRook, x, y, length, length);
			}
		}
	}
}

class Knight extends Piece {

	PImage blackKnight;
	PImage whiteKnight;

	public Knight(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackKnight = parent.loadImage("Image\\Black\\Knight.png");
		whiteKnight = parent.loadImage("Image\\White\\Knight.png");
	}

	public void draw() {
		if (moving) {
			if (white) {
				parent.image(whiteKnight, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(blackKnight, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (white) {
				parent.image(whiteKnight, x, y, length, length);
			} else {
				parent.image(blackKnight, x, y, length, length);
			}
		}
	}
}

class Bishop extends Piece {

	PImage blackBishop;
	PImage whiteBishop;

	public Bishop(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackBishop = parent.loadImage("Image\\Black\\Bishop.png");
		whiteBishop = parent.loadImage("Image\\White\\Bishop.png");
	}

	public void draw() {
		if (moving) {
			if (white) {
				parent.image(whiteBishop, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(blackBishop, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (white) {
				parent.image(whiteBishop, x, y, length, length);
			} else {
				parent.image(blackBishop, x, y, length, length);
			}
		}
	}
}

class Queen extends Piece {

	PImage blackQueen;
	PImage whiteQueen;

	public Queen(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackQueen = parent.loadImage("Image\\Black\\Queen.png");
		whiteQueen = parent.loadImage("Image\\White\\Queen.png");
	}

	public void draw() {
		if (moving) {
			if (white) {
				parent.image(whiteQueen, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(blackQueen, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (white) {
				parent.image(whiteQueen, x, y, length, length);
			} else {
				parent.image(blackQueen, x, y, length, length);
			}
		}
	}
}

class King extends Piece {

	PImage blackKing;
	PImage whiteKing;

	public King(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackKing = parent.loadImage("Image\\Black\\King.png");
		whiteKing = parent.loadImage("Image\\White\\King.png");
	}

	public void draw() {
		if (moving) {
			if (white) {
				parent.image(whiteKing, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			} else {
				parent.image(blackKing, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
			}
		} else {
			if (white) {
				parent.image(whiteKing, x, y, length, length);
			} else {
				parent.image(blackKing, x, y, length, length);
			}
		}
	}
}