package view;

import bot.Bot;
import game.listeners.KeyListener;
import game.model.Game;
import game.model.GameMode;
import game.model.Position;
import game.view.GameView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Stage{

	private static MainFrame instance;
	private BorderPane layout;
	
	private MainFrame(){
		this.layout = new BorderPane();
		this.layout.setCenter(new NewGameDialog());
		this.layout.setPadding(new Insets(15));
		this.setScene(new Scene(layout));
		this.setTitle("Snake game");
	}
	
	public void playGame(GameMode mode){
		Game game = new Game(new Position(20,20),mode);
		this.layout.setCenter(new GameView(game));
		this.layout.setPadding(new Insets(0));
		this.sizeToScene();
		if(mode == GameMode.Play){
			this.getScene().setOnKeyPressed(new KeyListener(game.getSnake()));
		}else if(mode==GameMode.Bot){
			game.setController(new Bot());
		}
	}
	
	public static MainFrame getInstance(){
		if(instance==null){
			instance = new MainFrame();
		}
		return instance;
	}
}
