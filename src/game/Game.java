package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener{

	static final int MAIN_MENU = 0;
	static final int TUTORIAL = 1;
	static final int IN_GAME = 2;
	static final int END_STATE = 3;
	static final int SETTINGS = 4;
	static final int EASY = 0;
	static final int MEDIUM = 1;
	static final int HARD = 2;
	static final int EXPERT = 4;
	static final int NIGHTMARE = 6;
	boolean KPressed = false;
	static int difficulty = EASY;
	
	Object object;
	Player player = new Player(100, 100, 17, 38);
	Manager manager = new Manager(player);
	
	int currentState = MAIN_MENU;
	
	public static int attackCooldown = 0;
	
	Timer timer;
	
	Game(){
		timer = new Timer(1000/55, this);
	}
	
	void start() {
		Manager.platforms.add(new Platform(100,PixelLegend.HEIGHT-100,70,10));
		Manager.platforms.add(new Platform(100,PixelLegend.HEIGHT-200,70,10));
		Manager.platforms.add(new Platform(300,PixelLegend.HEIGHT-200,70,10));
		Manager.platforms.add(new Platform(500,PixelLegend.HEIGHT-200,70,10));
		Manager.sPlatforms.add(new SPlatform(200, PixelLegend.HEIGHT-100,70,10));
		Manager.sPlatforms.add(new SPlatform(400, PixelLegend.HEIGHT-20,70,10));
		Manager.sPlatforms.add(new SPlatform(0,0,PixelLegend.WIDTH, 10));
		Manager.sPlatforms.add(new SPlatform(0,PixelLegend.HEIGHT - 10,PixelLegend.WIDTH, 10));
		Manager.sPlatforms.add(new SPlatform(0,0,10,PixelLegend.HEIGHT));
		Manager.sPlatforms.add(new SPlatform(PixelLegend.WIDTH - 10,0,10,PixelLegend.HEIGHT));
		timer.start();
	}
	
	void updateMainMenu() {
		
	}
	void updateTutorial() {
		
	}
	void updateGame() {
		//manager.checkPlatformCollision();
		Manager.update();
		
		if(attackCooldown > 0) {
			attackCooldown--;
		}
		//System.out.println(Manager.p.isFalling);
		//System.out.println(manager.p.canFall);
		//System.out.println(manager.p.escPlatform);
		//System.out.println(manager.p.facing);
		//System.out.println(Manager.checkPlatformCollision());
		//System.out.println(Manager.p.yVelocity);
		//System.out.println(Manager.p.x + "," + Manager.p.y);
		//System.out.println(attackCooldown);
		//System.out.println(Manager.p.kXV);
	}
	void updateEnd() {
		
	}
	void updateSettings() {
		
	}
	
	void drawMainMenu(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
		g.setFont(new Font("Papyrus", Font.PLAIN, 24));
		g.setColor(Color.black);
		g.drawString("Main Menu", 300, 50);
		g.drawString("Press [T] for tutorial", 250, 280);
		g.drawString("Press [S] for difficulty settings", 200, 310);
		g.drawString("Press any other key to start game", 190, 350);
		g.setFont(new Font("Skia", Font.PLAIN, 48));
		g.drawString("Pixel Legends", 220, 180);
	}
	void drawTutorial(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
		g.setFont(new Font("Papyrus", Font.PLAIN, 24));
		g.setColor(Color.black);
		g.drawString("Tutorial", 320, 50);
		g.drawString("Use the arrow keys to move left and right.", 160, 100);
		g.drawString("Press [space] to jump.", 240, 140);
		g.drawString("Press [F] to perform a melee attack.", 180, 220);
		g.drawString("Press [G] to perform a ranged attack.", 180, 260);
		g.drawString("Press any key to continue", 220, 550);
		g.setFont(new Font("Papyrus", Font.PLAIN, 20));
		g.drawString("Press [space] while holding [shift] makes you fall faster or off a platform.", 50, 180);
	}
	void drawGame(Graphics g) {
		Manager.draw(g);
	}
	void drawEnd(Graphics g) {
		
	}
	void drawSettings(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
		g.setColor(Color.black);
		g.setFont(new Font("Papyrus", Font.PLAIN, 24));
		g.drawString("Difficulty Settings", 270, 50);
		g.drawString("Press any other key to continue", 220, 550);
		g.setFont(new Font("Papyrus", Font.PLAIN, 30));
		g.drawString("Press [1] for easy", 230, 200);
		g.drawString("Press [2] for normal", 230, 240);
		g.drawString("Press [3] for hard", 230, 280);
		g.drawString("Press [4] for expert", 230, 320);
		g.drawString("Press [5] for nightmare", 230, 360);
	}
	
	public void updateState() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MAIN_MENU) {
			drawMainMenu(g);
		}
		if(currentState == TUTORIAL) {
			drawTutorial(g);
		}
		if(currentState == IN_GAME) {
			drawGame(g);
		}
		if(currentState == END_STATE) {
			drawEnd(g);
		}
		if(currentState == SETTINGS) {
			drawSettings(g);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MAIN_MENU) {
			updateMainMenu();
		}
		if(currentState == TUTORIAL) {
			updateTutorial();
		}
		if(currentState == IN_GAME) {
			updateGame();
		}
		if(currentState == END_STATE) {
			updateEnd();
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(KPressed == false) {
			if (currentState == MAIN_MENU && e.getKeyCode() == KeyEvent.VK_T){
				currentState = TUTORIAL;
			}
			else if(currentState == MAIN_MENU && e.getKeyCode() == KeyEvent.VK_S) {
				currentState = SETTINGS;
			}
			else if(currentState == SETTINGS && e.getKeyCode() == KeyEvent.VK_1) {
				difficulty = EASY;
				System.out.println("difficulty set to easy");
			}
			else if(currentState == SETTINGS && e.getKeyCode() == KeyEvent.VK_2) {
				difficulty = MEDIUM;
				System.out.println("difficulty set to normal");
			}
			else if(currentState == SETTINGS && e.getKeyCode() == KeyEvent.VK_3) {
				difficulty = HARD;
				System.out.println("difficulty set to hard");
			}
			else if(currentState == SETTINGS && e.getKeyCode() == KeyEvent.VK_4) {
				difficulty = EXPERT;
				System.out.println("difficulty set to expert");
			}
			else if(currentState == SETTINGS && e.getKeyCode() == KeyEvent.VK_5) {
				difficulty = NIGHTMARE;
				System.out.println("difficulty set to nightmare");
			}
			else if (currentState == MAIN_MENU) {
				currentState = IN_GAME;
			}
			else if(currentState == TUTORIAL) {
				currentState = MAIN_MENU;
			}
			else if(currentState == END_STATE) {
				currentState = MAIN_MENU;
			}
			else if(currentState == SETTINGS) {
				currentState = MAIN_MENU;
			}
			if(currentState != IN_GAME) {
				updateState();
			}
			KPressed = true;
		}
		
		if(currentState == IN_GAME) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				Manager.p.left = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				Manager.p.right = true;
			}

			
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(e.isShiftDown() == true) {
					Manager.p.fall();
				}
				else {
					Manager.p.jump();
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_F && attackCooldown == 0) {
				Manager.p.attack();
				attackCooldown = Manager.p.attackCooldown;
			}
			if(e.getKeyCode() == KeyEvent.VK_G && attackCooldown == 0) {
				Manager.p.shoot();
				attackCooldown = Manager.p.attackCooldown;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_U) {
				//Manager.p.y = 20;
				//Manager.p.yVelocity = 0;
				/*for(int i = 0; i < Manager.playerAttacks.size(); i++) {
					Manager.playerAttacks.get(i).isAlive = false;
				}*/
				//Manager.p.knockback(15, Object.FACING_RIGHT);
				Manager.warriors.add(new Warrior(Manager.p.x, Manager.p.y, 17, 38));
			}
			
			if(e.getKeyCode() == KeyEvent.VK_K) {
				System.out.println(Manager.warriors.get(0).x +","+ Manager.warriors.get(0).y);
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(currentState == IN_GAME) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				Manager.p.left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				Manager.p.right = false;
			}
		}
		KPressed = false;
	}

}
