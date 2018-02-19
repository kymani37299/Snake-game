package controller;

import bot.Bot;
import bot.learning.GeneticAlg;
import game.GlobalSettings;
import game.model.Game;
import game.model.GameMode;
import game.model.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import neuralNetwork.NeuralNetwork;
import view.MainFrame;
import view.TrainingWindow;

public class TrainingWindowController implements EventHandler<ActionEvent>{

	private TrainingWindow trainingWindow;
	
	public TrainingWindowController(TrainingWindow trainingWindow){
		this.trainingWindow = trainingWindow;
	}

	@Override
	public void handle(ActionEvent event) {
		int numGenerations = -1;
		int mapHeight = -1;
		int mapWidth = -1;
		int FPS = -1;
		try{
			numGenerations = Integer.parseInt(this.trainingWindow.getTfSkipNumber().getText());
		}catch(NumberFormatException e){
		}
		
		try{
			mapHeight = Integer.parseInt(this.trainingWindow.getTfMapHeight().getText());
			mapWidth = Integer.parseInt(this.trainingWindow.getTfMapWidth().getText());
		}catch(NumberFormatException e){
		}
		
		try{			
			FPS = Integer.parseInt(this.trainingWindow.getTfFPS().getText());
		}catch(NumberFormatException e){
		}
				
		if(mapHeight>4 && mapWidth>4){
			GlobalSettings.getInstance().setMapSize(new Position(mapHeight,mapWidth));
			Game newGame = new Game(GameMode.Simulation);
			Bot bot = new Bot(new NeuralNetwork(this.trainingWindow.getGa().getBestDNA()),newGame);
			newGame.setController(bot);
			MainFrame.getInstance().playGame(newGame);
		}
		
		if(FPS>0){
			GlobalSettings.getInstance().setFPS(FPS);
		}
		
		if(numGenerations > 0){
			GeneticAlg ga = this.trainingWindow.getGa();
			ga.setGoalGeneration(numGenerations + ga.getGeneration());
			new Thread(ga).start();
		}
	}
	
}
