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
		
	}
	void updateEnd() {
		
	}
	
	void drawMainMenu(Graphics g) {
		
	}
	void drawTutorial(Graphics g) {
		
	}
	void drawGame(Graphics g) {
		
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
		System.out.println(currentState);
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
		if(currentState == MAIN_MENU && e.getKeyCode() != KeyEvent.VK_T) {
			currentState = IN_GAME;
		}
		else {
			currentState = TUTORIAL;
		}
		if(currentState == TUTORIAL) {
			currentState = MAIN_MENU;
		}
		if(currentState == IN_GAME) {
			currentState++;
		}
		if(currentState == END_STATE) {
			currentState = MAIN_MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
