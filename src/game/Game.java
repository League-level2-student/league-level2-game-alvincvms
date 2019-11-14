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
	
	Timer timer;
	
	Game(){
		timer = new Timer(1000/60, this);
	}
	
	void start() {
		timer.start();
	}
	
	void updateMainMenu() {
		
	}
	void updateTutorial() {
		
	}
	void updateGame() {
		manager.p.update();
	}
	void updateEnd() {
		
	}
	
	void drawMainMenu(Graphics g) {
		
	}
	void drawTutorial(Graphics g) {
		
	}
	void drawGame(Graphics g) {
		manager.p.draw(g);
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
				manager.p.jump();
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
