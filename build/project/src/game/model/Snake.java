package game.model;

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
		this.direction = direction;
	}
	
}
