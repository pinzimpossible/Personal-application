package Game2048.game;

import javax.swing.JFrame;

public class Start {

	public static void main(String[] args){
		Game game = new Game();
		
		JFrame window = new JFrame("Game 2048 - T152400 - Huynh Thanh Toai");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		game.start();
	}
}