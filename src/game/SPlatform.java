package game;

import java.awt.Color;
import java.awt.Graphics;

public class SPlatform extends Object{
	
	SPlatform(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	void update() {
		cBox.setBounds(x, y, width, height);
	}
	
	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}
	
}
