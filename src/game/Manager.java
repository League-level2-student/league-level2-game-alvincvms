package game;

import java.awt.Graphics;

public class Manager {
	Player p;
	
	Manager(Player p) {
		this.p = p;
	}
	
	void update() {
		p.update();
	}
	
	void draw(Graphics g) {
		p.draw(g);
	}
}
