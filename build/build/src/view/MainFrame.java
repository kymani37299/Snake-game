package view;

import game.listeners.KeyListener;
import game.model.Game;
import game.model.Position;
import game.view.GameView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Stage{

	private static MainFrame instance;
	private BorderPane layout;
	
	private MainFrame(){
		this.layout = new BorderPane();
		Game game = new Game(new Position(20,20));
		this.layout.setCenter(new GameView(game));
		Scene sc = new Scene(layout);
		sc.setOnKeyPressed(new KeyListener(game.getSnake()));
		this.setScene(sc);
		this.setTitle("Snake game");
	}
	
	public static MainFrame getInstance(){
		if(instance==null){
			instance = new MainFrame();
		}
		return instance;
	}
}
