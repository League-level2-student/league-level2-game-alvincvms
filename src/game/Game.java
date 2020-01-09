package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener{

	final int MAIN_MENU = 0;
	final int TUTORIAL = 1;
	final int IN_GAME = 2;
	final int END_STATE = 3;
	final int SETTINGS = 4;
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
		
	}
	void drawTutorial(Graphics g) {
		
	}
	void drawGame(Graphics g) {
		Manager.draw(g);
	}
	void drawEnd(Graphics g) {
		
	}
	void drawSettings(Graphics g) {
		
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
