package piece;

import processing.core.PApplet;

public class Pawn {
	int i;
	int j;
	int x;
	int y;
	int length;
//	String position;
	PApplet parent;

	public Pawn(int i, int j, int length, PApplet p) {
		this.i = i;
		this.j = j;
		this.length = length;
		x = i * length;
		y = j * length;
//		position = String.valueOf(Character.getNumericValue(j)).concat(String.valueOf(Character.getNumericValue(8-i)));
		parent = p;
	}

	public void show() {
		parent.fill(0, 255, 0);
		parent.rect((float) (length * i * 0.25), (float) (length * j * 0.25), (float) (length * 0.5),
				(float) (length * 0.5));
	}
}
