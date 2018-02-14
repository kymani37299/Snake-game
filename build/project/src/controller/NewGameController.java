package controller;

import game.model.GameMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainFrame;
import view.NewGameDialog;

public class NewGameController implements EventHandler<ActionEvent>{

	private NewGameDialog dialog;
	
	public NewGameController(NewGameDialog dialog){
		this.dialog = dialog;
	}
	
	@Override
	public void handle(ActionEvent event) {
		GameMode mode;
		if(dialog.getRbHuman().isSelected()){
			mode = GameMode.Play;
		}else if(dialog.getRbBot().isSelected()){
			mode = GameMode.Bot;
		}else{
			mode = GameMode.Train;
		}
		MainFrame.getInstance().playGame(mode);
	}

}
