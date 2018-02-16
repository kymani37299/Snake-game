package view;

import controller.TrainingSettingsController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TrainingSettingsDialog extends GridPane{

	private TextField tfMutationRate;
	private TextField tfPopulationSize;
	private TextField tfGenerations;
	
	public TrainingSettingsDialog(){
		this.setVgap(15);
		this.setHgap(15);
		this.tfMutationRate = new TextField("1");
		this.tfMutationRate.setMaxWidth(50);
		this.tfPopulationSize = new TextField("50");
		this.tfPopulationSize.setMaxWidth(50);
		this.tfGenerations = new TextField("20");
		this.tfGenerations.setMaxWidth(50);
		this.add(new Label("Population size: "), 0, 0);
		this.add(tfPopulationSize,1,0);
		this.add(new Label("Mutation rate: "), 0, 1);
		this.add(tfMutationRate, 1, 1);
		this.add(new Label("Skip generations:"), 0, 2);
		this.add(tfGenerations, 1, 2);
		Button btnOk = new Button("Ok");
		btnOk.setOnAction(new TrainingSettingsController(this));
		this.add(btnOk, 1, 3);
	}

	public TextField getTfMutationRate() {
		return tfMutationRate;
	}

	public TextField getTfPopulationSize() {
		return tfPopulationSize;
	}

	public TextField getTfGenerations() {
		return tfGenerations;
	}
	
	
	
}
