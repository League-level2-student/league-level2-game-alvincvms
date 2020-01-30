package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class BossProjectile extends Object{
	int dmg;
	int yVelocity = 5;
	boolean spawned = false;
	boolean attack = false;
	Timer timer;
	TimerTask task = new Attack();
	
	class Attack extends TimerTask{
		public void run() {
			attack = true;
		}
	}
	
	BossProjectile(int x, int y, int width, int height, int dmg) {
		super(x, y, width, height);
		this.dmg = dmg;
		timer = new Timer();
	}
	
	void update() {
		if(attack) {
			y -= yVelocity;
		
			if(Manager.checkSolidCollision(cBox) && spawned == true) {
			finishAttack();
			}
		
			spawned = true;
		}
		
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, width, height);
	}
	
	void finishAttack() {
		isAlive = false;
	}
	
	void attack() {
		timer.schedule(task, 1000);
	}
}
