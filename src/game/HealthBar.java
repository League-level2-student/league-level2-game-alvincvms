package game;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends Object{

	double hpPercentage;
	
	HealthBar(int x, int y, int width, int height, double hpPercentage){
		super(x, y, width, height);
		this.hpPercentage = hpPercentage;
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		g.fillRect(x, y, (int)Math.round(hpPercentage * width), height);
	}
}
