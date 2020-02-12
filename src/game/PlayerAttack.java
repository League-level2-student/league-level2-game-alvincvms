package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class PlayerAttack extends Object{

	int dmg;
	int atk;
	Timer timer;
	TimerTask task = new FinishAttack();
	
	public static BufferedImage Slash1;
	public static BufferedImage Slash2;
	Random r = new Random();
	
	class FinishAttack extends TimerTask{
		public void run() {
			isAlive = false;
		}
	}
	
	public PlayerAttack(int x, int y, int width, int height, int direction, int dmg) {
		super(x, y, width, height);
		facing = direction;
		timer = new Timer();
		this.dmg = dmg;
		try {
			Slash1 = ImageIO.read(this.getClass().getResourceAsStream("SwordSlash1.png"));
			Slash2 = ImageIO.read(this.getClass().getResourceAsStream("SwordSlash2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		atk = r.nextInt(2);
	}
	
	void update() {
		/*
		x = (facing == FACING_LEFT)? Player.playerX - 16 : Player.playerX + Player.playerWidth;
		y = Player.playerY + 3;
		*/
		
		if(facing == FACING_LEFT) {
			x = Player.playerX - 25;
			y = Player.playerY - 2;
		}
		if(facing == FACING_RIGHT) {
			x = Player.playerX + Player.playerWidth - 14;
			y = Player.playerY - 2;
		} 
		
		super.update();
	}
	
	void draw(Graphics g) {
		if(atk == 1) {
			
		} unfinished
		if(atk == 2) {
			
		}
	}
	
	void attack() {
		timer.schedule(task, 160);
	}
}
