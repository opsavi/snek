package snek;

import javax.swing.JPanel;

public class GameBoard extends JPanel {
	
	final int HEIGHT, WIDTH;
	
	
	public GameBoard(int height, int width) {
		
		HEIGHT = height;
		WIDTH = width;
	}
	
	public boolean areCoordinatesInRange(int xPos, int yPos) {
		
		if(xPos>=0 && xPos < WIDTH && yPos >= 0 && yPos < HEIGHT) {
			return true;
		}
			
		return false;
		
	}
	
	

}
