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
	int EXvHeight;
	int hurtTimer = 0;
	Rectangle vBox;
	
	Monster(int x, int y, int width, int height, int viewLength, int EXviewHeight){
		super(x, y, width, height);
		vLength = viewLength;
		EXvHeight = EXviewHeight;
		vBox = new Rectangle(x - vLength, y - EXvHeight, x + width + vLength, height + EXvHeight);
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
		vBox.setBounds(x - vLength, y - EXvHeight, width + 2 * vLength, height + EXvHeight);
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
