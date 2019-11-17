package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Object{
	
	int xVelocity = 6;
	int yVelocity = 0;
	int jumpPower = 12;
	
	public int yLimit = PixelLegend.HEIGHT;
	public int xLimitR = PixelLegend.WIDTH;
	public int xLimitL = 0;
	
	int canJump = 0;
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void jump() {
		if (canJump > 0) {
			yVelocity = -jumpPower;
			canJump -= 1;
		}
	}
	
	void update() {
		if(left){
			x -= xVelocity;
		}
		if(right){
			x += xVelocity;
		}
		
		if(yVelocity < 20) {
			yVelocity += gravity;
		}
		y += yVelocity;
		
		if(y >= yLimit - height){
			y = yLimit - height;
			yVelocity = 0;
			canJump = 2;
		}
		if(x >= xLimitR - width) {
			x = xLimitR - width;
		}
		if(x <= xLimitL) {
			x = xLimitL;
		}
		
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
	
	public void setYLimit(int limit) {
		yLimit = limit;
	}
	
	public void setXLimitL(int limit) {
		xLimitL = limit;
	}
	public void setXLimitR(int limit) {
		xLimitR = limit;
	}
}
