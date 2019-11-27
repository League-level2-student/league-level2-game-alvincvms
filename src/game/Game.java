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
	final int R_start = 0;
	boolean KPressed = false;
	
	Object object;
	Player player = new Player(0, 0, 17, 38);
	Manager manager = new Manager(player);
	
	int currentState = MAIN_MENU;
	int currentRoom = R_start;
	
	int testCounter = 1;
	
	Timer timer;
	
	Game(){
		timer = new Timer(1000/55, this);
	}
	
	void start() {
		manager.platforms.add(new Platform(100,PixelLegend.HEIGHT-100,70,10));
		manager.platforms.add(new Platform(100,PixelLegend.HEIGHT-200,70,10));
		manager.platforms.add(new Platform(300,PixelLegend.HEIGHT-200,70,10));
		manager.platforms.add(new Platform(500,PixelLegend.HEIGHT-200,70,10));
		manager.sPlatforms.add(new SPlatform(200, PixelLegend.HEIGHT-100,70,10));
		timer.start();
	}
	
	void updateMainMenu() {
		
	}
	void updateTutorial() {
		
	}
	void updateGame() {
		manager.checkPlatformCollision();
		manager.p.update();
		for(Platform p : manager.platforms) {
			p.update();
		}
		for(SPlatform p : manager.sPlatforms) {
			p.update();
		}
		//System.out.println(manager.p.isFalling);
		//System.out.println(manager.p.canFall);
		//System.out.println(manager.p.escPlatform);
		//System.out.println(manager.p.facing);
	}
	void updateEnd() {
		
	}
	
	void drawMainMenu(Graphics g) {
		
	}
	void drawTutorial(Graphics g) {
		
	}
	void drawGame(Graphics g) {
		manager.p.draw(g);
		for(Platform p : manager.platforms) {
			p.draw(g);
		}
		for(SPlatform p : manager.sPlatforms) {
			p.draw(g);
		}
	}
	void drawEnd(Graphics g) {
		
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
		
		
		testCounter --;
		if(testCounter < 0) {
			testCounter = 1;
		}
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
			else if (currentState == MAIN_MENU) {
				currentState = IN_GAME;
			}
			else if(currentState == TUTORIAL) {
				currentState = MAIN_MENU;
			}
			else if(currentState == END_STATE) {
				currentState = MAIN_MENU;
			}
			KPressed = true;
		}
		
		if(currentState == IN_GAME) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				manager.p.left = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				manager.p.right = true;
			}

			
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(e.isShiftDown() == true) {
					manager.p.fall();
				}
				else {
					manager.p.jump();
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_U) {
				manager.p.y = 0;
				manager.p.yVelocity = 0;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(currentState == IN_GAME) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				manager.p.left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				manager.p.right = false;
			}
		}
		KPressed = false;
	}

}
