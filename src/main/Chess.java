package main;

import piece.Pawn;
import processing.core.PApplet;

public class Chess extends PApplet {

	static int CANVAS_WIDTH = 400;
	static int CANVAS_HEIGHT = 400;
	static int LENGTH = 50;
	int ROWS = 8;
	int COLUMNS = 8;

	public static void main(String[] args) {
		PApplet.main("main.Chess");
	}

	public void settings() {
		size(400, 400);
	}

	public void setup() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				noStroke();
				if ((i + j + 1) % 2 == 0) {
					fill(250);
					rect(LENGTH * i, LENGTH * j, LENGTH, LENGTH);
				} else {
					fill(0);
					rect(LENGTH * i, LENGTH * j, LENGTH, LENGTH);
				}
			}
		}
	}

	public void draw() {
		Pawn p = new Pawn(1, 1, LENGTH, this);
		p.show();
	}
}
