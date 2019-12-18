package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Manager {
	static Player p;
	static ArrayList<PlayerAttack> playerAttacks = new ArrayList<PlayerAttack>();
	static ArrayList<PlayerProjectile> playerProjectiles = new ArrayList<PlayerProjectile>();
	
	static ArrayList<Platform> platforms = new ArrayList<Platform>();
	static ArrayList<SPlatform> sPlatforms = new ArrayList<SPlatform>();
	static Platform collidingPlatform;
	
	Manager(Player p) {
		Manager.p = p;
	}
	
	static void update() {
		p.update();
		for(Platform p : Manager.platforms) {
			p.update();
		}
		for(SPlatform p : Manager.sPlatforms) {
			p.update();
		}
		for(PlayerAttack attack : playerAttacks) {
			attack.update();
		}
		for(PlayerProjectile projectile : playerProjectiles) {
			projectile.update();
		}
		
		purgeObjects();
	}
	
	static void draw(Graphics g) {
		p.draw(g);
		for(Platform p : Manager.platforms) {
			p.draw(g);
		}
		for(SPlatform p : Manager.sPlatforms) {
			p.draw(g);
		}
		for(PlayerAttack attack : playerAttacks) {
			attack.draw(g);
		}
		for(PlayerProjectile projectile : playerProjectiles) {
			projectile.draw(g);
		}
	}
	
	public static boolean checkSolidCollision(Rectangle cBox) {
		for(SPlatform sPlatform : sPlatforms) {
			if(cBox.intersects(sPlatform.cBox)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkPlatformCollision(Rectangle cBox) {
		for(Platform platform : platforms) {
			if(cBox.intersects(platform.cBox)) {
				collidingPlatform = platform;
				return true;
			}
		}
		return false;
	}
	public static void handlePCollision(Platform P) {
		if(p.yVelocity >= 0) {
			p.y = P.y - p.height + 1;
			p.canJump = 1;
			p.doubleJump = true;
			p.yVelocity = 0;
			p.canFall = true;
			p.isFalling = false;
			if(p.escPlatform) {
				while(p.y + p.height < P.y + 10) {
					p.y += 1;
				}
				p.yVelocity += 15;
				p.escPlatform = false;
			}
		}
	}
	
	public static void purgeObjects() {
		for(int i = 0; i < playerAttacks.size(); i++) {
			if(!playerAttacks.get(i).isAlive) {
				playerAttacks.remove(i);
			}
		}
		for(int i = 0; i < playerProjectiles.size(); i++) {
			if(!playerProjectiles.get(i).isAlive) {
				playerProjectiles.remove(i);
			}
		}
	}
	
	/*void handleSCollision(SPlatform P) {
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
	}*/
}
