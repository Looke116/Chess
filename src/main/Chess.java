package main;

import processing.core.PApplet;

public class Chess extends PApplet {

	final int CANVAS_WIDTH = 400;
	final int CANVAS_HEIGHT = 400;
	final int LENGTH = 50;
	int ROWS = 8;
	int COLUMNS = 8;
	Piece[] pawn = new Pawn[16];
	Piece[] rook = new Rook[4];
	Piece[] knight = new Knight[4];
	Piece[] bishop = new Bishop[4];
	Piece[] queen = new Queen[2];
	Piece[] king = new King[2];

	public static void main(String[] args) {
		PApplet.main("main.Chess");
	}

	public void settings() {
		size(400, 400);
	}

	public void setup() {
		// creating the pieces
		//// pawn
		for (int i = 0; i < pawn.length; i++) {
			if (i < 8) {
				pawn[i] = new Pawn(i, 1, LENGTH, false, this);
			} else {
				pawn[i] = new Pawn(i - 8, 6, LENGTH, true, this);
			}
		}

		//// rook
		rook[0] = new Rook(0, 0, LENGTH, false, this);
		rook[1] = new Rook(7, 0, LENGTH, false, this);
		rook[2] = new Rook(0, 7, LENGTH, true, this);
		rook[3] = new Rook(7, 7, LENGTH, true, this);

		//// knight
		knight[0] = new Knight(1, 0, LENGTH, false, this);
		knight[1] = new Knight(6, 0, LENGTH, false, this);
		knight[2] = new Knight(1, 7, LENGTH, true, this);
		knight[3] = new Knight(6, 7, LENGTH, true, this);

		//// bishop
		bishop[0] = new Bishop(2, 0, LENGTH, false, this);
		bishop[1] = new Bishop(5, 0, LENGTH, false, this);
		bishop[2] = new Bishop(2, 7, LENGTH, true, this);
		bishop[3] = new Bishop(5, 7, LENGTH, true, this);

		//// queen
		queen[0] = new Queen(3, 0, LENGTH, false, this);
		queen[1] = new Queen(3, 7, LENGTH, true, this);

		//// king
		king[0] = new King(4, 0, LENGTH, false, this);
		king[1] = new King(4, 7, LENGTH, true, this);
	}

	public void draw() {
		// creating the board
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				noStroke();
				if ((i + j + 1) % 2 == 0) {
					fill(200);
					rect(LENGTH * i, LENGTH * j, LENGTH, LENGTH);
				} else {
					fill(100);
					rect(LENGTH * i, LENGTH * j, LENGTH, LENGTH);
				}
			}
		}

		// drawing the pieces
		//// pawn
		for (int i = 0; i < pawn.length; i++) {
			pawn[i].draw();
		}

		//// rook
		for (int i = 0; i < rook.length; i++) {
			rook[i].draw();
		}

		//// knight
		for (int i = 0; i < knight.length; i++) {
			knight[i].draw();
		}

		//// bishop
		for (int i = 0; i < bishop.length; i++) {
			bishop[i].draw();
		}

		//// queen
		for (int i = 0; i < queen.length; i++) {
			queen[i].draw();
		}

		//// king
		for (int i = 0; i < king.length; i++) {
			king[i].draw();
		}
	}

	public void mousePressed() {
		// putting the selected piece back down
		putDown(pawn);
		putDown(rook);
		putDown(knight);
		putDown(bishop);
		putDown(queen);
		putDown(king);

		// checking for the selected piece
		setMoving(pawn);
		setMoving(rook);
		setMoving(knight);
		setMoving(bishop);
		setMoving(queen);
		setMoving(king);

	}

	void setMoving(Piece[] piece) {
		for (int i = 0; i < piece.length; i++) {
			if (piece[i].checkMouseLocation(mouseX, mouseY)) {
				piece[i].moving = true;
			}
		}
	}

	void putDown(Piece[] piece) {
		//TODO actually change the position of the piece
		if (mouseButton == RIGHT) {
			for (int i = 0; i < piece.length; i++) {
				if (piece[i].moving) {
					piece[i].moving = false;
				}
			}
		}
	}
}
