package controller;

import bot.learning.GeneticAlg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
			ga.setGoalGeneration(numGenerations + ga.getGeneration());
			new Thread(ga).start();
		}
	}
	
}
