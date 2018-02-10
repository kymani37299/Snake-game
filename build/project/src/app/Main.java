package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainFrame;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainFrame mf = MainFrame.getInstance();
		mf.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
