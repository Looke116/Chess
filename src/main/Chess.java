package main;

import processing.core.PApplet;

public class Chess extends PApplet {

	static int CANVAS_WIDTH = 400;
	static int CANVAS_HEIGHT = 400;
	static int LENGTH = 50;
	int ROWS = 8;
	int COLUMNS = 8;
	Piece[] pawn = new Pawn[16];

	public static void main(String[] args) {
		PApplet.main("main.Chess");
	}

	public void settings() {
		size(400, 400);
	}

	public void setup() {
		for (int i = 0; i < 16; i++) {
			if (i < 8) {
				pawn[i] = new Pawn(i, 1, LENGTH, false, this);
			} else {
				pawn[i] = new Pawn(i - 8, 6, LENGTH, true, this);
			}
		}
	}

	public void draw() {
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

		for (int i = 0; i < 16; i++) {
			((main.Pawn) pawn[i]).draw();
		}
	}

	public void mousePressed() {
		for (int i = 0; i < pawn.length; i++) {
			if (pawn[i].checkMouseLocation(mouseX, mouseY)) {
				pawn[i].moving = true;
			}
		}
	}
}
