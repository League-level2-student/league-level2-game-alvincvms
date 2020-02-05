package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import game.PlayerAttack.FinishAttack;

public class PlayerProjectile extends Object{
	
	int dmg;
	int xVelocity = 8;
	boolean spawned = false;
	Timer timer;
	TimerTask task = new FinishAttack();
	
	class FinishAttack extends TimerTask {
		public void run() {
			finishAttack();
		}
	}
	
	PlayerProjectile(int x, int y, int width, int height, int direction, int dmg){
		super(x, y, width, height);
		facing = direction;
		this.dmg = dmg;
		timer = new Timer();
	}
	
	void update() {
		if(facing == FACING_LEFT) {
			x -= xVelocity;
		}
		if(facing == FACING_RIGHT) {
			x += xVelocity;
		} 

		super.update();
		
		if(Manager.checkSolidCollision(cBox) && spawned == true) {
			finishAttack();
		}
		
		spawned = true;
		
	}
	
	void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
	
	void finishAttack() {
		isAlive = false;
	}
	
	void attack() {
		timer.schedule(task, 800);
	}
}
