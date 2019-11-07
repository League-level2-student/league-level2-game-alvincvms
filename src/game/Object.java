package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Object {
	
	int x;
	int y;
	int width;
	int height;
	int health;
	boolean isAlive = true;
	Rectangle cBox = new Rectangle();
	
	public Object(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cBox.setBounds(x, y, width, height);
	}
	
	void update() {
		if(health <= 0) {
			isAlive = false;
		}
		cBox.setBounds(x, y, width, height);
	}
	
	void draw(Graphics g) {
		
	}
}
