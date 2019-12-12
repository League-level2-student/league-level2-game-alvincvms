package game;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerAttack extends Object{

	int direction;
	
	public PlayerAttack(int x, int y, int width, int height, int direction) {
		super(x, y, width, height);
		this.direction = direction;
	}
	
	void update(){
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
}
