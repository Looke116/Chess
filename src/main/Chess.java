package main;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import processing.core.PApplet;

public class Chess extends PApplet {

	private static final int LENGTH = 50;
	int[] current = new int[2];
	boolean hasPiece = false;
	List<List<Piece>> pieces = Lists.newArrayList();
	ArrayList<Piece> pawn = new ArrayList<Piece>();
	ArrayList<Piece> rook = new ArrayList<Piece>();
	ArrayList<Piece> knight = new ArrayList<Piece>();
	ArrayList<Piece> bishop = new ArrayList<Piece>();
	ArrayList<Piece> queen = new ArrayList<Piece>();
	ArrayList<Piece> king = new ArrayList<Piece>();
	Pawn typePawn = new Pawn();
	Rook typeRook = new Rook();
	Knight typeKnight = new Knight();
	Bishop typeBishop = new Bishop();
	Queen typeQueen = new Queen();
	King typeKing = new King();
	Cell[][] cell = new Cell[8][8];

	public static void main(String[] args) {
		PApplet.main("main.Chess");
	}

	public void settings() {
		size(400, 400);
	}

	public void setup() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cell[i][j] = new Cell(i, j, false, this);
			}
		}

		// creating the pieces and setting the cells
		//// pawn
		for (int i = 0; i < 16; i++) {
			if (i < 8) {
				pawn.add(new Pawn(i, 1, LENGTH, false, this));
				cell[i][1].setPiece(true);
			} else {
				pawn.add(new Pawn(i - 8, 6, LENGTH, true, this));
				cell[i - 8][6].setPiece(true);
			}
		}

		//// rook
		rook.add(new Rook(0, 0, LENGTH, false, this));
		cell[0][0].setPiece(true);
		rook.add(new Rook(7, 0, LENGTH, false, this));
		cell[7][0].setPiece(true);
		rook.add(new Rook(0, 7, LENGTH, true, this));
		cell[0][7].setPiece(true);
		rook.add(new Rook(7, 7, LENGTH, true, this));
		cell[7][7].setPiece(true);

		//// knight
		knight.add(new Knight(1, 0, LENGTH, false, this));
		cell[1][0].setPiece(true);
		knight.add(new Knight(6, 0, LENGTH, false, this));
		cell[6][0].setPiece(true);
		knight.add(new Knight(1, 7, LENGTH, true, this));
		cell[1][7].setPiece(true);
		knight.add(new Knight(6, 7, LENGTH, true, this));
		cell[6][7].setPiece(true);

		//// bishop
		bishop.add(new Bishop(2, 0, LENGTH, false, this));
		cell[2][0].setPiece(true);
		bishop.add(new Bishop(5, 0, LENGTH, false, this));
		cell[5][0].setPiece(true);
		bishop.add(new Bishop(2, 7, LENGTH, true, this));
		cell[2][7].setPiece(true);
		bishop.add(new Bishop(5, 7, LENGTH, true, this));
		cell[5][7].setPiece(true);

		//// queen
		queen.add(new Queen(3, 0, LENGTH, false, this));
		cell[3][0].setPiece(true);
		queen.add(new Queen(3, 7, LENGTH, true, this));
		cell[3][7].setPiece(true);

		//// king
		king.add(new King(4, 0, LENGTH, false, this));
		cell[4][0].setPiece(true);
		king.add(new King(4, 7, LENGTH, true, this));
		cell[4][7].setPiece(true);

		// putting all the pieces in a single list to find all of them at the same time
		pieces.add(pawn);
		pieces.add(rook);
		pieces.add(knight);
		pieces.add(bishop);
		pieces.add(queen);
		pieces.add(king);

	}

	public void draw() {
		// creating the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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
		pawn.forEach(piece -> piece.draw()); // TODO something's wrong here
		rook.forEach(piece -> piece.draw());
		knight.forEach(piece -> piece.draw());
		bishop.forEach(piece -> piece.draw());
		queen.forEach(piece -> piece.draw());
		king.forEach(piece -> piece.draw());
	}

	public void mousePressed() {
		if (hasPiece) {
			// putting the selected piece back down
			putDown(pawn, pieces);
			putDown(rook, pieces);
			putDown(knight, pieces);
			putDown(bishop, pieces);
			putDown(queen, pieces);
			putDown(king, pieces);
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

	void setMoving(ArrayList<Piece> piece) {
		for (int i = 0; i < piece.size(); i++) {
			if (piece.get(i).checkMouseLocation(mouseX, mouseY)) {
				piece.get(i).moving = true;
				current[0] = mouseX / LENGTH;
				current[1] = mouseY / LENGTH;
			}
		}
	}

	void putDown(ArrayList<Piece> piece, List<List<Piece>> pieces) {

		if (mouseButton == RIGHT) {
			for (int i = 0; i < piece.size(); i++) {
				if (piece.get(i).moving) {
					piece.get(i).moving = false;
				}
			}
		} else if (mouseButton == LEFT) {
			for (int i = 0; i < piece.size(); i++) {
				if (piece.get(i).moving) {
					piece.get(i).moving = false;
					if (!cell[mouseX / LENGTH][mouseY / LENGTH].occupied) {
						if (checkMove(mouseX / LENGTH, mouseY / LENGTH, piece.get(i))) {
							piece.get(i).setIndex(mouseX / LENGTH, mouseY / LENGTH);
							cell[current[0]][current[1]].setPiece(false);
							cell[mouseX / LENGTH][mouseY / LENGTH].setPiece(true);
						}
					} else {
						boolean isWhite = true;
						for (int j = 0; j < pieces.size(); j++) {
							for (int k = 0; k < pieces.get(j).size(); k++) {
								if (piece.get(j).checkMouseLocation(mouseX, mouseY)) {
									isWhite = piece.get(j).white;
								}
							}
						}

						if (checkMove(mouseX / LENGTH, mouseY / LENGTH, piece.get(i), isWhite)) {
							for (int j = 0; j < piece.size(); j++) {
								if (piece.get(j).checkMouseLocation(mouseX, mouseY)) {
									piece.get(j).taken = true;

								}
							}
							piece.get(i).setIndex(mouseX / LENGTH, mouseY / LENGTH);
							cell[current[0]][current[1]].setPiece(false);
							cell[mouseX / LENGTH][mouseY / LENGTH].setPiece(true);
						}
					}
				}
			}
		}
	}

	boolean checkMove(int i, int j, Piece piece) {
		if (piece.getClass() == typePawn.getClass()) {
			return ((Pawn) piece).checkMove(i, j);
		} else if (piece.getClass() == typeRook.getClass()) {
			return ((Rook) piece).checkMove(i, j);
		} else if (piece.getClass() == typeKnight.getClass()) {
			return ((Knight) piece).checkMove(i, j);
		} else if (piece.getClass() == typeBishop.getClass()) {
			return ((Bishop) piece).checkMove(i, j);
		} else if (piece.getClass() == typeQueen.getClass()) {
			return ((Queen) piece).checkMove(i, j);
		} else if (piece.getClass() == typeKing.getClass()) {
			return ((King) piece).checkMove(i, j);
		}
		return false;
	}

	boolean checkMove(int i, int j, Piece piece, boolean isWhite) {
		if (piece.getClass() == typePawn.getClass()) {
			return ((Pawn) piece).checkAttack(i, j, isWhite);
		} else if (piece.getClass() == typeRook.getClass()) {
			return ((Rook) piece).checkAttack(i, j, isWhite);
		} else if (piece.getClass() == typeKnight.getClass()) {
//			return ((Knight) piece).checkAttack(i, j, isWhite);
//		} else if (piece.getClass() == typeBishop.getClass()) {
//			return ((Bishop) piece).checkAttack(i, j, isWhite);
//		} else if (piece.getClass() == typeQueen.getClass()) {
//			return ((Queen) piece).checkAttack(i, j, isWhite);
//		} else if (piece.getClass() == typeKing.getClass()) {
//			return ((King) piece).checkAttack(i, j, isWhite);
		}
		return false;
	}
}
