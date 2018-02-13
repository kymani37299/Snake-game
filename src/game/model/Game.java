package game.model;

import java.util.Observable;
import java.util.Random;

import game.controller.GameController;

public class Game extends Observable implements Runnable{

	private final int FPS = 10;
	private Snake snake;
	private Apple apple;
	private Position dimension;
	private boolean gameActive;
	private Random rand = new Random();
	private GameController controller;
	private GameMode mode;
	
	public Game(Position dimension,GameMode mode){
		this.mode = mode;
		this.dimension = dimension;
		this.gameActive = true;
		this.snake = new Snake(new Position(10,10),this);
		this.apple = new Apple(new Position(rand.nextInt(this.dimension.getX()),rand.nextInt(this.dimension.getY())));
		new Thread(this).start();
	}
	
	private void updateGame(){
		if(this.snake.getPosition().equals(this.apple.getPosition())){
			this.snake.grow();
			this.apple = new Apple(new Position(rand.nextInt(this.dimension.getX()),rand.nextInt(this.dimension.getY())));
			this.notifyObservers(this.apple);
		}else{
			Piece p = this.snake.move();
			if(!this.inBounds() || !this.snake.isAlive()){
				this.gameActive = false;
				return;
			}
			this.notifyObservers(p);
		}
		this.setChanged();
	}
	
	private boolean inBounds(){
		int h = this.dimension.getX();
		int w = this.dimension.getY();
		int x = this.snake.getPosition().getX();
		int y = this.snake.getPosition().getY();
		return x < w && y < h && x >= 0 && y >= 0;
	}

	public Snake getSnake() {
		return this.snake;
	}
	
	public Apple getApple(){
		return this.apple;
	}

	public Position getDimension() {
		return this.dimension;
	}

	public void setController(GameController controller){
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while(gameActive){
			if(this.controller!=null){
				this.snake.setDirection(this.controller.getAction());
			}
			this.updateGame();
			try {
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
