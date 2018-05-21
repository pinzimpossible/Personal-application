package Game2048.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game2048.game.DrawUtils;
import Game2048.game.Game;

public class MainMenuPanel extends GuiPanel {

	private Font titleFont = Game.main.deriveFont(110f);
	private Font creatorFont = Game.main.deriveFont(24f);
	private String title = "2048";
	private String creator = "By Toai Huynh T152400";
	private int buttonWidth = 220;
	
	public MainMenuPanel() {
		super();
		GuiButton playButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, 220, buttonWidth, 60);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Play");
			}
		});
		playButton.setText("Play");
		add(playButton);
		
		GuiButton scoresButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, 310, buttonWidth, 60);
		scoresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Leaderboards");
			}
		});
		scoresButton.setText("Scores");
		add(scoresButton);
		
		GuiButton quitButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, 400, buttonWidth, 60);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setText("Quit");
		add(quitButton);
	}

	@Override
	public void render(Graphics2D g){
		super.render(g);
		g.setFont(titleFont);
		g.setColor(Color.PINK);
		g.drawString(title, Game.WIDTH / 2 - DrawUtils.getMessageWidth(title, titleFont, g) / 2, 150);
		g.setFont(creatorFont);
		g.drawString(creator, 20, Game.HEIGHT - 10);
	}
}
