package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class The_Inquisitor extends Monster{
	int attackMode = 0;
	boolean attackFinished = true;
	int cdTimer = 0;
	int atkCounter = 0;
	boolean isAttacking = false;
	
	public static BufferedImage M0;
	public static BufferedImage M1L;
	public static BufferedImage M1R;
	public static BufferedImage M2L;
	public static BufferedImage M2R;
	
	HealthBar hBar = new HealthBar(150, 100, 450, 20, 1);
	
	The_Inquisitor(int x, int y, int width, int height){
		super(x, y, width, height, 240, 300);
		xV = 0;
		yV = 0;
		hBar.outline = true;
		if(Game.difficulty == Game.EASY) {
			maxHP = 580;
			health = 580;
			dmg = 9;
		}
		if(Game.difficulty == Game.MEDIUM) {
			maxHP = 640;
			health = 640;
			dmg = 12;
		}
		if(Game.difficulty == Game.HARD) {
			maxHP = 720;
			health = 720;
			dmg = 14;
		}
		if(Game.difficulty == Game.EXPERT) {
			maxHP = 850;
			health = 850;
			dmg = 17;
		}
		if(Game.difficulty == Game.NIGHTMARE) {
			maxHP = 1200;
			health = 1200;
			dmg = 20;
		}
		
		try {
			M0 = ImageIO.read(this.getClass().getResourceAsStream("TheInquisitor.png"));
			M1L = ImageIO.read(this.getClass().getResourceAsStream("TheInquisitorM1L.png"));
			M1R = ImageIO.read(this.getClass().getResourceAsStream("TheInquisitorM1R.png"));
			M2L = ImageIO.read(this.getClass().getResourceAsStream("TheInquisitorM2L.png"));
			M2R = ImageIO.read(this.getClass().getResourceAsStream("TheInquisitorM2R.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void update() {
		if(hurtTimer > 0) {
			AM = 0.8;
		}
		else {
			AM = 1;
		}
		
		if(cBox.intersects(Manager.p.cBox) && Manager.p.hurtTimer <= 0) {
			if(Game.difficulty == Game.EASY) {
				Manager.p.hurtTimer = 56;
			}
			if(Game.difficulty == Game.MEDIUM) {
				Manager.p.hurtTimer = 48;
			}
			if(Game.difficulty == Game.HARD) {
				Manager.p.hurtTimer = 32;
			}
			if(Game.difficulty == Game.EXPERT) {
				Manager.p.hurtTimer = 27;
			}
			if(Game.difficulty == Game.NIGHTMARE) {
				Manager.p.hurtTimer = 22;
			}
			if(isAttacking) {
				Manager.p.health -= (int) (dmg * 2.4 * AM);
			}
			else {
				Manager.p.health -= (int) (dmg * AM);
			}
			Manager.p.knockback(11, facing);
		}
		
		if(Monster.playerInSight(vBox)) {
			vLength = 1000;
			if(Manager.p.x < x) {
				facing = Object.FACING_LEFT;
			}
			if(Manager.p.x + Manager.p.width > x + width) {
				facing = Object.FACING_RIGHT;
			}
			if(cdTimer == 0) {
				if(!attackFinished) {
					if(attackMode == 1) {
						if(facing == FACING_LEFT) {
							ArcherProjectile p = new ArcherProjectile(x, y + 10, 21, 12, FACING_LEFT, (int) (dmg * 1.5 * AM), 12);
							Manager.archerProjectiles.add(p);
							p.xVelocity = 7;
							p.attack();
						}
						if(facing == FACING_RIGHT) {
							ArcherProjectile p = new ArcherProjectile(x + width - 21, y + 10, 21, 12, FACING_RIGHT, (int) (dmg * 1.5 * AM), 12);
							Manager.archerProjectiles.add(p);
							p.xVelocity = 7;
							p.attack();
						}
						
						if(Game.difficulty == Game.NIGHTMARE) {
							cdTimer = 16;
						}
						else {
							cdTimer = 8;
						}
						
						atkCounter--;
						if(atkCounter == 0) {
							attackFinished = true;
							cdTimer = 90;
						}
					}
					if(attackMode == 2) {
						if(isAttacking == false) {
							if(Manager.p.x > x) {
								if(Game.difficulty == Game.EASY) {
									xV = 8;
								}
								if(Game.difficulty == Game.MEDIUM) {
									xV = 10;
								}
								if(Game.difficulty == Game.HARD) {
									xV = 13;
								}
								if(Game.difficulty == Game.EXPERT) {
									xV = 15;
								}
								if(Game.difficulty == Game.NIGHTMARE) {
									xV = 17;
								}
							}
							else {
								if(Game.difficulty == Game.EASY) {
									xV = -8;
								}
								if(Game.difficulty == Game.MEDIUM) {
									xV = -10;
								}
								if(Game.difficulty == Game.HARD) {
									xV = -13;
								}
								if(Game.difficulty == Game.EXPERT) {
									xV = -15;
								}
								if(Game.difficulty == Game.NIGHTMARE) {
									xV = -17;
								}
							}
						}
						m2Move();
						
						if(!isAttacking) {
							atkCounter--;
						}
						if(atkCounter == 0) {
							attackFinished = true;
							cdTimer = 60;
						}
					}
					if(attackMode == 3) {
						m3Atk();
						atkCounter--;
						if(Game.difficulty == Game.NIGHTMARE) {
							cdTimer = 2;
						}
						else if(Game.difficulty == Game.EXPERT) {
							cdTimer = 2;
						}
						else if(Game.difficulty == Game.HARD) {
							cdTimer = 3;
						}
						else if(Game.difficulty == Game.MEDIUM) {
							cdTimer = 4;
						}
						else {
							cdTimer = 5;
						}
						if(atkCounter == 0) {
							attackFinished = true;
							cdTimer = 120;
						}
					}
				}
				else {
					if(attackMode == 0 || attackMode == 3) {
						if(Game.difficulty == Game.NIGHTMARE) {
							changeMode(5);
						}
						else if(Game.difficulty == Game.EASY){
							changeMode(3);
						}
						else {
							changeMode(4);
						}
						
					}
					else if(attackMode == 1) {
						if(Game.difficulty == Game.NIGHTMARE) {
							changeMode(5);
						}
						else if(Game.difficulty == Game.EXPERT) {
							changeMode(4);
						}
						else {
							changeMode(3);
						}
					}
					else {
						if(Game.difficulty == Game.NIGHTMARE) {
							changeMode(72);
						}
						else if(Game.difficulty == Game.EXPERT) {
							changeMode(64);
						}
						else if(Game.difficulty == Game.HARD) {
							changeMode(52);
						}
						else if(Game.difficulty == Game.MEDIUM) {
							changeMode(45);
						}
						else {
							changeMode(36);
						}
					}
				}
			}
		}
		
		
		gravity();
		
		hBar = new HealthBar(150, 100, 450, 20, (double)health/maxHP);
		hBar.outline = true;
		super.update();
		
		if(hurtTimer > 0) {
			hurtTimer--;
		}
		
		if(cdTimer > 0) {
			cdTimer--;
		}
	}
	
	void gravity() {
		int newY = y;
		Rectangle newCBox;
		
		if(yV < 25) {
			yV += gravity;
		}
		newY += yV;
		newCBox = new Rectangle(x, newY, width, height);
		
		if(!Manager.checkSolidCollision(newCBox)) {
			y = newY;
		}
		else {
			while(Manager.checkSolidCollision(newCBox) == true) {
				newY -= 1;
				newCBox = new Rectangle(x, newY, width, height);
			}
			y = newY;
			yV = 0;
		}
	}
	
	void m2Move() {
		isAttacking = true;
		int newX = x;
		
		newX += xV;
		
		Rectangle newCBox = new Rectangle(newX, y, width, height);
		
		if(Manager.checkSolidCollision(newCBox) == false) {
			x = newX;	
		}
		else {
			if(xV < 0) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX += 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
				if(Game.difficulty == Game.EASY) {
					cdTimer = 25;
				}
				else if(Game.difficulty == Game.MEDIUM) {
					cdTimer = 18;
				}
				else if(Game.difficulty == Game.HARD) {
					cdTimer = 8;
				}
				else if(Game.difficulty == Game.EXPERT) {
					cdTimer = 4;
				}
				else {
					cdTimer = 1;
				}
			}
			if(xV > 0) {
				while(Manager.checkSolidCollision(newCBox) == true) {
					newX -= 1;
					newCBox = new Rectangle(newX, y, width, height);
				}
				x = newX;
				if(Game.difficulty == Game.EASY) {
					cdTimer = 25;
				}
				else if(Game.difficulty == Game.MEDIUM) {
					cdTimer = 18;
				}
				else if(Game.difficulty == Game.HARD) {
					cdTimer = 8;
				}
				else if(Game.difficulty == Game.EXPERT) {
					cdTimer = 4;
				}
				else {
					cdTimer = 1;
				}
			}
			
			isAttacking = false;
		}
	}
	
	void m3Atk() {
		Random r = new Random();
		BossProjectile p = new BossProjectile(r.nextInt(723) + 10, 10, 8, 20, (int) (dmg * 1.8 * AM));
		Manager.bossProjectiles.add(p);
		p.facing = r.nextInt(2);
		p.attack();
	}
	
	void changeMode(int atk) {
		if(attackMode < 3) {
			attackMode++;
		}
		else {
			attackMode = 1;
		}
		attackFinished = false;
		atkCounter = atk;
	}
	
	void draw(Graphics g) {
		if(attackMode == 0 || attackMode == 3) {
			g.drawImage(M0, x-8, y-20, width+16, height+22, null);
		}
		else if(attackMode == 1) {
			if(facing == FACING_LEFT) {
				g.drawImage(M1L, x-8, y-20, width+16, height+22, null);
			}
			if(facing == FACING_RIGHT) {
				g.drawImage(M1R, x-8, y-20, width+16, height+22, null);
			}
		}
		else if(attackMode == 2) {
			if(xV < 0) {
				g.drawImage(M2L, x-8, y-20, width+16, height+22, null);
			}
			if(xV > 0) {
				g.drawImage(M2R, x-8, y-20, width+16, height+22, null);
			}
		}
		
		hBar.draw(g);
		
		g.setFont(new Font("TimesRoman", Font.ITALIC, 24));
		g.setColor(Color.black);
		g.drawString("The Inquisitor", 300, 85);
		
		g.setFont(new Font("Arial", Font.PLAIN, 22));
		g.drawString(Integer.toString(health), 300, 117);
		g.drawString("/", 367, 117);
		g.drawString(Integer.toString(maxHP), 400, 117);
	}
}
