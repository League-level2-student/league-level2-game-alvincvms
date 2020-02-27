package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import game.PlayerProjectile.FinishAttack;

public class ArcherProjectile extends Object{
	
	int dmg;
	int xVelocity = 6;
	boolean spawned = false;
	int PHT;
	
	public static BufferedImage arrowR;
	public static BufferedImage arrowL;
	
	Timer timer;
	TimerTask task = new FinishAttack();
	
	class FinishAttack extends TimerTask {
		public void run() {
			finishAttack();
		}
	}
	
	ArcherProjectile(int x, int y, int width, int height, int direction, int dmg, int PHT){
		super(x, y, width, height);
		facing = direction;
		this.dmg = dmg;
		this.PHT = PHT;
		timer = new Timer();
		try {
			arrowR = ImageIO.read(this.getClass().getResourceAsStream("arrowR.png"));
			arrowL = ImageIO.read(this.getClass().getResourceAsStream("arrowL.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(facing == FACING_LEFT) {
			g.drawImage(arrowL, x-15, y-8, width+30, height+16, null);
		}
		if(facing == FACING_RIGHT) {
			g.drawImage(arrowR, x-15, y-8, width+30, height+16, null);
		}
	}
	
	void finishAttack() {
		isAlive = false;
	}
	
	void attack() {
		Game.bow.play();
		timer.schedule(task, 5000);
	}
}
