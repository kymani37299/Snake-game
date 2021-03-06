package game.view;

import java.util.Observable;
import java.util.Observer;

import game.GlobalSettings;
import game.model.Apple;
import game.model.Game;
import game.model.Piece;
import game.model.Position;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameView extends GridPane implements Observer{
	
	private final double squareSize = 15;
	private Rectangle grid[][];
	private Game game;
	
	public GameView(Game game){
		game.addObserver(this);
		this.game = game;
		this.grid = new Rectangle[game.getDimension().getX()][game.getDimension().getY()];
		for(int i=0;i<game.getDimension().getX();i++){
			for(int j=0;j<game.getDimension().getY();j++){
				grid[i][j] = new Rectangle(squareSize, squareSize);
				Position pos = new Position(i,j);
				if(game.getApple().getPosition().equals(pos)){
					grid[i][j].setFill(GlobalSettings.getInstance().getAppleColor());
				}else{
					grid[i][j].setFill(GlobalSettings.getInstance().getBackgroundColor());
				}
				this.add(grid[i][j], i, j);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Apple){
			Apple apple = (Apple)arg;
			Position head = game.getSnake().getPosition();
			grid[head.getX()][head.getY()].setFill(GlobalSettings.getInstance().getSnakeColor());
			grid[apple.getPosition().getX()][apple.getPosition().getY()].setFill(GlobalSettings.getInstance().getAppleColor());
		}else{
			Position pos = ((Piece)arg).getPosition();
			Position head = game.getSnake().getPosition();
			if(!head.equals(pos)){
				grid[head.getX()][head.getY()].setFill(GlobalSettings.getInstance().getSnakeColor());
				grid[pos.getX()][pos.getY()].setFill(GlobalSettings.getInstance().getBackgroundColor());
			}
		}
	}
	
}
