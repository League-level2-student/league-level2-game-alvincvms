package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import javazoom.jl.player.advanced.AdvancedPlayer;

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
	Random r = new Random();
	
	int currentState = MAIN_MENU;
	static int currentRoom = 0;
	int tutorialPage = 1;
	float tutorialHP;
	
	public static int mAttackCooldown = 0;
	public static int rAttackCooldown = 0;
	public static int colorTimer = 0;
	
	static Song menu = new Song("New Hero in Town.mp3");
	static Song game = new Song("Raving Energy.mp3");
	static Song boss = new Song("Metalmania.mp3");
	static Song win = new Song("Flying Kerfuffle.mp3");
	static Song loss = new Song("Man Down.mp3");
	static Song swoosh1 = new Song("swoosh1.mp3");
	static Song swoosh2 = new Song("swoosh2.mp3");
	static Song bow = new Song("bow.mp3");
	
	Timer timer;
	
	Game(){
		timer = new Timer(1000/55, this);
	}
	
	void start() {
		/*
		Manager.platforms.add(new Platform(100,PixelLegend.HEIGHT-100,70,10));
		Manager.platforms.add(new Platform(100,PixelLegend.HEIGHT-200,70,10));
		Manager.platforms.add(new Platform(300,PixelLegend.HEIGHT-200,70,10));
		Manager.platforms.add(new Platform(500,PixelLegend.HEIGHT-200,70,10));
		Manager.sPlatforms.add(new SPlatform(200, PixelLegend.HEIGHT-100,70,10));
		*/
		timer.start();
	}
	
	void updateMainMenu() {
		
	}
	void updateTutorial() {
		
	}
	void updateGame() {
		//manager.checkPlatformCollision();
		Manager.update();
		
		if(mAttackCooldown > 0) {
			mAttackCooldown--;
		}
		if(rAttackCooldown > 0) {
			rAttackCooldown--;
		}
		if(Manager.playerDead == true) {
			currentState = END_STATE;
		}
		if(Manager.bossDefeated == true) {
			currentState = END_STATE;
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
		if(colorTimer > 0) {
			colorTimer--;
		}
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
		
		g.setFont(new Font("Papyrus", Font.PLAIN, 30));
		g.drawString("Current difficulty:", 170, 420);
		g.setFont(new Font("Rockwell", Font.PLAIN, 24));
		if(difficulty == EASY) {
			g.setColor(new Color(0, 255, 51));
			g.drawString("EASY", 450, 420);
		}
		if(difficulty == MEDIUM) {
			g.setColor(new Color(51, 153, 255));
			g.drawString("NORMAL", 450, 420);
		}
		if(difficulty == HARD) {
			g.setColor(new Color(255, 255, 0));
			g.drawString("HARD", 450, 420);
		}
		if(difficulty == EXPERT) {
			g.setColor(new Color(255, 153, 0));
			g.drawString("EXPERT", 450, 420);
		}
		if(difficulty == NIGHTMARE) {
			g.setColor(new Color(255, 0, 0));
			g.drawString("NIGHTMARE", 450, 420);
		}
	}
	void drawTutorial(Graphics g) {
		if(tutorialPage == 1) {
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
			g.setColor(Color.black);
			g.setFont(new Font("Papyrus", Font.PLAIN, 48));
			g.drawString(Integer.toString(tutorialPage), 385, 95);
			g.setFont(new Font("Papyrus", Font.PLAIN, 24));
			g.drawString("Tutorial", 320, 50);
			g.drawString("Page:", 290, 90);
			g.drawString("Use the arrow keys to move left and right.", 160, 160);
			g.drawString("Press [space] to jump, press again to perform a double jump.", 50, 200);
			g.drawString("Press [S] to perform a melee attack.", 180, 280);
			g.drawString("Press [D] to perform a ranged attack.", 180, 320);
			g.drawString("Press any other key to continue", 220, 550);
			g.setFont(new Font("Papyrus", Font.PLAIN, 20));
			g.drawString("Press [space] while holding [shift] makes you fall faster or off a platform.", 50, 240);
			g.setFont(new Font("Papyrus", Font.PLAIN, 16));
			g.drawString("Press left arrow key to the previous page", 10, 520);
			g.drawString("Press right arrow key to the next page", 460, 520);
			try {
				g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("PlayerR.png")), 330, 370, 85, 100, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(tutorialPage == 2) {
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
			g.setColor(Color.black);
			g.setFont(new Font("Papyrus", Font.PLAIN, 48));
			g.drawString(Integer.toString(tutorialPage), 385, 95);
			g.setFont(new Font("Papyrus", Font.PLAIN, 24));
			g.drawString("Tutorial", 320, 50);
			g.drawString("Page:", 290, 90);
			g.drawString("Your goal is to beat the final boss.", 100, 150);
			g.drawString("To do so, you have to get to the flags first.", 95, 235);
			g.drawString("Watch out for the enemies!", 200, 315);
			g.drawString("Press any other key to continue", 220, 550);
			g.setFont(new Font("Papyrus", Font.PLAIN, 16));
			g.drawString("Press left arrow key to the previous page", 10, 520);
			g.drawString("Press right arrow key to the next page", 460, 520);
			try {
				g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("TheInquisitor.png")), 570, 75, 60, 110, null);
				g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("Flag.png")), 590, 197, 60, 80, null);
				g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("WarriorR.png")), 200, 375, 100, 110, null);
				g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("ArcherL.png")), 440, 375, 100, 110, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(tutorialPage == 3) {
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
			g.setColor(Color.black);
			g.setFont(new Font("Papyrus", Font.PLAIN, 48));
			g.drawString(Integer.toString(tutorialPage), 385, 95);
			g.setFont(new Font("Papyrus", Font.PLAIN, 24));
			g.drawString("Tutorial", 320, 50);
			g.drawString("Page:", 290, 90);
			g.drawString("This is your health bar,", 300, 140);
			g.drawString("it indicates the amount of HP you have left.", 280, 170);
			g.drawString("You loses if you run out of HP or failed to defeat the boss.", 90, 220);
			g.drawString("A melee attack allows you to perform a sword slash that", 90, 280);
			g.drawString("is able to block enemy arrows and has a fast cooldown time.", 80, 310);
			g.drawString("A ranged attack allows you to shoot an arrow that does", 95, 370);
			g.drawString("Gray blocks are platforms that you can jump onto.", 95, 455);
			g.drawString("Press any other key to continue", 220, 550);
			g.setFont(new Font("Papyrus", Font.PLAIN, 16));
			g.drawString("Press left arrow key to the previous page", 10, 520);
			g.drawString("Press right arrow key to the next page", 460, 520);
			g.setFont(new Font("Papyrus", Font.PLAIN, 22));
			g.drawString("less damage than melee attacks but is able to hit enemies from a distance.", 24, 400);
			
			HealthBar hp = new HealthBar(75, 125, 175, 20, tutorialHP);
			hp.outline = true;
			hp.draw(g);
		}
	}
	void drawGame(Graphics g) {
		Manager.draw(g);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString("HP", 30, 70);
		g.setFont(new Font("Arial", Font.PLAIN, 22));
		if(Manager.p.health >= 0) {
			g.drawString(Manager.p.health + "/" + Manager.p.maxHP, 120, 69);
		}
		else {
			g.drawString(0 + "/" + Manager.p.maxHP, 120, 69);
		}
	}
	void drawEnd(Graphics g) {
		if(Manager.bossDefeated) {
			g.setColor(new Color(255,255,colorTimer));
			g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
			g.setColor(Color.black);
			g.setFont(new Font("Papyrus", Font.PLAIN, 60));
			g.drawString("You Won!", 240, 280);
			g.setFont(new Font("Papyrus", Font.PLAIN, 24));
			g.setColor(new Color(0 + colorTimer,0 + colorTimer,0));
			g.drawString("Press any key to continue", 230, 550);
		}
		if(Manager.playerDead) {
			g.setColor(new Color(255 - colorTimer,0,0));
			g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
			g.setColor(Color.black);
			g.setFont(new Font("Papyrus", Font.PLAIN, 60));
			g.drawString("You Died!", 240, 280);
			g.setFont(new Font("Papyrus", Font.PLAIN, 24));
			g.setColor(new Color(0 + colorTimer,0,0));
			g.drawString("Press any key to continue", 230, 550);
		}
	}
	void drawSettings(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, PixelLegend.WIDTH, PixelLegend.HEIGHT);
		g.setColor(Color.black);
		g.setFont(new Font("Papyrus", Font.PLAIN, 24));
		g.drawString("Difficulty Settings", 270, 50);
		g.drawString("Press any other key to continue", 220, 550);
		g.setFont(new Font("Papyrus", Font.PLAIN, 30));
		g.drawString("Current difficulty:", 150, 125);
		g.drawString("Press [1] for easy", 230, 200);
		g.drawString("Press [2] for normal", 230, 240);
		g.drawString("Press [3] for hard", 230, 280);
		g.drawString("Press [4] for expert", 230, 320);
		g.drawString("Press [5] for nightmare", 230, 360);
		g.setFont(new Font("Rockwell", Font.PLAIN, 24));
		if(difficulty == EASY) {
			g.setColor(new Color(0, 255, 51));
			g.drawString("EASY", 400, 125);
			g.drawString("A great experience!", 237, 420);
		}
		if(difficulty == MEDIUM) {
			g.setColor(new Color(51, 153, 255));
			g.drawString("NORMAL", 400, 125);
			g.drawString("Your journey awaits.", 235, 420);
		}
		if(difficulty == HARD) {
			g.setColor(new Color(255, 255, 0));
			g.drawString("HARD", 400, 125);
			g.drawString("A good challenge.", 246, 420);
		}
		if(difficulty == EXPERT) {
			g.setColor(new Color(255, 153, 0));
			g.drawString("EXPERT", 400, 125);
			g.drawString("Only for the pros!", 240, 420);
		}
		if(difficulty == NIGHTMARE) {
			g.setColor(new Color(255, 0, 0));
			g.drawString("NIGHTMARE", 400, 125);
			g.drawString("IMPOSSIBLE.", 270, 420);
		}
	}
	
	public void updateState() {
		
	}
	
	public static void updateRoom() {
		stopAll();
		Manager.platforms.clear();
		Manager.sPlatforms.clear();
		Manager.playerAttacks.clear();
		Manager.playerProjectiles.clear();
		Manager.warriors.clear();
		Manager.archers.clear();
		Manager.archerProjectiles.clear();
		Manager.boss.clear();
		Manager.bossProjectiles.clear();
		
		currentRoom++;
		
		if(currentRoom == 1) {
			Manager.p.x = 60;
			Manager.p.y = 552;
			
			Manager.sPlatforms.add(new SPlatform(0,0,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,PixelLegend.HEIGHT - 10,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(PixelLegend.WIDTH - 10,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(0,420,625,10));
			Manager.sPlatforms.add(new SPlatform(125,160,625,10));
			
			Manager.platforms.add(new Platform(650,420,70,8));
			Manager.platforms.add(new Platform(650,500,70,8));
			Manager.platforms.add(new Platform(28,160,70,8));
			Manager.platforms.add(new Platform(28,240,70,8));
			Manager.platforms.add(new Platform(28,320,70,8));
			
			Manager.warriors.add(new Warrior(650, 552, 17, 38));
			Manager.warriors.add(new Warrior(200,382,17,38));
			Manager.f = new Flag(680, 100, 40, 60);
			game.play();
		}
		if(currentRoom == 2) {
			Manager.p.x = 60;
			Manager.p.y = 552;
			
			Manager.sPlatforms.add(new SPlatform(0,0,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,PixelLegend.HEIGHT - 10,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(PixelLegend.WIDTH - 10,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(330,70,10,530));
			Manager.sPlatforms.add(new SPlatform(330,70,325,10));
			Manager.sPlatforms.add(new SPlatform(0,500,295,10));
			Manager.sPlatforms.add(new SPlatform(28,305,284,10));
			Manager.sPlatforms.add(new SPlatform(390,150,360,10));
			Manager.sPlatforms.add(new SPlatform(330,300,325,10));
			Manager.sPlatforms.add(new SPlatform(390,480,360,10));
			
			Manager.platforms.add(new Platform(10,400,35,8));
			Manager.platforms.add(new Platform(295,400,35,8));
			if(difficulty == EASY) {
				Manager.platforms.add(new Platform(27,205,55,8));
				Manager.platforms.add(new Platform(165,135,55,8));
			}
			else if(difficulty == MEDIUM) {
				Manager.platforms.add(new Platform(27,205,45,8));
				Manager.platforms.add(new Platform(170,135,45,8));
			}
			else if(difficulty == HARD) {
				Manager.platforms.add(new Platform(27,205,35,8));
				Manager.platforms.add(new Platform(172,135,35,8));
			}
			else {
				Manager.platforms.add(new Platform(27,205,8,8));
				Manager.platforms.add(new Platform(175,135,8,8));
			}
			Manager.platforms.add(new Platform(340,225,50,8));
			Manager.platforms.add(new Platform(690,390,50,8));
			
			Manager.warriors.add(new Warrior(120, 462, 17, 38));
			Manager.warriors.add(new Warrior(156, 267, 17, 38));
			Manager.warriors.add(new Warrior(465, 112, 17, 38));
			Manager.warriors.add(new Warrior(540, 442, 17, 38));
			Manager.warriors.add(new Warrior(625, 552, 17, 38));
			Manager.archers.add(new Archer(675, 552, 17, 38));
			Manager.archers.add(new Archer(620, 32, 17, 38));
			
			Manager.f = new Flag(680,530,40,60);
			game.play();
		}
		if(currentRoom == 3) {
			Manager.p.x = 30;
			Manager.p.y = 552;
			
			Manager.sPlatforms.add(new SPlatform(0,0,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,PixelLegend.HEIGHT - 10,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(PixelLegend.WIDTH - 10,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(0,490,540,10));
			Manager.sPlatforms.add(new SPlatform(670,120,10,480));
			Manager.sPlatforms.add(new SPlatform(240,300,10,190));
			Manager.sPlatforms.add(new SPlatform(240,300,50,10));
			Manager.sPlatforms.add(new SPlatform(120,130,10,260));
			Manager.sPlatforms.add(new SPlatform(180,22,8,58));
			Manager.sPlatforms.add(new SPlatform(420,102,50,10));
			
			Manager.platforms.add(new Platform(680,120,60,8));
			Manager.platforms.add(new Platform(680,230,60,8));
			Manager.platforms.add(new Platform(680,340,60,8));
			Manager.platforms.add(new Platform(680,450,60,8));
			Manager.platforms.add(new Platform(430,380,8,8));
			Manager.platforms.add(new Platform(130,382,25,8));
			Manager.platforms.add(new Platform(10,382,8,8));
			Manager.platforms.add(new Platform(60,272,8,8));
			Manager.platforms.add(new Platform(112,162,8,8));
			Manager.platforms.add(new Platform(147,162,8,8));
			Manager.platforms.add(new Platform(230,102,8,8));
			Manager.platforms.add(new Platform(395,101,25,11));
			Manager.platforms.add(new Platform(470,101,25,11));
			Manager.platforms.add(new Platform(580,130,34,8));
			
			Manager.warriors.add(new Warrior(340, 452, 17, 38));
			Manager.warriors.add(new Warrior(10, 452, 17, 38));
			Manager.warriors.add(new Warrior(340, 552, 17, 38));
			Manager.warriors.add(new Warrior(460, 552, 17, 38));
			Manager.warriors.add(new Warrior(435, 64, 17, 38));
			Manager.warriors.add(new Warrior(437, 64, 17, 38));
			Manager.warriors.add(new Warrior(445, 64, 17, 38));
			Manager.warriors.add(new Warrior(449, 64, 17, 38));
			Manager.warriors.add(new Warrior(451, 64, 17, 38));
			Manager.warriors.add(new Warrior(453, 64, 17, 38));
			Manager.archers.add(new Archer(360, 552, 17, 38));
			Manager.archers.add(new Archer(510, 552, 17, 38));
			
			Manager.f = new Flag(690,530,40,60);
			game.play();
		}
		if(currentRoom == 4) {
			Manager.p.x = 17;
			Manager.p.y = 552;
			
			Manager.sPlatforms.add(new SPlatform(0,0,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,PixelLegend.HEIGHT - 10,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(PixelLegend.WIDTH - 10,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(0,420,625,10));
			Manager.sPlatforms.add(new SPlatform(125,160,625,10));
			Manager.sPlatforms.add(new SPlatform(450,500,75,10));
			Manager.sPlatforms.add(new SPlatform(330,350,75,10));
			
			Manager.platforms.add(new Platform(665,500,50,8));
			Manager.platforms.add(new Platform(250,500,50,8));
			Manager.platforms.add(new Platform(200,350,50,8));
			Manager.platforms.add(new Platform(480,350,50,8));
			Manager.platforms.add(new Platform(80,242,8,8));
			
			Manager.warriors.add(new Warrior(240, 552, 17, 38));
			Manager.warriors.add(new Warrior(310, 552, 17, 38));
			Manager.warriors.add(new Warrior(500, 552, 17, 38));
			Manager.warriors.add(new Warrior(675, 552, 17, 38));
			Manager.warriors.add(new Warrior(555, 552, 17, 38));
			Manager.warriors.add(new Warrior(505, 462, 17, 38));
			Manager.warriors.add(new Warrior(459, 462, 17, 38));
			Manager.warriors.add(new Warrior(440, 382, 17, 38));
			Manager.warriors.add(new Warrior(240, 382, 17, 38));
			Manager.warriors.add(new Warrior(125, 382, 17, 38));
			Manager.warriors.add(new Warrior(60, 382, 17, 38));
			Manager.warriors.add(new Warrior(360, 312, 17, 38));
			Manager.warriors.add(new Warrior(500, 122, 17, 38));
			Manager.archers.add(new Archer(360, 552, 17, 38));
			Manager.archers.add(new Archer(400, 552, 17, 38));
			Manager.archers.add(new Archer(520, 552, 17, 38));
			Manager.archers.add(new Archer(630, 552, 17, 38));
			Manager.archers.add(new Archer(470, 462, 17, 38));
			Manager.archers.add(new Archer(290, 382, 17, 38));
			Manager.archers.add(new Archer(220, 382, 17, 38));
			Manager.archers.add(new Archer(165, 382, 17, 38));
			Manager.archers.add(new Archer(337, 312, 17, 38));
			Manager.archers.add(new Archer(420, 122, 17, 38));
			Manager.archers.add(new Archer(630, 122, 17, 38));
			
			Manager.f = new Flag(690,100,40,60);
			game.play();
		}
		if(currentRoom == 5) {
			Manager.p.x = 35;
			Manager.p.y = 552;
			
			Manager.sPlatforms.add(new SPlatform(0,PixelLegend.HEIGHT - 10,PixelLegend.WIDTH, 10));
			Manager.sPlatforms.add(new SPlatform(0,0,10,PixelLegend.HEIGHT));
			Manager.sPlatforms.add(new SPlatform(PixelLegend.WIDTH - 10,0,10,PixelLegend.HEIGHT));
			
			Manager.boss.add(new The_Inquisitor(600, 100, 30, 56));
			
			Manager.f = new Flag(1000,1000,40,60);
			boss.play();
		}
		
		//System.out.println(currentRoom);
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
				updateRoom();
			}
			else if(currentState == TUTORIAL) {
				tutorialHP = r.nextFloat();
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					tutorialPage--;
					if(tutorialPage == 0) {
						tutorialPage = 3;
					}
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					tutorialPage++;
					if(tutorialPage == 4) {
						tutorialPage = 1;
					}
				}
				else {
					currentState = MAIN_MENU;
				}
			}
			else if(currentState == END_STATE && colorTimer <= 75) {
				currentState = MAIN_MENU;
				Manager.playerDead = false;
				Manager.bossDefeated = false;
				Manager.p = new Player(100, 100, 17, 38);
				colorTimer = 0;
				currentRoom = 0;
				stopAll();
				menu.play();
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
			
			if(e.getKeyCode() == KeyEvent.VK_S && mAttackCooldown == 0) {
				int EXC = 1;
				if(difficulty == MEDIUM) {
					EXC = 2;
				}
				if(difficulty == HARD) {
					EXC = 3;
				}
				if(difficulty == EXPERT) {
					EXC = 4;
				}
				if(difficulty == NIGHTMARE) {
					EXC = 5;
				}
				Manager.p.attack();
				mAttackCooldown = Manager.p.attackCooldown + EXC;
			}
			if(e.getKeyCode() == KeyEvent.VK_D && rAttackCooldown == 0) {
				int EXC = 2;
				if(difficulty == MEDIUM) {
					EXC = 4;
				}
				if(difficulty == HARD) {
					EXC = 6;
				}
				if(difficulty == EXPERT) {
					EXC = 9;
				}
				if(difficulty == NIGHTMARE) {
					EXC = 10;
				}
				Manager.p.shoot();
				rAttackCooldown = Manager.p.attackCooldown + EXC;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_U) {
				//Manager.p.y = 20;
				//Manager.p.yVelocity = 0;
				/*for(int i = 0; i < Manager.playerAttacks.size(); i++) {
					Manager.playerAttacks.get(i).isAlive = false;
				}*/
				//Manager.p.knockback(15, Object.FACING_RIGHT);
				Manager.boss.add(new The_Inquisitor(600, 100, 30, 56));
			}
			
			if(e.getKeyCode() == KeyEvent.VK_K) {
				Manager.p.health = 5000;
				Manager.p.mDmg = 250;
				Manager.p.rDmg = 200;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_L) {
				updateRoom();
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

	static void stopAll() {
		menu.stop();
		game.stop();
		boss.stop();
		win.stop();
		loss.stop();
	}
}

class Song {

	private int duration;
	private String songAddress;
	private AdvancedPlayer mp3Player;
	private InputStream songStream;

	public Song(String songAddress) {
		this.songAddress = songAddress;
	}

	public void play() {
		loadFile();
		if (songStream != null) {
			loadPlayer();
			startSong();
		} else
			System.err.println("Unable to load file: " + songAddress);
	}

	public void setDuration(int seconds) {
		this.duration = seconds * 100;
	}

	public void stop() {
		if (mp3Player != null)
			mp3Player.close();
	}

	private void startSong() {
		Thread t = new Thread() {
			public void run() {
				try {
					if (duration > 0)
						mp3Player.play(duration);
					else
						mp3Player.play();
				} catch (Exception e) {
				}
			}
		};
		t.start();
	}

	private void loadPlayer() {
		try {
			this.mp3Player = new AdvancedPlayer(songStream);
		} catch (Exception e) {
		}
	}

	private void loadFile() {
		if (songAddress.contains("http"))
			this.songStream = loadStreamFromInternet();
		else
			this.songStream = loadStreamFromComputer();
	}

	private InputStream loadStreamFromInternet() {
		try {
			return new URL(songAddress).openStream();
		} catch (Exception e) {
			return null;
		}
	}

	private InputStream loadStreamFromComputer() {
		try {
			return new FileInputStream(songAddress);
		} catch (FileNotFoundException e) {
			return this.getClass().getResourceAsStream(songAddress);
		}
	}
}
