package JumpDemo;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.Timer;


	public class JumperDemo extends JPanel implements ActionListener, KeyListener{
		public static final int WIDTH = 800;
		public static final int HEIGHT = 600;
		
		JFrame window;
		Timer timer;
		
		Player p1 = new Player(50, 50, 100, 100);
		
		public static void main(String[] args) {
			new JumperDemo().run();
		}
		
		public void run(){
			window = new JFrame("JUMPER!!");
			window.addKeyListener(this);
			window.add(this);
			window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
			window.pack();
			timer = new Timer(1000 / 60, this);
			timer.start();
			
		}
		
		public void paintComponent(Graphics g){
			p1.draw(g);
		}
		
		public void actionPerformed(ActionEvent e){
			p1.update();
			repaint();
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				p1.left = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				p1.right = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				p1.jump();
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				p1.left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				p1.right = false;
			}
			
		}
	}

	class Player{
		private int x;
		private int y;
		private int width;
		private int height;
		
		public boolean left = false;
		public boolean right = false;
		
		private int xVelocity = 5;
		
		int gravity = 1;
		int yVelocty = 0;
		int jumpPower = 20;
		
		int yLimit = 500;

		boolean canJump = false;
		
		public Player(int x, int y, int w, int h){
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		
		public void jump(){
			if(canJump){
				yVelocty -= jumpPower;
				canJump = false;
			}
		}
		
		public void update(){
			if(left){
				x -= xVelocity;
			}
			if(right){
				x += xVelocity;
			}
			
			yVelocty += gravity;
			y += yVelocty;
			
			if(y >= yLimit){
				y = yLimit;
				yVelocty = 0;
				canJump = true;
			}
			
		}
		
		public void draw(Graphics g){
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

