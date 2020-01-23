package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class The_Inquisitor extends Monster{
	
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
		
		gravity();
		
		hBar = new HealthBar(150, 100, 450, 20, (double)health/maxHP);
		hBar.outline = true;
		super.update();
		
		if(hurtTimer > 0) {
			hurtTimer--;
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
		hBar.draw(g);
		g.setFont(new Font());
	}
}
