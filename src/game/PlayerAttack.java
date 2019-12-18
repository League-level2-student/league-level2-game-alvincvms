package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class PlayerAttack extends Object{

	Timer timer;
	TimerTask task = new FinishAttack();
	
	class FinishAttack extends TimerTask{
		public void run() {
			isAlive = false;
		}
	}
	
	public PlayerAttack(int x, int y, int width, int height, int direction) {
		super(x, y, width, height);
		facing = direction;
		timer = new Timer();
	}
	
	void update() {
		/*
		x = (facing == FACING_LEFT)? Player.playerX - 16 : Player.playerX + Player.playerWidth;
		y = Player.playerY + 3;
		*/
		
		if(facing == FACING_LEFT) {
			x = Player.playerX - 16;
			y = Player.playerY + 3;
		}
		if(facing == FACING_RIGHT) {
			x = Player.playerX + Player.playerWidth;
			y = Player.playerY + 3;
		} 
		
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
	
	void attack() {
		timer.schedule(task, 160);
	}
}
