package game;

import java.awt.Graphics;

public class Archer extends Monster{

	HealthBar hBar = new HealthBar(x - 4, y - 10, 25, 6, 1);
	
	Archer(int x, int y, int width, int height){
		super(x, y, width, height, 280);
		xV = 0;
		yV = 0;
		if(Game.difficulty == Game.EASY) {
			maxHP = 31;
			health = 31;
		}
		if(Game.difficulty == Game.MEDIUM) {
			maxHP = 48;
			health = 48;
		}
		if(Game.difficulty == Game.HARD) {
			maxHP = 75;
			health = 75;
		}
		if(Game.difficulty == Game.EXPERT) {
			maxHP = 95;
			health = 95;
		}
		if(Game.difficulty == Game.NIGHTMARE) {
			maxHP = 136;
			health = 136;
		}
	}
	
	void update() {
		
		if(Monster.playerInSight(vBox)) {
			if(Manager.p.x < x) {
				facing = Object.FACING_LEFT;
				if(Game.difficulty == Game.NIGHTMARE) {
					xV = -3;
				}
				else {
					xV = -2;
				}
			}
			if(Manager.p.x > x) {
				facing = Object.FACING_RIGHT;
				if(Game.difficulty == Game.NIGHTMARE) {
					xV = 3;
				}
				else {
					xV = 2;
				}
			}
		}
	}
	
	void draw(Graphics g) {
		
	}
	
}
