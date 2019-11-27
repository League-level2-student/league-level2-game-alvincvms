package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class PixelLegend {
	
	JFrame window;
	Game game;
	final static int WIDTH = 750;
	final static int HEIGHT = 600;
	
	
	
	PixelLegend(){
		window = new JFrame("Pixel Legends (Beta)");
		game = new Game();
	}
	
	public static void main(String[] args) {
		new PixelLegend().run();
	}
	
	void run() {
		window.add(game);
		window.addKeyListener(game);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
		game.start();
	}
}
