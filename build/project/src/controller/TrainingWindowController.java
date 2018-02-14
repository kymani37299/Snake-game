package controller;

import bot.Bot;
import bot.learning.GeneticAlg;
import game.model.GameMode;
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
		try{
			numGenerations = Integer.parseInt(this.trainingWindow.getTfSkipNumber().getText());
		}catch(NumberFormatException e){
		}
		
		if(numGenerations > 0){
			GeneticAlg ga = this.trainingWindow.getGa();
			for(int i=0;i<numGenerations;i++){
				ga.nextGeneration();
			}
			this.trainingWindow.updateGenerations();
			MainFrame.getInstance().setBot(new Bot(new NeuralNetwork(ga.getBest())));
			MainFrame.getInstance().playGame(GameMode.Bot);
		}
	}
	
}
