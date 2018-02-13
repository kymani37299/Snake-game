package view;

import controller.NewGameController;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class NewGameDialog extends VBox{
	
	private RadioButton rbHuman;
	private RadioButton rbBot;
	private RadioButton rbTrain;
	
	public NewGameDialog(){
		super(15);
		ToggleGroup tgChoose = new ToggleGroup();
		rbHuman = new RadioButton("Play game");
		rbBot = new RadioButton("Watch bot play game");
		rbTrain = new RadioButton("Bot training");
		Button btnOk = new Button("Ok");
		btnOk.setOnAction(new NewGameController(this));
		tgChoose.getToggles().addAll(rbHuman,rbBot,rbTrain);
		tgChoose.selectToggle(rbHuman);
		this.getChildren().addAll(rbHuman,rbBot,rbTrain,btnOk);
	}

	public RadioButton getRbHuman() {
		return rbHuman;
	}

	public RadioButton getRbBot() {
		return rbBot;
	}

	public RadioButton getRbTrain() {
		return rbTrain;
	}
	
}
