package Game2048.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final int SLIDE_SPEED = 30;
	public static final int ARC_WIDTH = 15;
	public static final int ARC_HEIGHT =15;

	private int value;
	private BufferedImage tileImage;
	private Color background;
	private Color text;
	private Font font;
	private Point slideTo;
	private int x;
	private int y;

	private boolean beginningAnimation = true;
	private double scaleFirst = 0.1;
	private BufferedImage beginningImage;
	
	private boolean combineAnimation = false;
	private double scaleCombine = 1.2;
	private BufferedImage combineImage;
	private boolean canCombine = true;

	public Tile(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		slideTo = new Point(x, y);
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		beginningImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		combineImage = new BufferedImage(WIDTH * 2, HEIGHT * 2, BufferedImage.TYPE_INT_ARGB);
		drawImage();
	}

	public void update() {
		if (beginningAnimation) {
			AffineTransform transform = new AffineTransform();
			transform.translate(WIDTH / 2 - scaleFirst * WIDTH / 2, HEIGHT / 2 - scaleFirst * HEIGHT / 2);
			transform.scale(scaleFirst, scaleFirst);
			Graphics2D g2d = (Graphics2D) beginningImage.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2d.setColor(new Color(0, 0, 0, 0));
			g2d.fillRect(0, 0, WIDTH, HEIGHT);
			g2d.drawImage(tileImage, transform, null);
			scaleFirst += 0.1;
			g2d.dispose();
			if(scaleFirst >= 1) beginningAnimation = false; 
		}
		else if(combineAnimation){
			AffineTransform transform = new AffineTransform();
			transform.translate(WIDTH / 2 - scaleCombine * WIDTH / 2, HEIGHT / 2 - scaleCombine * HEIGHT / 2);
			transform.scale(scaleCombine, scaleCombine);
			Graphics2D g2d = (Graphics2D) combineImage.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2d.setColor(new Color(0, 0, 0, 0));
			g2d.fillRect(0, 0, WIDTH, HEIGHT);
			g2d.drawImage(tileImage, transform, null);
			scaleCombine -= 0.08;
			g2d.dispose();
			if(scaleCombine <= 1) combineAnimation = false;
		}
	}
	
	public void render(Graphics2D g){
		if(beginningAnimation){
			g.drawImage(beginningImage, x, y, null);
		}
		else if(combineAnimation){
			g.drawImage(combineImage, (int)(x + WIDTH / 2 - scaleCombine * WIDTH / 2), 
													(int)(y + HEIGHT / 2 - scaleCombine * HEIGHT / 2), null);
		}
		else{
			g.drawImage(tileImage, x, y, null);
		}
	}
	
	private void drawImage() {
		Graphics2D g = (Graphics2D) tileImage.getGraphics();
		if (value == 2) {
			background = new Color(0xe9e9e9);
			text = new Color(0x000000);
		}
		else if (value == 4) {
			background = new Color(0xe6daab);
			text = new Color(0x000000);
		}
		else if (value == 8) {
			background = new Color(0xf79d3d);
			text = new Color(0xffffff);
		}
		else if (value == 16) {
			background = new Color(0xf28007);
			text = new Color(0xffffff);
		}
		else if (value == 32) {
			background = new Color(0xf55e3b);
			text = new Color(0xffffff);
		}
		else if (value == 64) {
			background = new Color(0xff0000);
			text = new Color(0xffffff);
		}
		else if (value == 128) {
			background = new Color(0xe9de84);
			text = new Color(0xffffff);
		}
		else if (value == 256) {
			background = new Color(0xf6e873);
			text = new Color(0xffffff);
		}
		else if (value == 512) {
			background = new Color(0xf5e455);
			text = new Color(0xffffff);
		}
		else if (value == 1024) {
			background = new Color(0xf7e12c);
			text = new Color(0xffffff);
		}
		else if (value == 2048) {
			background = new Color(0xffe400);
			text = new Color(0xffffff);
		}
		else if(value == 0){
			background = Color.lightGray;
			text = Color.black;
		}
		else{
			background = new Color(0x000000);
			text = new Color(0xffffff);
		}
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(background);
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);

		g.setColor(text);

		if (value <= 64) {
			font = Game.main.deriveFont(36f);
			g.setFont(font);
		}
		else {
			font = Game.main;
			g.setFont(font);
		}

		int drawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + value, font, g) / 2;
		int drawY = HEIGHT / 2 + DrawUtils.getMessageHeight("" + value, font, g) / 2;
		g.drawString("" + value, drawX, drawY);
		g.dispose();
	}
	
	public void print(){
		try {
			ImageIO.write(tileImage, "gif", new File("C:/Users/ToaiHuynh/Desktop/" + value + ".gif"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to export the image.");
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		drawImage();
	}

	public Point getSlideTo() {
		return slideTo;
	}

	public void setSlideTo(Point slideTo) {
		this.slideTo = slideTo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setCombineAnimation(boolean combineAnimation){
		this.combineAnimation = combineAnimation;
		if(combineAnimation) scaleCombine = 1.2;
	}
	
	public boolean isCombineAnimation(){
		return combineAnimation;
	}

	public boolean canCombine() {
		return canCombine;
	}

	public void setCanCombine(boolean canCombine) {
		this.canCombine = canCombine;
	}
}
