package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class BossProjectile extends Object{
	int dmg;
	int yVelocity = 5;
	boolean spawned = false;
	boolean attack = false;
	Timer timer;
	TimerTask task = new Attack();
	
	public static BufferedImage fireball;
	
	class Attack extends TimerTask{
		public void run() {
			attack = true;
		}
	}
	
	BossProjectile(int x, int y, int width, int height, int dmg) {
		super(x, y, width, height);
		this.dmg = dmg;
		timer = new Timer();
		try {
			fireball = ImageIO.read(this.getClass().getResourceAsStream("fireball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void update() {
		if(attack) {
			y += yVelocity;
		
			if(Manager.checkSolidCollision(cBox) && spawned == true) {
			finishAttack();
			}
		
			spawned = true;
		}
		
		super.update();
	}
	
	void draw(Graphics g) {
		g.drawImage(fireball, x-4, y-6, width+8, height+12, null);
	}
	
	void finishAttack() {
		isAlive = false;
	}
	
	void attack() {
		timer.schedule(task, 1000);
	}
}
