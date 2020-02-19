package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Manager {
	static Player p;
	static Flag f;
	static boolean bossDefeated = false;
	static boolean playerDead = false;
	
	static ArrayList<PlayerAttack> playerAttacks = new ArrayList<PlayerAttack>();
	static ArrayList<PlayerProjectile> playerProjectiles = new ArrayList<PlayerProjectile>();
	static ArrayList<Warrior> warriors = new ArrayList<Warrior>();
	static ArrayList<Archer> archers = new ArrayList<Archer>();
	static ArrayList<ArcherProjectile> archerProjectiles = new ArrayList<ArcherProjectile>();
	static ArrayList<The_Inquisitor> boss = new ArrayList<The_Inquisitor>();
	static ArrayList<BossProjectile> bossProjectiles = new ArrayList<BossProjectile>();
	
	static ArrayList<Platform> platforms = new ArrayList<Platform>();
	static ArrayList<SPlatform> sPlatforms = new ArrayList<SPlatform>();
	static Platform collidingPlatform;
	
	Manager(Player p) {
		Manager.p = p;
		f = new Flag(0,0,40,60);
	}
	
	static void update() {
		purgeObjects();
		p.update();
		f.update();
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
		for(Warrior w : warriors) {
			w.update();
		}
		for(Archer a : archers) {
			a.update();
		}
		for(ArcherProjectile p : archerProjectiles) {
			p.update();
		}
		for(The_Inquisitor b : boss) {
			b.update();
		}
		for(BossProjectile p : bossProjectiles) {
			p.update();
		}
		
		if(playerAttacks.size() > 0) {
			for(int i = 0; i < playerAttacks.size(); i++) {
				if(warriors.size() > 0) {
					for(int n = 0; n < warriors.size(); n++) {
						if(playerAttacks.get(i).cBox.intersects(warriors.get(n).cBox)) {
							playerAttacks.get(i).isAlive = false;
							warriors.get(n).knockback(14, playerAttacks.get(i).facing);
							warriors.get(n).hurtTimer = 24;
							warriors.get(n).health -= playerAttacks.get(i).dmg;
							if(warriors.get(n).health <= 0) {
								warriors.get(n).isAlive = false;
							}
						}
					}
				}
				if(archers.size() > 0) {
					for(int n = 0; n < archers.size(); n++) {
						if(playerAttacks.get(i).cBox.intersects(archers.get(n).cBox)) {
							playerAttacks.get(i).isAlive = false;
							archers.get(n).knockback(14, playerAttacks.get(i).facing);
							archers.get(n).hurtTimer = 24;
							archers.get(n).health -= playerAttacks.get(i).dmg;
							if(archers.get(n).health <= 0) {
								archers.get(n).isAlive = false;
							}
						}
					}
				}
				if(boss.size() > 0) {
					for(int n = 0; n < boss.size(); n++) {
						if(playerAttacks.get(i).cBox.intersects(boss.get(n).cBox)) {
							playerAttacks.get(i).isAlive = false;
							boss.get(n).hurtTimer = 18;
							boss.get(n).health -= playerAttacks.get(i).dmg;
							if(boss.get(n).health <= 0) {
								boss.get(n).isAlive = false;
							}
						}
					}
				}
			}
		}
		if(playerProjectiles.size() > 0) {
			for(int i = 0; i < playerProjectiles.size(); i++) {
				if(warriors.size() > 0) {
					for(int n = 0; n < warriors.size(); n++) {
						if(playerProjectiles.get(i).cBox.intersects(warriors.get(n).cBox) && playerProjectiles.get(i).isAlive) {
							playerProjectiles.get(i).isAlive = false;
							warriors.get(n).knockback(12, playerProjectiles.get(i).facing);
							warriors.get(n).hurtTimer = 10;
							warriors.get(n).health -= playerProjectiles.get(i).dmg;
							if(warriors.get(n).health <= 0) {
								warriors.get(n).isAlive = false;
							}
							if(!Monster.playerInSight(warriors.get(n).vBox)) {
								warriors.get(n).vLength += 90;
							}
						}
					}
				}
				if(archers.size() > 0) {
					for(int n = 0; n < archers.size(); n++) {
						if(playerProjectiles.get(i).cBox.intersects(archers.get(n).cBox) && playerProjectiles.get(i).isAlive) {
							playerProjectiles.get(i).isAlive = false;
							archers.get(n).knockback(10, playerProjectiles.get(i).facing);
							archers.get(n).hurtTimer = 10;
							archers.get(n).health -= playerProjectiles.get(i).dmg;
							if(archers.get(n).health <= 0) {
								archers.get(n).isAlive = false;
							}
							if(!Monster.playerInSight(archers.get(n).vBox)) {
								archers.get(n).vLength += 120;
							}
						}
					}
				}
				if(boss.size() > 0) {
					for(int n = 0; n < boss.size(); n++) {
						if(playerProjectiles.get(i).cBox.intersects(boss.get(n).cBox) && playerProjectiles.get(i).isAlive) {
							playerProjectiles.get(i).isAlive = false;
							boss.get(n).hurtTimer = 10;
							if(boss.get(n).attackMode == 3) {
								boss.get(n).health -= (int) (playerProjectiles.get(i).dmg * 0.85);
							}
							else {
								boss.get(n).health -= playerProjectiles.get(i).dmg;
							}
							if(boss.get(n).health <= 0) {
								boss.get(n).isAlive = false;
							}
							if(!Monster.playerInSight(boss.get(n).vBox)) {
								boss.get(n).vLength += 500;
							}
						}
					}
				}
			}
		}
		if(archerProjectiles.size() > 0) {
			for(int i = 0; i < archerProjectiles.size(); i++) {
				if(playerAttacks.size() > 0) {
					for(int a = 0; a < playerAttacks.size(); a++) {
						if(archerProjectiles.get(i).cBox.intersects(playerAttacks.get(a).cBox)) {
							archerProjectiles.get(i).finishAttack();
						}
					}
				}
				if(archerProjectiles.get(i).cBox.intersects(p.cBox) && Manager.p.hurtTimer <= 0 && archerProjectiles.get(i).isAlive) {
					Manager.p.health -= archerProjectiles.get(i).dmg;
					Manager.p.knockback(10, archerProjectiles.get(i).facing);
					Manager.p.hurtTimer = archerProjectiles.get(i).PHT;
					archerProjectiles.get(i).finishAttack();
				}
			}
		}
		if(bossProjectiles.size() > 0) {
			for(int i = 0; i < bossProjectiles.size(); i++) {
				if(bossProjectiles.get(i).cBox.intersects(p.cBox) && Manager.p.hurtTimer <= 0 && bossProjectiles.get(i).isAlive) {
					Manager.p.health -= bossProjectiles.get(i).dmg;
					Manager.p.knockback(10, bossProjectiles.get(i).facing);
					Manager.p.hurtTimer = 24;
					bossProjectiles.get(i).finishAttack();
				}
			}
		}

		if(f.cBox.intersects(p.cBox)) {
			Game.updateRoom();
		}
	}
	
	static void draw(Graphics g) {
		p.draw(g);
		f.draw(g);
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
		for(Warrior w : warriors) {
			w.draw(g);
		}
		for(Archer a : archers) {
			a.draw(g);
		}
		for(ArcherProjectile p : archerProjectiles) {
			p.draw(g);
		}
		for(The_Inquisitor b : boss) {
			b.draw(g);
		}
		for(BossProjectile p : bossProjectiles) {
			p.draw(g);
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
		for(int i = 0; i < warriors.size(); i++) {
			if(!warriors.get(i).isAlive) {
				warriors.remove(i);
			}
		}
		for(int i = 0; i < archers.size(); i++) {
			if(!archers.get(i).isAlive) {
				archers.remove(i);
			}
		}
		for(int i = 0; i < archerProjectiles.size(); i++) {
			if(!archerProjectiles.get(i).isAlive) {
				archerProjectiles.remove(i);
			}
		}
		for(int i = 0; i < boss.size(); i++) {
			if(!boss.get(i).isAlive) {
				boss.remove(i);
				bossDefeated = true;
				Game.colorTimer = 250;
			}
		}
		for(int i = 0; i < bossProjectiles.size(); i++) {
			if(!bossProjectiles.get(i).isAlive) {
				bossProjectiles.remove(i);
			}
		}
		if(p.health <= 0 && bossDefeated == false) {
			playerDead = true;
			Game.colorTimer = 250;
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
