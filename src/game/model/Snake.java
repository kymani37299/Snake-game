package game.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Snake {

	private Deque<Piece> snake;
	private Direction direction;
	private Direction definiteDirection;
	private boolean alive;
	private Game game;
	
	
	public Snake(Position position,Game game){
		this.snake = new LinkedList<>();
		this.snake.add(new Piece(position));
		this.direction = Direction.RIGHT;
		this.snake.add(new Piece(position));
		this.alive = true;
		this.game = game;
	}
	
	public void grow(){
		this.snake.addFirst(new Piece(this.getPosition()));
	}
	
	public Piece move(){
		this.snake.addFirst(new Piece(this.direction,this.getPosition(),this.game));
		Piece last = this.snake.removeLast();
		for(Piece p : snake){
			if(p!=this.snake.getFirst() && p.getPosition().equals(this.getPosition())){
				this.alive = false;
				break;
			}
		}
		this.definiteDirection = this.direction;
		return last;
	}
	
	public ArrayList<Position> getSnake(){
		ArrayList<Position> wholeSnake = new ArrayList<Position>();
		for(Piece p : this.snake){
			wholeSnake.add(p.getPosition());
		}
		return wholeSnake;
	}
	
	public Position getPosition(){
		return this.snake.getFirst().getPosition();
	}

	public Direction getDirection() {
		return definiteDirection;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setDirection(Direction direction) {
		if(direction == Direction.UP && this.direction == Direction.DOWN) return;
		if(direction == Direction.DOWN && this.direction == Direction.UP) return;
		if(direction == Direction.LEFT && this.direction == Direction.RIGHT) return;
		if(direction == Direction.RIGHT && this.direction == Direction.LEFT) return;
		this.direction = direction;
	}
	
}
