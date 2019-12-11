package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Object{
	
	int xVelocity = 6;
	int yVelocity = 0;
	int jumpPower = 11;
	
	public int yLimit = PixelLegend.HEIGHT;
	public int yLimitU = -50;
	public int xLimitR = PixelLegend.WIDTH;
	public int xLimitL = 0;
	
	int canJump = 0;
	boolean canFall = true;
	
	boolean isFalling = false;
	boolean escPlatform = false;
	boolean platformCollision = false;
	
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
		newMove();
	} 
	
	void newMove() {
		int newX = x;
		int newY = y;
		
		isFalling = true;
		
		if(left && !right) {
			newX = x - xVelocity;
			facing = FACING_LEFT;
		}
		if(right && !left) {
			newX = x + xVelocity;
			facing = FACING_RIGHT;
		}
		
		Rectangle newCBox = new Rectangle(newX, y, width, height);
		
		//System.out.println(Manager.checkCollision(newCBox) == false);
		//System.out.println(x + "," + y);
		//System.out.println(newX + "," + newY);
		if(Manager.checkSolidCollision(newCBox) == false) {
			x = newX;	
		}
		else {
			if(left && !right) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX += 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
			}
			
			if(right && !left) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX -= 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
			}
		}
		
		if(yVelocity < 20) {
			yVelocity += gravity;
		}
		newY += yVelocity;
		
		newCBox = new Rectangle(x, newY, width, height);

		if(Manager.checkPlatformCollision(newCBox) && yVelocity >= 0 && y + height < Manager.collidingPlatform.y + 20) {
			Manager.handlePCollision(Manager.collidingPlatform);
		}
		else if(Manager.checkSolidCollision(newCBox) == false) {
				y = newY;
		}
		else if(yVelocity < 0) {
			while(Manager.checkSolidCollision(newCBox) == true) {
					newY += 1;
					newCBox = new Rectangle(x, newY, width, height);
			}
			yVelocity = 0;
			y = newY;
		}
		else {
			while(Manager.checkSolidCollision(newCBox) == true) {
				newY -= 1;
				newCBox = new Rectangle(x, newY, width, height);
			}
			y = newY;
			canJump = 2;
			yVelocity = 0;
			canFall = true;
			isFalling = false;
		}

		

		
		super.update();
	}
	
	/*void oldMove() {
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
		
		if(x >= xLimitR - width) {
			x = xLimitR - width;
		}
        if(x <= xLimitL) {
			x = xLimitL;
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
	}*/
	
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
