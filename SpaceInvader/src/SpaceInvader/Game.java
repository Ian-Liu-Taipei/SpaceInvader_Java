package SpaceInvader;

import javax.swing.JFrame;

import SpaceInvader.GamePanel;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game01");
		frame.setSize(700, 700);
		frame.setLocation(100,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel p = new GamePanel();
		frame.setVisible(true);
		frame.setContentPane(p);
        p.requestFocus();
	}

}
