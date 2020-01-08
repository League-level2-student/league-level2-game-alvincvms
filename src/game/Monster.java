package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends Object{
	int xV;
	int yV;
	int kXV;
	int kDir;
	int dmg;
	int vLength;
	int hurtTimer = 0;
	Rectangle vBox;
	
	Monster(int x, int y, int width, int height, int viewLength){
		super(x, y, width, height);
		vLength = viewLength;
		vBox = new Rectangle(x - vLength, y, x + width + vLength, height);
	}
	
	public void knockback(int force, int dir) {
		kDir = dir;
		if(dir == FACING_LEFT) {
			kXV = -force;
		}
		if(dir == FACING_RIGHT) {
			kXV = force;
		}
	}
	
	void update() {
		vBox.setBounds(x - vLength, y, x + width + vLength, height);
		super.update();
	}
	
	void draw(Graphics g) {
		
	}
	
	static boolean playerInSight(Rectangle vBox) {
		if(vBox.intersects(Manager.p.cBox)) {
			return true;
		}
		else {
			return false;
		}
	}
}
