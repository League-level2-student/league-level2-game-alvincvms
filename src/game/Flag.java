package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Flag extends Object{
	
	public static BufferedImage flag;
	
	Flag(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			flag = ImageIO.read(this.getClass().getResourceAsStream("Flag.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		g.drawImage(flag, x-5, y-16, width+10, height+16, null);
	}
}
