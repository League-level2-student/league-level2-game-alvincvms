package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Archer extends Monster{
	
	public static BufferedImage archerL;
	public static BufferedImage archerR;
	public static BufferedImage atkL;
	public static BufferedImage atkR;
	public static BufferedImage hurtL;
	public static BufferedImage hurtR;
	public static BufferedImage atkHL;
	public static BufferedImage atkHR;

	HealthBar hBar = new HealthBar(x - 4, y - 10, 25, 6, 1);
	int AR = 160;
	int PHT;
	
	public int attackCooldown;
	int ACDTimer = 0;
	int drawTimer = 0;
	
	Archer(int x, int y, int width, int height){
		super(x, y, width, height, 270, 0);
		xV = 0;
		yV = 0;
		if(Game.difficulty == Game.EASY) {
			maxHP = 31;
			health = 31;
			dmg = 12;
			PHT = 52;
			attackCooldown = 120;
		}
		if(Game.difficulty == Game.MEDIUM) {
			maxHP = 48;
			health = 48;
			dmg = 20;
			PHT = 36;
			attackCooldown = 92;
		}
		if(Game.difficulty == Game.HARD) {
			maxHP = 55;
			health = 55;
			dmg = 25;
			PHT = 28;
			attackCooldown = 75;
		}
		if(Game.difficulty == Game.EXPERT) {
			maxHP = 65;
			health = 65;
			dmg = 29;
			PHT = 25;
			attackCooldown = 60;
		}
		if(Game.difficulty == Game.NIGHTMARE) {
			maxHP = 72;
			health = 72;
			dmg = 49;
			PHT = 21;
			attackCooldown = 48;
		}
		try {
			archerL = ImageIO.read(this.getClass().getResourceAsStream("ArcherL.png"));
			archerR = ImageIO.read(this.getClass().getResourceAsStream("ArcherR.png"));
			atkL = ImageIO.read(this.getClass().getResourceAsStream("ArcherATKL.png"));
			atkR = ImageIO.read(this.getClass().getResourceAsStream("ArcherATKR.png"));
			hurtL = ImageIO.read(this.getClass().getResourceAsStream("AHL.png"));
			hurtR = ImageIO.read(this.getClass().getResourceAsStream("AHR.png"));
			atkHL = ImageIO.read(this.getClass().getResourceAsStream("AAHL.png"));
			atkHR = ImageIO.read(this.getClass().getResourceAsStream("AAHR.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void update() {
		
		if(Monster.playerInSight(vBox)) {
			if(Manager.p.x < x) {
				facing = Object.FACING_LEFT;
			}
			if(Manager.p.x > x) {
				facing = Object.FACING_RIGHT;
			}
			if(Manager.p.x + AR <= x) {
				if(Game.difficulty == Game.NIGHTMARE) {
					xV = -3;
				}
				else {
					xV = -2;
				}
			}
			else if(Manager.p.x - AR >= x) {
				if(Game.difficulty == Game.NIGHTMARE) {
					xV = 3;
				}
				else {
					xV = 2;
				}
			}
			else {
				xV = 0;
				attack();
			}
		}
		else {
			xV = 0;
		}
		
		move();
		gravity();
		
		hBar = new HealthBar(x - 4, y - 10, 25, 6, (double)health/maxHP);
		super.update();
		
		if(hurtTimer > 0) {
			hurtTimer--;
		}
		if(ACDTimer > 0) {
			ACDTimer--;
		}
		if(drawTimer > 0) {
			drawTimer--;
		}
		
	}
	
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
	
	void attack() {
		if(hurtTimer > 0) {
			AM = 0.7;
		}
		else {
			AM = 1;
		}
		
		if(ACDTimer <= 0) {
			if(facing == FACING_LEFT) {
				ArcherProjectile p = new ArcherProjectile(x, y + 10, 21, 8, FACING_LEFT, (int) (dmg * AM), PHT);
				Manager.archerProjectiles.add(p);
				p.attack();
			}
			if(facing == FACING_RIGHT) {
				ArcherProjectile p = new ArcherProjectile(x, y + 10, 21, 8, FACING_RIGHT, (int) (dmg * AM), PHT);
				Manager.archerProjectiles.add(p);
				p.attack();
			}
			ACDTimer = attackCooldown;
			drawTimer = 36;
		}
	}
	
	void draw(Graphics g) {
		if(hurtTimer > 0) {
			if(drawTimer > 0) {
				if(facing == FACING_LEFT) {
					g.drawImage(atkHL, x-10, y-7, width+28, height+9, null);
				}
				if(facing == FACING_RIGHT) {
					g.drawImage(atkHR, x-10, y-7, width+28, height+9, null);
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
			if(drawTimer > 0) {
				if(facing == FACING_LEFT) {
					g.drawImage(atkL, x-10, y-7, width+28, height+9, null);
				}
				if(facing == FACING_RIGHT) {
					g.drawImage(atkR, x-10, y-7, width+28, height+9, null);
				}
			}
			else {
				if(facing == FACING_LEFT) {
					g.drawImage(archerL, x-10, y-7, width+28, height+9, null);
				}
				if(facing == FACING_RIGHT) {
					g.drawImage(archerR, x-10, y-7, width+28, height+9, null);
				}
			}
		}
		hBar.draw(g);
	}
	
}
