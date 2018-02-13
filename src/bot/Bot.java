package bot;

import java.util.Random;

import game.controller.GameController;
import game.model.Direction;

public class Bot implements GameController{

	//TODO
	
	@Override
	public Direction getAction() {
		Random r = new Random();
		int a = r.nextInt(4);
		if(a==0) return Direction.UP;
		else if(a==1) return Direction.DOWN;
		else if(a==2) return Direction.LEFT;
		else return Direction.RIGHT;
	}

}
