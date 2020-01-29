package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class The_Inquisitor extends Monster{
	int attackMode = 0;
	boolean attackFinished = true;
	int cdTimer = 0;
	int atkCounter = 0;
	
	HealthBar hBar = new HealthBar(150, 100, 450, 20, 1);
	
	The_Inquisitor(int x, int y, int width, int height){
		super(x, y, width, height, 240);
		xV = 0;
		yV = 0;
		hBar.outline = true;
		if(Game.difficulty == Game.EASY) {
			maxHP = 500;
			health = 500;
			dmg = 10;
		}
		if(Game.difficulty == Game.MEDIUM) {
			maxHP = 680;
			health = 680;
			dmg = 12;
		}
		if(Game.difficulty == Game.HARD) {
			maxHP = 820;
			health = 820;
			dmg = 15;
		}
		if(Game.difficulty == Game.EXPERT) {
			maxHP = 950;
			health = 950;
			dmg = 17;
		}
		if(Game.difficulty == Game.NIGHTMARE) {
			maxHP = 1200;
			health = 1200;
			dmg = 20;
		}
	}
	
	void update() {
		
		if(cBox.intersects(Manager.p.cBox) && Manager.p.hurtTimer <= 0) {
			if(Game.difficulty == Game.EASY) {
				Manager.p.hurtTimer = 56;
			}
			if(Game.difficulty == Game.MEDIUM) {
				Manager.p.hurtTimer = 48;
			}
			if(Game.difficulty == Game.HARD) {
				Manager.p.hurtTimer = 32;
			}
			if(Game.difficulty == Game.EXPERT) {
				Manager.p.hurtTimer = 27;
			}
			if(Game.difficulty == Game.NIGHTMARE) {
				Manager.p.hurtTimer = 22;
			}
			Manager.p.health -= dmg;
			Manager.p.knockback(11, facing);
		}
		
		if(Monster.playerInSight(vBox)) {
			vLength = 500;
			if(Manager.p.x < x) {
				facing = Object.FACING_LEFT;
			}
			if(Manager.p.x + Manager.p.width > x + width) {
				facing = Object.FACING_RIGHT;
			}
			if(cdTimer == 0) {
				if(!attackFinished) {
					if(attackMode == 1) {
						if(facing == FACING_LEFT) {
							ArcherProjectile p = new ArcherProjectile(x, y + 10, 21, 12, FACING_LEFT, (int) (dmg * 1.5), 12);
							Manager.archerProjectiles.add(p);
							p.attack();
						}
						if(facing == FACING_RIGHT) {
							ArcherProjectile p = new ArcherProjectile(x + width - 21, y + 10, 21, 12, FACING_RIGHT, (int) (dmg * 1.5), 12);
							Manager.archerProjectiles.add(p);
							p.attack();
						}
						cdTimer = 8;
						atkCounter--;
						if(atkCounter == 0) {
							attackFinished = true;
							cdTimer = 65;
						}
					}
					if(attackMode == 2) {
						if(Manager.)
						m2Move();
					}
				}
				else {
					if(attackMode == 0 || attackMode == 3) {
						changeMode(5);
					}
					else if(attackMode == 1) {
						changeMode(3);
					}
					else {
						changeMode(0);
					}
				}
			}
		}
		
		
		gravity();
		
		hBar = new HealthBar(150, 100, 450, 20, (double)health/maxHP);
		hBar.outline = true;
		super.update();
		
		if(hurtTimer > 0) {
			hurtTimer--;
		}
		
		if(cdTimer > 0) {
			cdTimer--;
		}
		
		System.out.println(attackMode);
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
	
	void m2Move() {
		int newX = x;
		
		Rectangle newCBox = new Rectangle(newX, y, width, height);
		
		if(Manager.checkSolidCollision(newCBox) == false) {
			x = newX;	
		}
		else {
			if(xV < 0) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX += 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
				cdTimer = 25;
			}
			
			if(xV > 0) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX -= 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
				cdTimer = 25;
			}
		}
	}
	
	void changeMode(int atk) {
		if(attackMode < 3) {
			attackMode++;
		}
		else {
			attackMode = 1;
		}
		attackFinished = false;
		atkCounter = atk;
	}
	
	void draw(Graphics g) {
		if(hurtTimer > 0) {
			g.setColor(Color.pink);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
		hBar.draw(g);
		
		g.setFont(new Font("TimesRoman", Font.ITALIC, 24));
		g.setColor(Color.black);
		g.drawString("The Inquisitor", 300, 85);
		
		g.setFont(new Font("Arial", Font.PLAIN, 22));
		g.drawString(Integer.toString(health), 300, 117);
		g.drawString("/", 367, 117);
		g.drawString(Integer.toString(maxHP), 400, 117);
	}
}
