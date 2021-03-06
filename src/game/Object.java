 package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Object {
	
	int x;
	int y;
	int width;
	int height;
	int maxHP;
	int health;
	boolean isAlive = true;
	
	public int facing;
	public final static int FACING_LEFT = 0;
	public final static int FACING_RIGHT = 1;
	int gravity = 1;
	
	Rectangle cBox = new Rectangle();
	
	public Object(int x, int y, int width, int height){
		Random r = new Random();
		facing = r.nextInt(2);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cBox.setBounds(x, y, width, height);
	}
	
	void update() {
		cBox.setBounds(x, y, width, height);
	}
	
	void draw(Graphics g) {
		
	}
}
