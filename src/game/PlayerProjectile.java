package game;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerProjectile extends Object{
	int xVelocity = 9;
	
	PlayerProjectile(int x, int y, int width, int height, int direction){
		super(x, y, width, height);
		facing = direction;
	}
	
	void update() {
		if(facing == FACING_LEFT) {
			x -= xVelocity;
		}
		if(facing == FACING_RIGHT) {
			x += xVelocity;
		} 
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
}
