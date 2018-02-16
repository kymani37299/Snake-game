package view;

import bot.learning.GeneticAlg;
import controller.TrainingWindowController;
import game.model.Position;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrainingWindow extends Stage{
	
	private Label lGeneration;
	private TextField tfSkipNumber;
	private GeneticAlg ga;
	
	private TextField tfMapHeight;
	private TextField tfMapWidth;
	private TextField tfFPS;
	
	public TrainingWindow(GeneticAlg ga){
		this.ga = ga;
		VBox layout = new VBox(15);
		this.lGeneration = new Label("Generation: " + ga.getGeneration());
		Label lPopulationSize = new Label("Population size: " + ga.getPopulationSize());
		Label lMutationRate = new Label("Mutation rate: " + ga.getMutationRate()+"%");
		
		HBox bottom = new HBox(15);
		this.tfSkipNumber = new TextField();
		this.tfSkipNumber.setMaxWidth(50);
		bottom.getChildren().addAll(new Label("Skip "),tfSkipNumber,new Label(" generations"));
		
		Button btnAccept = new Button("Accept");
		btnAccept.setOnAction(new TrainingWindowController(this));
		
		layout.getChildren().addAll(lGeneration,lPopulationSize,lMutationRate,bottom);
		
		HBox lMapsize = new HBox(15);
		Position mapSize = MainFrame.getInstance().getMapSize();
		this.tfMapHeight = new TextField(""+mapSize.getX());
		this.tfMapHeight.setMaxWidth(50);
		this.tfMapWidth = new TextField(""+mapSize.getY());
		this.tfMapWidth.setMaxWidth(50);
		
		lMapsize.getChildren().addAll(new Label("Map size: "),tfMapHeight , new Label("x"),tfMapWidth);
		
		HBox lFPS = new HBox(15);
		this.tfFPS = new TextField(""+MainFrame.getInstance().getFPS());
		lFPS.getChildren().addAll(new Label("FPS: "),tfFPS);
		
		layout.getChildren().addAll(lMapsize,lFPS,btnAccept);
		
		layout.setPadding(new Insets(15));
		this.setX(MainFrame.getInstance().getX() - this.getWidth());
		this.setY(MainFrame.getInstance().getY() - this.getHeight());
		this.setScene(new Scene(layout));
		this.setTitle("Training settings");
	}

	public TextField getTfSkipNumber() {
		return tfSkipNumber;
	}
	
	public TextField getTfMapHeight() {
		return tfMapHeight;
	}

	public TextField getTfMapWidth() {
		return tfMapWidth;
	}

	public TextField getTfFPS() {
		return tfFPS;
	}

	public GeneticAlg getGa() {
		return ga;
	}

	public void updateGenerations(){
		this.lGeneration.setText("Generation: " + ga.getGeneration());
	}
}
