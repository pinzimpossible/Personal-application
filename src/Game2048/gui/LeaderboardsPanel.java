package Game2048.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Game2048.game.DrawUtils;
import Game2048.game.Game;
import Game2048.game.Leaderboards;

public class LeaderboardsPanel extends GuiPanel{

	private Leaderboards lBoard;
	private int buttonWidth = 100;
	private int backButtonWidth = 220;
	private int buttonSpacing = 20;
	private int buttonY = 120;
	private int buttonHeight = 50;
	private int leaderboardsX = 130;
	private int leaderboardsY = buttonY + buttonHeight + 90;
	
	private String title = "Leaderboards";
	private Font titleFont = Game.main.deriveFont(48f);
	private Font scoreFont = Game.main.deriveFont(30f);
	private State currentState = State.SCORE;
	
	public LeaderboardsPanel(){
		super();
		lBoard = Leaderboards.getInstance();
		lBoard.loadScores();

		GuiButton tileButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, buttonY, buttonWidth, buttonHeight);
		tileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentState = State.TILE;
			}
		});
		tileButton.setText("Tiles");
		add(tileButton);
		
		GuiButton scoreButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2 - tileButton.getWidth() - buttonSpacing, buttonY, buttonWidth, buttonHeight);
		scoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentState = State.SCORE;
			}
		});
		scoreButton.setText("Scores");
		add(scoreButton);
		
		GuiButton timeButton = new GuiButton(Game.WIDTH / 2 + buttonWidth / 2 + buttonSpacing, buttonY, buttonWidth, buttonHeight);
		timeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentState = State.TIME;
			}
		});
		timeButton.setText("Times");
		add(timeButton);
		
		GuiButton backButton = new GuiButton(Game.WIDTH / 2 - backButtonWidth / 2, 500, backButtonWidth, 60);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Menu");
			}
		});
		backButton.setText("Back");
		add(backButton);
	}
	
	private void drawLeaderboards(Graphics2D g){
		ArrayList<String> strings = new ArrayList<String>();
		if(currentState == State.SCORE){
			strings = convertToStrings(lBoard.getTopScores());
		}
		else if(currentState == State.TILE){
			strings = convertToStrings(lBoard.getTopTiles());
		}
		else {
			for(Long l : lBoard.getTopTimes()){
				strings.add(DrawUtils.formatTime(l));
			}
		}
		
		g.setColor(Color.black);
		g.setFont(scoreFont);
		
		for(int i = 0; i < strings.size(); i++){
			String s = (i + 1) + ". " + strings.get(i);
			g.drawString(s, leaderboardsX, leaderboardsY + i * 40);
		}
	}
	
	private ArrayList<String> convertToStrings(ArrayList<? extends Number> list){
		ArrayList<String> ret = new ArrayList<String>();
		for(Number n : list){
			ret.add(n.toString());
		}
		return ret;
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public void render(Graphics2D g){
		super.render(g);
		g.setColor(Color.black);
		g.drawString(title, Game.WIDTH / 2 - DrawUtils.getMessageWidth(title, titleFont, g) / 2, DrawUtils.getMessageHeight(title, titleFont, g) + 40);
		drawLeaderboards(g);
	}
	
	private enum State{
		SCORE,
		TILE,
		TIME
	}
}
