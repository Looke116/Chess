package main;

import processing.core.PApplet;

public class Cell {
	int i;
	int j;
	int length;
	boolean occupied;
	boolean white;
	boolean selected;
	boolean attacked;
	PApplet parent;
	public static final int WHITE = 200;
	public static final int BLACK = 100;
	public static final int[] SELECTED_WHITE = { 179, 200, 179 };
	public static final int[] SELECTED_BLACK = { 90, 100, 90 };
	public static final int[] ATTACKED_WHITE = { 200, 179, 179 };
	public static final int[] ATTACKED_BLACK = { 100, 90, 90 };

	Cell(int i, int j, int length, boolean occupied, boolean white, PApplet p) {
		this.i = i;
		this.j = j;
		this.length = length;
		this.occupied = occupied;
		this.white = white;
		parent = p;
	}

	void setPiece(boolean piece) {
		this.occupied = piece;
	}

	void setSelected(boolean selected) {
		this.selected = selected;
	}

	void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	void draw() {
		parent.noStroke();
		if (white) {
			if (selected) {
				parent.fill(SELECTED_WHITE[0],SELECTED_WHITE[1],SELECTED_WHITE[2]);
				parent.rect(length * i, length * j, length, length);
			} else if (attacked) {
				parent.fill(ATTACKED_WHITE[0],ATTACKED_WHITE[1],ATTACKED_WHITE[2]);
				parent.rect(length * i, length * j, length, length);
			} else {
				parent.fill(WHITE);
				parent.rect(length * i, length * j, length, length);
			}
		} else {
			if (selected) {
				parent.fill(SELECTED_BLACK[0],SELECTED_BLACK[1],SELECTED_BLACK[2]);
				parent.rect(length * i, length * j, length, length);
			} else if (attacked) {
				parent.fill(ATTACKED_BLACK[0],ATTACKED_BLACK[1],ATTACKED_BLACK[2]);
				parent.rect(length * i, length * j, length, length);
			} else {
				parent.fill(BLACK);
				parent.rect(length * i, length * j, length, length);
			}
		}
	}
}

/*
 * j-|
 * |
 * |
 * |
 * |
 * |
 * j+|______________
 * i- i+
 * 
 * 
 * [0][0],[0][1],[0][2],[0][3],[0][4],[0][5],[0][6],[0][7]
 * [1][0],[1][1],[1][2],[1][3],[1][4],[1][5],[1][6],[1][7]
 * [2][0],[2][1],[2][2],[2][3],[2][4],[2][5],[2][6],[2][7]
 * [3][0],[3][1],[3][2],[3][3],[3][4],[3][5],[3][6],[3][7]
 */
