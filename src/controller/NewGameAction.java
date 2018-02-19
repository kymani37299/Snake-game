package controller;

import game.model.Game;
import game.model.GameMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainFrame;
import view.NewGameDialog;

public class NewGameAction implements EventHandler<ActionEvent>{

	private NewGameDialog dialog;
	
	public NewGameAction(NewGameDialog dialog){
		this.dialog = dialog;
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(dialog.getRbHuman().isSelected()){
			MainFrame.getInstance().playGame(new Game(GameMode.Play));
		}else if(dialog.getRbTrain().isSelected()){
			MainFrame.getInstance().openTrainSettings();
		}
	}

}
