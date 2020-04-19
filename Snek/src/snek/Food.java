package snek;

public class Food {
	
	private int xPos, yPos;
	
	public Food(int randX, int randY) {
		
		xPos = randX;
		yPos = randY;
		
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}

}
