package controller;

import bot.Bot;
import bot.learning.GeneticAlg;
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
			MainFrame.getInstance().setMapSize(new Position(mapHeight,mapWidth));
			MainFrame.getInstance().setBot(new Bot(new NeuralNetwork(this.trainingWindow.getGa().getBestDNA())));
			MainFrame.getInstance().playGame(GameMode.Bot);
		}
		
		if(FPS>0){
			MainFrame.getInstance().setFPS(FPS);
		}
		
		if(numGenerations > 0){
			GeneticAlg ga = this.trainingWindow.getGa();
			ga.setGoalGeneration(numGenerations + ga.getGeneration());
			new Thread(ga).start();
		}
	}
	
}
