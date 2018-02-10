package game.listeners;

import game.model.Direction;
import game.model.Snake;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent>{
	
	private Snake snake;
	
	public KeyListener(Snake snake){
		this.snake = snake;
	}

	@Override
	public void handle(KeyEvent event) {
		switch(event.getCode()){
		case UP:
			if(this.snake.getDirection() != Direction.DOWN)
				this.snake.setDirection(Direction.UP);
			break;
		case DOWN:
			if(this.snake.getDirection() != Direction.UP)
				this.snake.setDirection(Direction.DOWN);
			break;
		case LEFT:
			if(this.snake.getDirection() != Direction.RIGHT)
				this.snake.setDirection(Direction.LEFT);
			break;
		case RIGHT:
			if(this.snake.getDirection() != Direction.LEFT)
				this.snake.setDirection(Direction.RIGHT);
			break;
		default:
			break;
		}
	}
}
