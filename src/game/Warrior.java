package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Warrior extends Monster{
	
	public static  BufferedImage warriorL;
	public static  BufferedImage warriorR;
	public static  BufferedImage move1L;
	public static  BufferedImage move1R;
	public static  BufferedImage move2L;
	public static  BufferedImage move2R;
	public static  BufferedImage hurtL;
	public static  BufferedImage hurtR;
	public static  BufferedImage hurtW1L;
	public static  BufferedImage hurtW1R;
	public static  BufferedImage hurtW2L;
	public static  BufferedImage hurtW2R;
	
	int walkTimer = 0;
	int walkA = 0;
	//int alertCooldown = 0;
	HealthBar hBar = new HealthBar(x - 4, y - 10, 25, 6, 1);
	
	Warrior(int x, int y, int width, int height){
		super(x, y, width, height, 180, 0);
		xV = 0;
		yV = 0;
		if(Game.difficulty == Game.EASY) {
			maxHP = 72;
			health = 72;
		}
		if(Game.difficulty == Game.MEDIUM) {
			maxHP = 90;
			health = 90;
		}
		if(Game.difficulty == Game.HARD) {
			maxHP = 105;
			health = 105;
		}
		if(Game.difficulty == Game.EXPERT) {
			maxHP = 110;
			health = 110;
		}
		if(Game.difficulty == Game.NIGHTMARE) {
			maxHP = 108;
			health = 108;
		}
		try {
			warriorL = ImageIO.read(this.getClass().getResourceAsStream("WarriorL.png"));
			warriorR = ImageIO.read(this.getClass().getResourceAsStream("WarriorR.png"));
			move1L = ImageIO.read(this.getClass().getResourceAsStream("WarriorW1L.png"));
			move1R = ImageIO.read(this.getClass().getResourceAsStream("WarriorW1R.png"));
			move2L = ImageIO.read(this.getClass().getResourceAsStream("WarriorW2L.png"));
			move2R = ImageIO.read(this.getClass().getResourceAsStream("WarriorW2R.png"));
			hurtL = ImageIO.read(this.getClass().getResourceAsStream("WHL.png"));
			hurtR = ImageIO.read(this.getClass().getResourceAsStream("WHR.png"));
			hurtW1L = ImageIO.read(this.getClass().getResourceAsStream("WHW1L.png"));
			hurtW1R = ImageIO.read(this.getClass().getResourceAsStream("WHW1R.png"));
			hurtW2L = ImageIO.read(this.getClass().getResourceAsStream("WHW2L.png"));
			hurtW2R = ImageIO.read(this.getClass().getResourceAsStream("WHW2R.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void update() {
		
		if(Monster.playerInSight(vBox)) {
			//alertCooldown = 60;
			if(Manager.p.x < x) {
				facing = Object.FACING_LEFT;
				if(Game.difficulty == Game.NIGHTMARE) {
					xV = -5;
				}
				else if(Game.difficulty == Game.EXPERT){
					xV = -4;
				}
				else {
					xV = -3;
				}
			}
			if(Manager.p.x > x) {
				facing = Object.FACING_RIGHT;
				if(Game.difficulty == Game.NIGHTMARE) {
					xV = 5;
				}
				else if(Game.difficulty == Game.EXPERT){
					xV = 4;
				}
				else {
					xV = 3;
				}
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
		
		if(cBox.intersects(Manager.p.cBox) && Manager.p.hurtTimer <= 0) {
			if(hurtTimer > 0) {
				AM = 0.75;
			}
			else {
				AM = 1;
			}
			
			if(Game.difficulty == Game.EASY) {
				Manager.p.health -= (int) (12 * AM);
				Manager.p.knockback(12, facing);
				Manager.p.hurtTimer = 60;
			}
			if(Game.difficulty == Game.MEDIUM) {
				Manager.p.health -= (int) (16 * AM);
				Manager.p.knockback(12, facing);
				Manager.p.hurtTimer = 50;
			}
			if(Game.difficulty == Game.HARD) {
				Manager.p.health -= (int) (21 * AM);
				Manager.p.knockback(12, facing);
				Manager.p.hurtTimer = 36;
			}
			if(Game.difficulty == Game.EXPERT) {
				Manager.p.health -= (int) (32 * AM);
				Manager.p.knockback(12, facing);
				Manager.p.hurtTimer = 29;
			}
			if(Game.difficulty == Game.NIGHTMARE) {
				Manager.p.health -= (int) (42 * AM);
				Manager.p.knockback(12, facing);
				Manager.p.hurtTimer = 24;
			}
		}
		
		move();
		gravity();
		
		hBar = new HealthBar(x - 4, y - 10, 25, 6, (double)health/maxHP);
		super.update();
		
		if(hurtTimer > 0) {
			hurtTimer--;
		}
		
		if(xV != 0) {
			walkTimer--;
		}
		if(walkTimer < 1) {
			walkTimer = 10;
			if(walkA == 0) {
				walkA = 1;
			}
			else if(walkA == 1) {
				walkA = 0;
			}
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
			
			else if(xV > 0 && !(kXV < 0)|| kXV > 0) {
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
			if(xV < 0) {
				if(walkA == 0) {
					g.drawImage(hurtW1L, x-10, y-7, width+28, height+9, null);	
				}
				if(walkA == 1) {
					g.drawImage(hurtW2L, x-10, y-7, width+28, height+9, null);
				}
			}
			else if(xV > 0) {
				if(walkA == 0) {
					g.drawImage(hurtW1R, x-10, y-7, width+28, height+9, null);	
				}
				if(walkA == 1) {
					g.drawImage(hurtW2R, x-10, y-7, width+28, height+9, null);
				}
			}
			else {
				if(facing == FACING_LEFT) {
					g.drawImage(hurtL, x-10, y-7, width+28, height+9, null);
				}
				if(facing == FACING_RIGHT) {
					g.drawImage(hurtR, x-10, y-7, width+28, height+9, null);
				}
			}
		}
		else {
			if(xV < 0) {
				if(walkA == 0) {
					g.drawImage(move1L, x-10, y-7, width+28, height+9, null);	
				}
				if(walkA == 1) {
					g.drawImage(move2L, x-10, y-7, width+28, height+9, null);
				}
			}
			else if(xV > 0) {
				if(walkA == 0) {
					g.drawImage(move1R, x-10, y-7, width+28, height+9, null);	
				}
				if(walkA == 1) {
					g.drawImage(move2R, x-10, y-7, width+28, height+9, null);
				}
			}
			else {
				if(facing == FACING_LEFT) {
					g.drawImage(warriorL, x-10, y-7, width+28, height+9, null);
				}
				if(facing == FACING_RIGHT) {
					g.drawImage(warriorR, x-10, y-7, width+28, height+9, null);
				}
			}
		}
		hBar.draw(g);
	}
}
