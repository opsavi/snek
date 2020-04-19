package snek;

import java.util.ArrayList;
import java.util.List;

public class Snek {

	private List<Cell> body;
	private int xDir, yDir;
	private boolean readyToMove;
	Cell head;

	public Snek(int startX, int startY) {
		body = new ArrayList<Cell>();
		head = new Cell(startX, startY);
		body.add(head);
		xDir = 0;
		yDir = 0;
		readyToMove = true;
	}

	public void increaseLength(int xPos, int yPos) {

		
		Cell newCell = new Cell(xPos, yPos);
		body.add(newCell);
		
	}

	public void move() {
		
		int cellX = head.getXPos();
		int cellY = head.getYPos();
		int nextHeadX = cellX + 1 * xDir;
		int nextHeadY = cellY + 1 * yDir;
		head.setXPos(nextHeadX);
		head.setYPos(nextHeadY);

		for(int i=1; i<body.size(); i++) {
			Cell currentCell = body.get(i);
			int nextX = currentCell.getXPos();
			int nextY = currentCell.getYPos();
			currentCell.setXPos(cellX);
			currentCell.setYPos(cellY);
			cellX = nextX;
			cellY = nextY;
		}

	}

	public void changeDirToRight() {

		if (xDir != -1 && readyToMove) {
			setXDir(1);
			setYDir(0);
			setReadyToMove(false);
		}

	}

	public void changeDirToLeft() {

		if (xDir != 1 && readyToMove) {
			setXDir(-1);
			setYDir(0);
			setReadyToMove(false);
		}
	}

	public void changeDirToUp() {

		if (yDir != 1 && readyToMove) {
			setXDir(0);
			setYDir(-1);
			setReadyToMove(false);
		}

	}

	public void changeDirToDown() {

		if (yDir != -1 && readyToMove) {
			setXDir(0);
			setYDir(1);
			setReadyToMove(false);
		}

	}

	public int getHeadXPos() {
		return head.getXPos();
	}

	public int getHeadYPos() {
		return head.getYPos();
	}
	
	public int getLastCellXPos() {
		
		return body.get(body.size()-1).getXPos();
		
	}
	
	public int getLastCellYPos() {
		
		return body.get(body.size()-1).getYPos();
	}

	public boolean getReadyToMove() {
		return readyToMove;
	}

	public void setReadyToMove(boolean choice) {
		readyToMove = choice;
	}

	public List<Cell> getBody() {
		return body;
	}

	public void setXDir(int val) {
		xDir = val;
	}

	public void setYDir(int val) {
		yDir = val;
	}

	public int getXDir() {
		return xDir;
	}

	public int getYDir() {
		return yDir;
	}

}
