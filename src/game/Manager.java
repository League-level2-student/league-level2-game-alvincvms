package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Manager {
	static Player p;
	static ArrayList<Platform> platforms = new ArrayList<Platform>();
	static ArrayList<SPlatform> sPlatforms = new ArrayList<SPlatform>();
	
	Manager(Player p) {
		this.p = p;
	}
	
	void update() {
		p.update();
	}
	
	void draw(Graphics g) {
		p.draw(g);
	}
	
	public static boolean checkCollision(Rectangle cBox) {
		for(SPlatform sPlatform : sPlatforms) {
			if(cBox.intersects(sPlatform.cBox)) {
				return true;
			}
		}
		for(Platform platform : platforms) {
			if(cBox.intersects(platform.cBox)) {
				return true;
			}
		}
		return false;
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
		p.setYLimitU(-50);
		p.setXLimitL(0);
		p.setXLimitR(PixelLegend.WIDTH);
		return false;
	}
	void handlePCollision(Platform P) {
		if(p.yVelocity >= 0 && p.y + p.height < P.y + 20) {	
			p.setYLimit(P.y + 1);
			if(p.escPlatform == true) {
				p.setYLimit(PixelLegend.HEIGHT);
				p.y += 3;
				p.escPlatform = false;
			}
		}
		else {
			p.setYLimit(PixelLegend.HEIGHT);
		}
	}
	void handleSCollision(SPlatform P) {
		if(p.y + p.height >= P.y && p.yVelocity > 0){
			p.setYLimit(P.y + 1);
		}
		if(p.y < P.y + P.height && p.yVelocity < 0 && p.isFalling == true) {
			p.setYLimitU(P.y + P.height);
		}
		
		
		if(p.x <= P.x + P.width && p.left == true) {
			p.setXLimitL(P.x + P.width);

		}
		if(p.x + p.width >= P.x && p.right == true) {
			p.setXLimitR(P.x);

		}
	}
}
