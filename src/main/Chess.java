package main;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import processing.core.PApplet;

public class Chess extends PApplet {

	private static final int LENGTH = 100;
	int[] current = new int[2];
	boolean hasPiece = false;
	String turn = "WHITE";
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
		size(800, 800);
	}

	public void setup() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j + 1) % 2 == 0) {
					cell[i][j] = new Cell(i, j, LENGTH, false, true, this);
				} else {
					cell[i][j] = new Cell(i, j, LENGTH, false, false, this);
				}
			}
		}

		// creating the pieces and setting the cells
		//// pawn
		for (int i = 0; i < 16; i++) {
			if (i < 8) {
				pawn.add(new Pawn(i, 1, LENGTH, false, this));
				cell[i][1].setOccupied(true);
			} else {
				pawn.add(new Pawn(i - 8, 6, LENGTH, true, this));
				cell[i - 8][6].setOccupied(true);
			}
		}

		//// rook
		rook.add(new Rook(0, 0, LENGTH, false, this));
		cell[0][0].setOccupied(true);
		rook.add(new Rook(7, 0, LENGTH, false, this));
		cell[7][0].setOccupied(true);
		rook.add(new Rook(0, 7, LENGTH, true, this));
		cell[0][7].setOccupied(true);
		rook.add(new Rook(7, 7, LENGTH, true, this));
		cell[7][7].setOccupied(true);

		//// knight
		knight.add(new Knight(1, 0, LENGTH, false, this));
		cell[1][0].setOccupied(true);
		knight.add(new Knight(6, 0, LENGTH, false, this));
		cell[6][0].setOccupied(true);
		knight.add(new Knight(1, 7, LENGTH, true, this));
		cell[1][7].setOccupied(true);
		knight.add(new Knight(6, 7, LENGTH, true, this));
		cell[6][7].setOccupied(true);

		//// bishop
		bishop.add(new Bishop(2, 0, LENGTH, false, this));
		cell[2][0].setOccupied(true);
		bishop.add(new Bishop(5, 0, LENGTH, false, this));
		cell[5][0].setOccupied(true);
		bishop.add(new Bishop(2, 7, LENGTH, true, this));
		cell[2][7].setOccupied(true);
		bishop.add(new Bishop(5, 7, LENGTH, true, this));
		cell[5][7].setOccupied(true);

		//// queen
		queen.add(new Queen(3, 0, LENGTH, false, this));
		cell[3][0].setOccupied(true);
		queen.add(new Queen(3, 7, LENGTH, true, this));
		cell[3][7].setOccupied(true);

		//// king
		king.add(new King(4, 0, LENGTH, false, this));
		cell[4][0].setOccupied(true);
		king.add(new King(4, 7, LENGTH, true, this));
		cell[4][7].setOccupied(true);

		// putting all the pieces in a single list to find all of them at the same time
		pieces.add(pawn);
		pieces.add(rook);
		pieces.add(knight);
		pieces.add(bishop);
		pieces.add(queen);
		pieces.add(king);

	}

	public void draw() {
		// drawing the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cell[i][j].draw();
			}
		}

		// drawing the pieces
		pawn.forEach(piece -> piece.draw());
		rook.forEach(piece -> piece.draw());
		knight.forEach(piece -> piece.draw());
		bishop.forEach(piece -> piece.draw());
		queen.forEach(piece -> piece.draw());
		king.forEach(piece -> piece.draw());
	}

	public void mousePressed() {
		if (hasPiece) {
			// putting the selected piece back down
			putDown(pawn);
			putDown(rook);
			putDown(knight);
			putDown(bishop);
			putDown(queen);
			putDown(king);
			hasPiece = false;
		} else {
			// checking for the selected piece
			pickUp(pawn);
			pickUp(rook);
			pickUp(knight);
			pickUp(bishop);
			pickUp(queen);
			pickUp(king);
			hasPiece = true;
		}
	}

	void pickUp(ArrayList<Piece> piece) {
		for (int i = 0; i < piece.size(); i++) {
			if ((piece.get(i).checkMouseLocation(mouseX, mouseY)) && (piece.get(i).white == checkTurn())) {
				piece.get(i).picked = true;
				current[0] = mouseX / LENGTH;
				current[1] = mouseY / LENGTH;
				collorCells(piece.get(i));
			}
		}
	}

	void putDown(ArrayList<Piece> piece) {

//		checkWhite();  //TODO replace the manual check with the new function
		restoreCells();

		if (mouseButton == RIGHT) {
			for (int i = 0; i < piece.size(); i++) {
				if (piece.get(i).picked) {
					piece.get(i).picked = false;
				}
			}
		} else if (mouseButton == LEFT) {
			for (int i = 0; i < piece.size(); i++) {
				if (piece.get(i).picked) {
					piece.get(i).picked = false;
					if (!cell[mouseX / LENGTH][mouseY / LENGTH].occupied) {
						if (checkMove(mouseX / LENGTH, mouseY / LENGTH, piece.get(i))) {
							piece.get(i).setIndex(mouseX / LENGTH, mouseY / LENGTH);
							cell[current[0]][current[1]].setOccupied(false);
							cell[mouseX / LENGTH][mouseY / LENGTH].setOccupied(true);
							syncronizeList();
							if (turn == "WHITE") {
								turn = "BLACK";
							} else {
								turn = "WHITE";
							}
						}
					} else {
						Boolean isWhite = null;
						for (int j = 0; j < pieces.size(); j++) {
							for (int k = 0; k < pieces.get(j).size(); k++) {
								if (pieces.get(j).get(k).checkMouseLocation(mouseX, mouseY)) {
									isWhite = pieces.get(j).get(k).white;
									break;
								}
							}
							if (isWhite != null) {
								break;
							}
						}

						if (checkMove(mouseX / LENGTH, mouseY / LENGTH, piece.get(i), isWhite)) {
							for (int j = 0; j < pieces.size(); j++) {
								for (int k = 0; k < pieces.get(j).size(); k++) {
									if (pieces.get(j).get(k).checkMouseLocation(mouseX, mouseY)) {
										pieces.get(j).get(k).taken = true;
										cell[pieces.get(j).get(k).i][pieces.get(j).get(k).j].occupied = false;
									}
								}
							}

							piece.get(i).setIndex(mouseX / LENGTH, mouseY / LENGTH);
							cell[current[0]][current[1]].setOccupied(false);
							cell[mouseX / LENGTH][mouseY / LENGTH].setOccupied(true);
							syncronizeList();
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

	boolean checkMove(int i, int j, Piece piece, Boolean isWhite) {
		if (isWhite == null) {
			throw new NullPointerException();
		}
		if (piece.getClass() == typePawn.getClass()) {
			if (((Pawn) piece).checkAttack(i, j, isWhite)) {
				isWhite = null;
				return true;
			}
		} else if (piece.getClass() == typeRook.getClass()) {
			if (((Rook) piece).checkAttack(i, j, isWhite)) {
				isWhite = null;
				return true;
			}
		} else if (piece.getClass() == typeKnight.getClass()) {
			if (((Knight) piece).checkAttack(i, j, isWhite)) {
				isWhite = null;
				return true;
			}
		} else if (piece.getClass() == typeBishop.getClass()) {
			if (((Bishop) piece).checkAttack(i, j, isWhite)) {
				isWhite = null;
				return true;
			}
		} else if (piece.getClass() == typeQueen.getClass()) {
			if (((Queen) piece).checkAttack(i, j, isWhite)) {
				isWhite = null;
				return true;
			}
		} else if (piece.getClass() == typeKing.getClass()) {
			if (((King) piece).checkAttack(i, j, isWhite)) {
				isWhite = null;
				return true;
			}
		}
		return false;
	}

	void collorCells(Piece piece) {
//		if (piece.getClass() == typePawn.getClass()) {
//
//			Pawn pawn = (Pawn) piece;
//
//			if (pawn.canMoveTwo) {
//				if (pawn.white) {
//					if (!cell[pawn.i][pawn.j - 1].occupied && !cell[pawn.i][pawn.j - 2].occupied) {
//						cell[pawn.i][pawn.j - 1].setSelected(true);
//						cell[pawn.i][pawn.j - 2].setSelected(true);
//					} else if (cell[pawn.i][pawn.j - 2].occupied && !cell[pawn.i][pawn.j - 1].occupied) {
//						cell[pawn.i][pawn.j - 1].setSelected(true);
//					}
//				} else {
//					cell[pawn.i][pawn.j + 1].setSelected(true);
//					cell[pawn.i][pawn.j + 2].setSelected(true);
//				}
//			} else {
//				if (pawn.white) {
//					cell[pawn.i][pawn.j - 1].setSelected(true);
//				} else {
//					cell[pawn.i][pawn.j + 1].setSelected(true);
//				}
//			}
//
//			if (pawn.white) {
//				try {
//					if (pawn.checkAttack(pawn.i - 1, pawn.j - 1, checkWhite(pawn.i, pawn.j))) {
//						cell[pawn.i - 1][pawn.j - 1].setAttacked(true);
//					}
//				} catch (NullPointerException e) {
//					if (pawn.checkAttack(pawn.i + 1, pawn.j - 1, checkWhite(pawn.i, pawn.j))) {
//						cell[pawn.i + 1][pawn.j - 1].setAttacked(true);
//					}
//				}
//			} else {
//				try {
//					if (pawn.checkAttack(pawn.i - 1, pawn.j + 1, checkWhite(pawn.i, pawn.j))) {
//						cell[pawn.i - 1][pawn.j + 1].setAttacked(true);
//					}
//				} catch (NullPointerException e) {
//					if (pawn.checkAttack(pawn.i + 1, pawn.j + 1, checkWhite(pawn.i, pawn.j))) {
//						cell[pawn.i + 1][pawn.j + 1].setAttacked(true);
//					}
//				}
//			}
//
//		} else if (piece.getClass() == typeRook.getClass()) {
////			TODO
//			Rook rook = (Rook) piece;
//			for (int i = rook.i; i < 8; i++) {
//				if (cell[i][rook.j].occupied) {
//					if (checkWhite(i, rook.j) != rook.white) {
//						cell[i][rook.j].setAttacked(true);
//					}
//					break;
//				} else {
//					cell[i][rook.j].setSelected(true);
//				}
//
//			}
//			for (int i = rook.i; i >= 0; i--) {
//				if (cell[i][rook.j].occupied) {
//					if (checkWhite(i, rook.j) != rook.white) {
//						cell[i][rook.j].setAttacked(true);
//					}
//					break;
//				} else {
//					cell[i][rook.j].setSelected(true);
//
//				}
//			}
//
//			for (int j = rook.j; j < 8; j++) {
//				if(cell[rook.i][j].occupied){
//					if(checkWhite(rook.i, j) != rook.white) {
//						cell[rook.i][j].setAttacked(true);
//					}
//					break;
//				}else {
//					cell[rook.i][j].setSelected(true);
//				}
//			}
//
//		} else if (piece.getClass() == typeKnight.getClass()) {
////			 TODO
//
//		} else if (piece.getClass() == typeBishop.getClass()) {
////			 TODO
//
//		} else if (piece.getClass() == typeQueen.getClass()) {
////			 TODO
//
//		} else if (piece.getClass() == typeKing.getClass()) {
////			 TODO
//
//		}
	}

	boolean checkTurn() {
		if (turn == "WHITE") {
			return true;
		} else {
			return false;
		}
	}

	Boolean checkWhite(int i, int j) {
		for (int k = 0; k < pieces.size(); k++) {
			for (int l = 0; l < pieces.get(k).size(); l++) {
				if (pieces.get(k).get(l).i == i && pieces.get(k).get(l).j == j) {
					return pieces.get(k).get(l).white;
				}
			}
		}
		return null;
	}

	void restoreCells() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cell[i][j].setSelected(false);
				cell[i][j].setAttacked(false);
			}
		}
	}

	void syncronizeList() {
		pieces.clear();
		pieces.add(pawn);
		pieces.add(rook);
		pieces.add(knight);
		pieces.add(bishop);
		pieces.add(queen);
		pieces.add(king);
	}
}
