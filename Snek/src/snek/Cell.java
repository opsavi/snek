package snek;

public class Cell {

	private int xPos, yPos;
	
	public Cell(int xPos, int yPos) {
		
		this.xPos = xPos;
		this.yPos = yPos;	
		
	}
	
	public void setXPos(int val) {
		xPos = val;
	}
	
	public void setYPos(int val) {
		yPos = val;
	}
	
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	
}
