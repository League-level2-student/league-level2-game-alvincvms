package game;

import java.awt.Color;
import java.awt.Graphics;

public class Platform extends Object{
	
	Platform(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x, y+1, width, height-1);
	}
}
