package game;

import java.awt.Color;
import java.awt.Graphics;

public class Flag extends Object{
	
	Flag(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, width, height);
	}
}
