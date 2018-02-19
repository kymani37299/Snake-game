package bot;

import game.controller.GameController;
import game.model.Direction;
import game.model.Game;
import neuralNetwork.Matrix;
import neuralNetwork.NeuralNetwork;
import view.MainFrame;

public class Bot implements GameController{

	private NeuralNetwork nn;
	private Game game;

	public Bot(NeuralNetwork nn,Game game){
		this.nn = nn;
		this.game = game;
	}
	
	public Bot(NeuralNetwork nn){
		this.nn = nn;
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public Game getGame(){
		return this.game;
	}
	
	@Override
	public Direction getAction() {
		Matrix input = MainFrame.getInstance().getInputGenerator().getInput(this.game);
		Matrix output = nn.feedFoward(input);
		double max = output.get(0, 0);
		for(int i=1;i<4;i++){
			if(output.get(0, i)>max) max = output.get(0, i);
		}
		if(output.get(0, 0) == max) return Direction.UP;
		else if(output.get(0, 1) == max) return Direction.DOWN;
		else if(output.get(0, 2) == max) return Direction.LEFT;
		else return Direction.RIGHT;
	}

}
