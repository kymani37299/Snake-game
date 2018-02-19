package game;

import bot.input.FourWayDistanceInputGenerator;
import bot.input.InputGenerator;
import game.model.Position;
import javafx.scene.paint.Color;

public class GlobalSettings {

	public static GlobalSettings instance;
	
	private Position mapSize;
	private int FPS;
	private InputGenerator inputGenerator;
	
	//Colors
	private Color backgroundColor;
	private Color snakeColor;
	private Color appleColor;
	
	private GlobalSettings(){
		this.mapSize = new Position(20, 20);
		this.FPS = 20;
		this.inputGenerator = new FourWayDistanceInputGenerator();
		this.backgroundColor = Color.BLACK;
		this.snakeColor = Color.GREEN;
		this.appleColor = Color.RED;
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

	public InputGenerator getInputGenerator() {
		return inputGenerator;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getSnakeColor() {
		return snakeColor;
	}

	public void setSnakeColor(Color snakeColor) {
		this.snakeColor = snakeColor;
	}

	public Color getAppleColor() {
		return appleColor;
	}

	public void setAppleColor(Color appleColor) {
		this.appleColor = appleColor;
	}

	public void setInputGenerator(InputGenerator inputGenerator) {
		this.inputGenerator = inputGenerator;
	}

	public static GlobalSettings getInstance(){
		if(instance==null){
			instance = new GlobalSettings();
		}
		return instance;
	}
	
}
