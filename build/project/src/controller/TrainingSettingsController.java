package controller;

import bot.learning.GeneticAlg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.TrainingSettingsDialog;
import view.TrainingWindow;

public class TrainingSettingsController implements EventHandler<ActionEvent>{

	private TrainingSettingsDialog dialog;
	
	public TrainingSettingsController(TrainingSettingsDialog dialog){
		this.dialog = dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		int mutationRate = -1;
		int populationSize = -1;
		int numGenerations = -1;
		int neuronNumber = -1;
		int numLayers = -1;
		try{
			mutationRate = Integer.parseInt(dialog.getTfMutationRate().getText());
			populationSize = Integer.parseInt(dialog.getTfPopulationSize().getText());
			numGenerations = Integer.parseInt(dialog.getTfGenerations().getText());
			neuronNumber = Integer.parseInt(dialog.getTfHiddenNeurons().getText());
			numLayers = Integer.parseInt(dialog.getTfNumLayers().getText());
		}catch(NumberFormatException e){
		}
		if(mutationRate >= 0 && mutationRate <= 100 && populationSize>0 && numGenerations >= 0 && neuronNumber > 0 && numLayers > 0){
			GeneticAlg ga = new GeneticAlg(populationSize, mutationRate , neuronNumber , numLayers);
			TrainingWindow trainingWindow = new TrainingWindow(ga);
			ga.setTrainingWindow(trainingWindow);
			ga.setGoalGeneration(numGenerations + ga.getGeneration());
			Thread t = new Thread(ga);
			t.setDaemon(true);
			t.start();
			trainingWindow.show();
			
		}
	}
	
	
	
}
