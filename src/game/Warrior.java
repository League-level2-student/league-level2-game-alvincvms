package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Warrior extends Monster{
	
	//int alertCooldown = 0;
	
	Warrior(int x, int y, int width, int height){
		super(x, y, width, height, 180);
		xV = 0;
		yV = 0;
		dmg = 15;
		health = 100;
	}
	
	void update() {
		
		if(Monster.playerInSight(vBox)) {
			//alertCooldown = 60;
			if(Manager.p.x < x) {
				facing = Object.FACING_LEFT;
				xV = -4;
			}
			if(Manager.p.x > x) {
				facing = Object.FACING_RIGHT;
				xV = 4;
			}
		}
		else {
			xV = 0;
		}
		/*else {
			if(alertCooldown > 0) {
				alertCooldown--;
			}
			else {
				casualMove();
			}
		}*/
		
		if(cBox.intersects(Manager.p.cBox)) {
			Manager.p.knockback(12, facing);
		}
		
		move();
		
		gravity();
		
		super.update();
		if(hurtTimer > 0) {
			hurtTimer--;
		}
	}
	
	/*void casualMove() {
		int dir;
		int distance;
		Random r = new Random();
		dir = r.nextInt(2);
		distance = r.nextInt(400);
	}*/
	
	void move() {
		int newX = x;
		
		if(kXV < 0) {
			kXV += 1;
			if(kXV > 0) {
				kXV = 0;
				kDir = 3;
			}
		}
		if(kXV > 0) {
			kXV -= 1;
			if(kXV < 0) {
				kXV = 0;
				kDir = 3;
			}
		}
		
		newX += kXV;
		newX += xV;
		
		Rectangle newCBox = new Rectangle(newX, y, width, height);
		
		if(Manager.checkSolidCollision(newCBox) == false) {
			x = newX;	
		}
		else {
			if(xV < 0 && !(kXV > 0)|| kXV < 0) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX += 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
			}
			
			if(xV > 0 && !(kXV < 0)|| kXV > 0) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX -= 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
			}
		}
	}
	
	void gravity() {
		int newY = y;
		Rectangle newCBox;
		
		if(yV < 20) {
			yV += gravity;
		}
		newY += yV;
		newCBox = new Rectangle(x, newY, width, height);
		
		if(!Manager.checkSolidCollision(newCBox)) {
			y = newY;
		}
		else {
			while(Manager.checkSolidCollision(newCBox) == true) {
				newY -= 1;
				newCBox = new Rectangle(x, newY, width, height);
			}
			y = newY;
			yV = 0;
		}
	}
	
	void draw(Graphics g) {
		if(hurtTimer > 0) {
			g.setColor(Color.pink);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
	}
}
