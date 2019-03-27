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