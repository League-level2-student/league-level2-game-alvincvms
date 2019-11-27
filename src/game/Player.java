package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Object{
	
	int xVelocity = 6;
	int yVelocity = 0;
	int jumpPower = 12;
	
	public int yLimit = PixelLegend.HEIGHT;
	public int yLimitU = -50;
	public int xLimitR = PixelLegend.WIDTH;
	public int xLimitL = 0;
	
	int canJump = 0;
	boolean canFall = true;
	boolean isFalling = false;
	boolean escPlatform = false;
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void jump() {
		if (canJump > 0) {
			yVelocity = -jumpPower;
			canJump -= 1;
		}
	}
	
	public void fall() {
		if(canFall == true) {
			yVelocity += 15;
			if(isFalling == false) {
				escPlatform = true;
			}
			else {
				escPlatform = false;
			}
			canFall = false;
		}
		
		if(yVelocity > 25) {
			yVelocity = 25;
		}
	}
	
	void update() {
		if(left){
			x -= xVelocity;
		}
		if(right){
			x += xVelocity;
		}
		
		if(x >= xLimitR - width) {
			x = xLimitR - width;
		}
		if(x <= xLimitL) {
			x = xLimitL;
		}
		
		if(yVelocity < 20) {
			yVelocity += gravity;
		}
		y += yVelocity;
		
		if(y >= yLimit - height) {
			y = yLimit - height;
			yVelocity = 0;
			canJump = 2;
			canFall = true;
			isFalling = false;
		}
		else {
			isFalling = true;
		}
		
		if(y <= yLimitU) {
			y = yLimitU;
			yVelocity = 0;
		}
		
		if(y >= PixelLegend.HEIGHT - height) {
			escPlatform = false;
		}
		
		if(left == true && right == false) {
			facing = FACING_LEFT;
		}
		else if(right == true && left == false) {
			facing = FACING_RIGHT;
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
	
	public void setYLimitU(int limit) {
		yLimitU = limit;
	}
	
	public void setXLimitL(int limit) {
		xLimitL = limit;
	}
	public void setXLimitR(int limit) {
		xLimitR = limit;
	}
}
