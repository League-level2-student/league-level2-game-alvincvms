package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Archer extends Monster{

	HealthBar hBar = new HealthBar(x - 4, y - 10, 25, 6, 1);
	int AR = 160;
	int PHT;
	
	public int attackCooldown;
	int ACDTimer = 0;
	
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
			maxHP = 75;
			health = 75;
			dmg = 25;
			PHT = 28;
			attackCooldown = 75;
		}
		if(Game.difficulty == Game.EXPERT) {
			maxHP = 95;
			health = 95;
			dmg = 29;
			PHT = 25;
			attackCooldown = 50;
		}
		if(Game.difficulty == Game.NIGHTMARE) {
			maxHP = 120;
			health = 120;
			dmg = 65;
			PHT = 21;
			attackCooldown = 36;
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
		if(ACDTimer <= 0) {
			if(facing == FACING_LEFT) {
				ArcherProjectile p = new ArcherProjectile(x, y + 10, 21, 8, FACING_LEFT, dmg, PHT);
				Manager.archerProjectiles.add(p);
				p.attack();
			}
			if(facing == FACING_RIGHT) {
				ArcherProjectile p = new ArcherProjectile(x, y + 10, 21, 8, FACING_RIGHT, dmg, PHT);
				Manager.archerProjectiles.add(p);
				p.attack();
			}
			ACDTimer = attackCooldown;
		}
	}
	
	void draw(Graphics g) {
		if(hurtTimer > 0) {
			g.setColor(new Color(204, 153, 255));
		}
		else {
			g.setColor(new Color(102, 0, 204));
		}
		g.fillRect(x, y, width, height);
		hBar.draw(g);
	}
	
}
