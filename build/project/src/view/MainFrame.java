package view;

import game.listeners.KeyListener;
import game.model.Game;
import game.model.GameMode;
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
	
	public void playGame(Game game){
		this.layout.setCenter(new GameView(game));
		this.layout.setPadding(new Insets(0));
		if(game.getMode()==GameMode.Play)
			this.getScene().setOnKeyPressed(new KeyListener(game.getSnake()));
		this.sizeToScene();
		game.startGame();
	}
	
	public void openTrainSettings(){
		this.layout.setCenter(new TrainingSettingsDialog());
		this.sizeToScene();
	}

	public static MainFrame getInstance(){
		if(instance==null){
			instance = new MainFrame();
		}
		return instance;
	}
}
