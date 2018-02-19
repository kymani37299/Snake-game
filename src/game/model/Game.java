package game.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import game.GlobalSettings;
import game.controller.GameController;

public class Game extends Observable implements Runnable{
	private final int maxFailCount = 100;
	
	private Snake snake;
	private Apple apple;
	private Position dimension;
	private boolean gameActive;
	private Random rand = new Random();
	private GameController controller;
	private GameMode mode;
	private ArrayList<Apple> apples;
	
	public Game(GameMode mode){
		this.mode = mode;
		this.dimension = GlobalSettings.getInstance().getMapSize();
		this.gameActive = true;
		this.snake = new Snake(new Position(rand.nextInt(this.dimension.getX()),rand.nextInt(this.dimension.getY())),this);
		this.apple = this.getRandomApple();
		this.apples = new ArrayList<>();
		this.apples.add(this.apple);
	}
	
	private void updateGame(){
		if(this.snake.getPosition().equals(this.apple.getPosition())){
			this.snake.grow();
			this.apple = this.getRandomApple();
			this.apples.add(this.apple);
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
	
	private Apple getRandomApple(){
		ArrayList<Position> space = new ArrayList<Position>();
		for(int i=0;i<this.dimension.getX();i++){
			for(int j=0;j<this.dimension.getY();j++){
				space.add(new Position(i,j));
			}
		}
		for(Position p : this.snake.getSnake()){
			space.remove(p);
		}
		return new Apple(space.get(rand.nextInt(space.size())));
	}
	
	private void pauseGame(){
		try {
			Thread.sleep(1000/GlobalSettings.getInstance().getFPS());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean inBounds(){
		int h = this.dimension.getX();
		int w = this.dimension.getY();
		int x = this.snake.getPosition().getX();
		int y = this.snake.getPosition().getY();
		return x < w && y < h && x >= 0 && y >= 0;
	}
	
	public void startGame(){
		new Thread(this).start();
	}
	
	public void simulateGame(){
		int numApples = 1;
		int failCount = 0;
		while(this.gameActive && failCount < this.maxFailCount){
			this.snake.setDirection(this.controller.getAction());
			this.updateGame();
			if(numApples < this.apples.size()){
				numApples++;
				failCount = 0;
			}else{
				failCount++;
			}
		}
		this.gameActive = false;
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
	
	public ArrayList<Apple> getApples() {
		return apples;
	}
	
	public GameMode getMode(){
		return this.mode;
	}
	
	@Override
	public void run() {
		while(gameActive){
			if(this.controller!=null){
				this.snake.setDirection(this.controller.getAction());
			}
			this.updateGame();
			this.pauseGame();
		}
	}
	
	
	
}
