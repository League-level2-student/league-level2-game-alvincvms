package LeagueInvaders;

import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Random;


public class ObjectManager {
	
	Rocketship S;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	
	ObjectManager(Rocketship S) {
		this.S = S;
	}
	
	void update() {
		S.update();
		for(Projectile p : projectiles) {
			p.update();
		}
		for(Alien a : aliens) {
			a.update();
		}
	}
	
	void draw(Graphics g) {
		S.draw(g);
		for(Projectile p : projectiles) {
			p.draw(g);
		}
		for(Alien a : aliens) {
			a.draw(g);
		}
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien(Alien a) {
		aliens.add(a);
	}
	
	public void manageEnemies() {
		if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
            addAlien(new Alien(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));
            enemyTimer = System.currentTimeMillis();
		}
		if(enemySpawnTime >= 10) {
		enemySpawnTime = 1000 - (score*8);
		}
	}
	
	void purgeObjects() {
		for(int i = 0; i < projectiles.size();i++) {
			if(projectiles.get(i).isAlive == false) {
				projectiles.remove(i);
			}
		}
		
		for(int i = 0; i < aliens.size();i++) {
			if(aliens.get(i).isAlive == false) {
				aliens.remove(i);
				score++;
			}
		}
	}
	
	void checkCollision() {
		for(Alien a : aliens) {
			if(S.collisionBox.intersects(a.collisionBox)) {
				S.isAlive = false;
			}
			
			for(Projectile p : projectiles) {
				if(p.collisionBox.intersects(a.collisionBox)) {
					p.isAlive = false;
					a.isAlive = false;
					
				}
			}
		}
		
	}
	
	int getScore() {
		return score;
	}
	
}
