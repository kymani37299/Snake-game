package game.controller;

import game.model.Direction;
import game.model.Game;

public interface GameController {
	public Direction getAction();
	public void setGame(Game game);
}
