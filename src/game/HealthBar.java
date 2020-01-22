package game;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends Object{

	double hpPercentage;
	boolean outline = false;
	
	HealthBar(int x, int y, int width, int height, double hpPercentage){
		super(x, y, width, height);
		this.hpPercentage = hpPercentage;
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		if(outline) {
			g.setColor(Color.black);
			g.fillRect(x - 3, y - 3, width + 6, height + 6);
		}
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		g.fillRect(x, y, (int)Math.round(hpPercentage * width), height);
	}
}
