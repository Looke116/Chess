package main;

import processing.core.PApplet;

public class Chess extends PApplet {

	final int CANVAS_WIDTH = 400;
	final int CANVAS_HEIGHT = 400;
	final int LENGTH = 50;
	final int ROWS = 8;
	final int COLUMNS = 8;
	boolean hasPiece = false;
	Piece[] pawn = new Pawn[16];
	Piece[] rook = new Rook[4];
	Piece[] knight = new Knight[4];
	Piece[] bishop = new Bishop[4];
	Piece[] queen = new Queen[2];
	Piece[] king = new King[2];
	Pawn typePawn = new Pawn();
	Rook typeRook = new Rook();
	Knight typeKnight = new Knight();
	Bishop typeBishop = new Bishop();
	Queen typeQueen = new Queen();
	King typeKing = new King();

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
		if (hasPiece) {
			// putting the selected piece back down
			putDown(pawn, typePawn);
			putDown(rook, typeRook);
			putDown(knight, typeKnight);
			putDown(bishop, typeBishop);
			putDown(queen, typeQueen);
			putDown(king, typeKing);
			hasPiece = false;
		} else {
			// checking for the selected piece
			setMoving(pawn);
			setMoving(rook);
			setMoving(knight);
			setMoving(bishop);
			setMoving(queen);
			setMoving(king);
			hasPiece = true;
		}
	}

	void setMoving(Piece[] piece) {
		for (int i = 0; i < piece.length; i++) {
			if (piece[i].checkMouseLocation(mouseX, mouseY)) {
				piece[i].moving = true;
			}
		}
	}

	void putDown(Piece[] piece, Piece type) {

		if (mouseButton == RIGHT) {
			for (int i = 0; i < piece.length; i++) {
				if (piece[i].moving) {
					piece[i].moving = false;
				}
			}
		} else if (mouseButton == LEFT) {
			for (int i = 0; i < piece.length; i++) {
				if (piece[i].moving) {
					piece[i].moving = false;
					if (checkMove(mouseX / LENGTH, mouseY / LENGTH, type)) {
						piece[i].setIndex(mouseX / LENGTH, mouseY / LENGTH);
					}
				}
			}
		}
	}

	boolean checkMove(int i, int j, Piece piece) {
		if (piece == typePawn) {
			return ((Pawn) piece).checkValidMove(i, j);
		}else if(piece == typeRook) {
			return ((Rook) piece).checkValidMove(i, j);
		}
		return false;

	}
}
