package snek;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;
import java.awt.Font;

public class Main extends JFrame implements KeyListener {

	final int windowX = 1000;
	final int windowY = 600;
	final int tileSize = 15;

	Font font = new Font("Arial", Font.BOLD, 20);

	int width = (windowX - 300) / tileSize;
	int height = windowY / tileSize;

	GameBoard gb = new GameBoard(height, width);
	Snek snek = new Snek(width / 2, height / 2);

	boolean keepRunning = true;
	boolean collisionDetected = false;
	boolean increaseSnekLength = false;

	Random rand = new Random();
	Food food = spawnFood();
	
	int score = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main main = new Main();
		main.initializeGame();
		main.repaint();
		main.runSnek();
	}

	public void initializeGame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(windowX, windowY);
		this.setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		System.out.println(width);

	}

	public void runSnek() {

		while (keepRunning) {

			int lastX = snek.getLastCellXPos();
			int lastY = snek.getLastCellYPos();

			snek.setReadyToMove(true);

			if (checkIfSnekCanEatFood()) {
				score++;
				food = spawnFood();
				increaseSnekLength = true;
			}

			snek.move();

			if (increaseSnekLength) {

				snek.increaseLength(lastX, lastY);
				increaseSnekLength = false;
			}

			if (!gb.areCoordinatesInRange(snek.getHeadXPos(), snek.getHeadYPos()) || checkIfSnakeHitsBody()) {

				keepRunning = false;
				collisionDetected = true;

			}

			repaint();

			try {
				Thread.sleep(calculateTime(score));
			} catch (InterruptedException ex) {

			}

		}

	}

	public void paint(Graphics g) {

		if (keepRunning) {
			
			g.setColor(Color.WHITE);
			g.fillRect(width, 0, windowX, windowY);

			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString("Score: " + score, (windowX - 280), 50);

			//g.fillRect(0, 0, gb.WIDTH * tileSize, gb.HEIGHT * tileSize);
			g.fillRect(0, 0, gb.WIDTH * tileSize, gb.HEIGHT * tileSize);

			g.setColor(Color.RED);

			g.fillRect(food.getXPos() * tileSize, food.getYPos() * tileSize, tileSize, tileSize);

			g.setColor(Color.GREEN);

			for (Cell cell : snek.getBody()) {
				g.fillRect(cell.getXPos() * tileSize, cell.getYPos() * tileSize, tileSize, tileSize);
			}

			g.setColor(Color.BLACK);

			for (int i = 0; i <= gb.WIDTH; i++) {

				g.drawLine(i * tileSize, 0, i * tileSize, gb.HEIGHT * tileSize);

			}

			for (int i = 0; i <= gb.HEIGHT; i++) {
				g.drawLine(0, i * tileSize, gb.WIDTH * tileSize, i * tileSize);
			}
		}

		if (collisionDetected) {
			g.fillRect(0, 0, gb.WIDTH, gb.HEIGHT);
		}

	}

	public boolean checkIfSnekCanEatFood() {

		int snekX = snek.getHeadXPos();
		int snekY = snek.getHeadYPos();
		int foodX = food.getXPos();
		int foodY = food.getYPos();

		if (snekX == foodX && snekY == foodY) {
			return true;
		}
		return false;
	}

	public boolean checkIfSnakeHitsBody() {

		int headX = snek.getHeadXPos();
		int headY = snek.getHeadYPos();

		for (int i = 1; i < snek.getBody().size(); i++) {

			int cellX = snek.getBody().get(i).getXPos();
			int cellY = snek.getBody().get(i).getYPos();

			if (headX == cellX && headY == cellY) {
				return true;
			}

		}

		return false;

	}

	public Food spawnFood() {

		int randX = 0;
		int randY = 0;
		boolean cellIsTaken = false;

		do {

			cellIsTaken = false;
			randX = rand.nextInt(gb.WIDTH);
			randY = rand.nextInt(gb.HEIGHT);

			for (Cell cell : snek.getBody()) {

				int cellX = cell.getXPos();
				int cellY = cell.getYPos();

				if (randX == cellX && randY == cellY) {
					cellIsTaken = true;

				}
			}

		} while (cellIsTaken);

		Food spawnedFood = new Food(randX, randY);
		return spawnedFood;

	}

	public long calculateTime(int score) {
		
		return (long)(100/Math.pow(1.015, score));
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snek.changeDirToUp();
			break;

		case KeyEvent.VK_DOWN:
			snek.changeDirToDown();
			break;

		case KeyEvent.VK_RIGHT:
			snek.changeDirToRight();
			break;

		case KeyEvent.VK_LEFT:
			snek.changeDirToLeft();
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
