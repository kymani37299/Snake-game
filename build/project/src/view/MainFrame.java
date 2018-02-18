package view;

import bot.Bot;
import game.controller.GameController;
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
	private Position mapSize = new Position(20,20);
	private int FPS = 20;
	private BorderPane layout;
	private GameController bot;
	
	private MainFrame(){
		this.layout = new BorderPane();
		this.layout.setCenter(new NewGameDialog());
		this.layout.setPadding(new Insets(15));
		this.setScene(new Scene(layout));
		this.setTitle("Snake game");
	}
	
	public void playGame(GameMode mode){
		if(mode != GameMode.Train){
			Game game = new Game(mapSize,mode);
			this.layout.setCenter(new GameView(game));
			this.layout.setPadding(new Insets(0));
			this.sizeToScene();
			if(mode == GameMode.Play){
				this.getScene().setOnKeyPressed(new KeyListener(game.getSnake()));
			}else if(mode==GameMode.Bot){
				if(this.bot == null){ // TODO: Load from file best snake ever
					game.setController(new Bot(null,game));
				}else{
					this.bot.setGame(game);
					game.setController(this.bot);
				}
			}
			game.startGame();
		}else{
			this.layout.setCenter(new TrainingSettingsDialog());
			this.sizeToScene();
		}
	}
	
	public void setBot(GameController bot){
		this.bot = bot;
	}
	
	public Position getMapSize() {
		return mapSize;
	}

	public void setMapSize(Position mapSize) {
		this.mapSize = mapSize;
	}
	
	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public static MainFrame getInstance(){
		if(instance==null){
			instance = new MainFrame();
		}
		return instance;
	}
}
