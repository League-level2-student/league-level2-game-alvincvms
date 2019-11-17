package game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Manager {
	Player p;
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<SPlatform> sPlatforms = new ArrayList<SPlatform>();
	
	Manager(Player p) {
		this.p = p;
	}
	
	void update() {
		p.update();
	}
	
	void draw(Graphics g) {
		p.draw(g);
	}
	
	boolean checkPlatformCollision() {
		
		for(SPlatform sPlatform : sPlatforms) {
			if(p.cBox.intersects(sPlatform.cBox)) {
				handleSCollision(sPlatform);
				return true;
			}
		}
		for(Platform platform : platforms) {
			if(p.cBox.intersects(platform.cBox)) {
				handlePCollision(platform);
				return true;
			}
		}
		
		p.setYLimit(PixelLegend.HEIGHT);
		return false;
	}
	void handlePCollision(Platform p) {
		
	}
	void handleSCollision(SPlatform p) {
		
	}
}
