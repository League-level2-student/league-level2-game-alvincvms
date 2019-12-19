package game;

import java.awt.Graphics;

public class Warrior extends Monster{
	
	Warrior(int x, int y, int width, int length){
		super(x, y, width, length);
		xV = 5;
		yV = 0;
	}
	
	void update() {
		
		super.update();
	}
	
	void draw(Graphics g) {
		
	}
}
