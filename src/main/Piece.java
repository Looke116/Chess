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
	boolean taken;
	PApplet parent;
	PImage blackImage;
	PImage whiteImage;

	public Piece(int i, int j, int length, boolean white, PApplet p) {
		this.i = i;
		this.j = j;
		this.length = length;
		x = i * length;
		y = j * length;
		this.white = white;
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
		if (!this.taken) {
			if (moving) {
				if (white) {
					parent.image(whiteImage, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
				} else {
					parent.image(blackImage, parent.mouseX - length / 2, parent.mouseY - length / 2, length, length);
				}
			} else {
				if (white) {
					parent.image(whiteImage, x, y, length, length);
				} else {
					parent.image(blackImage, x, y, length, length);
				}
			}
		} else {
			// if a piece gets taken set everything to -1 so it won't intervene with other pieces or the game in general
			this.i = -1;
			this.j = -1;
			setIndex(-1, -1);
			this.x = -1;
			this.y = -1;
			this.length = -1;
			this.moving = false;
			this.blackImage = null;
			this.whiteImage = null;
		}
	}
}

class Pawn extends Piece {

	boolean canMoveTwo = true;

	public Pawn(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackImage = parent.loadImage("Image\\Black\\Pawn.png");
		whiteImage = parent.loadImage("Image\\White\\Pawn.png");
	}

	public Pawn() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkMove(int i, int j) {

		if (this.white) {
			if (this.canMoveTwo) {
				if (j < this.j && j > this.j - 3 && i == this.i) {
					this.canMoveTwo = false;
					return true;
				}
			} else {
				if (j < this.j && j > this.j - 2 && i == this.i) {
					this.canMoveTwo = false;
					return true;
				}
			}
		} else {
			if (this.canMoveTwo) {
				if (j > this.j && j < this.j + 3 && i == this.i) {
					this.canMoveTwo = false;
					return true;
				}
			} else {
				if (j > this.j && j < this.j + 2 && i == this.i) {
					this.canMoveTwo = false;
					return true;
				}
			}
		}

		return false;
	}

	boolean checkAttack(int i, int j, boolean isWhite) {

		if (this.white) {
			if (!isWhite) {
				if ((this.i + 1 == i) || (this.i - 1 == i)) {
					if (this.j - 1 == j) {
						return true;
					}
				}
			}
		} else {
			if (isWhite) {
				if ((this.i + 1 == i) || (this.i - 1 == i)) {
					if (this.j + 1 == j) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

class Rook extends Piece {

	public Rook(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackImage = parent.loadImage("Image\\Black\\Rook.png");
		whiteImage = parent.loadImage("Image\\White\\Rook.png");
	}

	public Rook() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkMove(int i, int j) {

		if (this.i == i) {
			return true;
		} else if (this.j == j) {
			return true;
		}
		return false;
	}

	boolean checkAttack(int i, int j, boolean isWhite) {
		if (this.white) {
			if (!isWhite) {
				if (this.i == i) {
					return true;
				} else if (this.j == j) {
					return true;
				}
			} 
		} else if (isWhite) {
			if (this.i == i) {
				return true;
			} else if (this.j == j) {
				return true;
			}
		}
		return false;
	}
}

class Knight extends Piece {

	public Knight(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackImage = parent.loadImage("Image\\Black\\Knight.png");
		whiteImage = parent.loadImage("Image\\White\\Knight.png");
	}

	public Knight() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkMove(int i, int j) {
		if ((this.i + 2 == i) || (this.i - 2 == i)) {
			if ((this.j + 1 == j) || (this.j - 1 == j)) {
				return true;
			}
		}
		if ((this.i + 1 == i) || (this.i - 1 == i)) {
			if ((this.j + 2 == j) || (this.j - 2 == j)) {
				return true;
			}
		}
		return false;
	}
}

class Bishop extends Piece {

	public Bishop(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackImage = parent.loadImage("Image\\Black\\Bishop.png");
		whiteImage = parent.loadImage("Image\\White\\Bishop.png");
	}

	public Bishop() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkMove(int i, int j) {
		if (Math.abs(this.i - i) == Math.abs(this.j - j)) {
			return true;
		}
		return false;
	}
}

class Queen extends Piece {

	public Queen(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackImage = parent.loadImage("Image\\Black\\Queen.png");
		whiteImage = parent.loadImage("Image\\White\\Queen.png");
	}

	public Queen() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkMove(int i, int j) {
		if (this.i == i) {
			return true;
		} else if (this.j == j) {
			return true;
		} else if (Math.abs(this.i - i) == Math.abs(this.j - j)) {
			return true;
		}
		return false;
	}
}

class King extends Piece {

	public King(int i, int j, int length, boolean white, PApplet p) {
		super(i, j, length, white, p);
		blackImage = parent.loadImage("Image\\Black\\King.png");
		whiteImage = parent.loadImage("Image\\White\\King.png");
	}

	public King() {
		// this is here just so I can make an empty object that is needed for the typePiece variables
	}

	boolean checkMove(int i, int j) {
		if ((this.i + 1 == i) || (this.i - 1 == i)) {
			if ((this.j + 1 == j) || (this.j - 1 == j)) {
				return true;
			}
		}
		if (this.i == i) {
			if ((this.j + 1 == j) || (this.j - 1 == j)) {
				return true;
			}
		}
		if (this.j == j) {
			if ((this.i + 1 == i) || (this.i - 1 == i)) {
				return true;
			}
		}
		return false;
	}
}