package bot;

import bot.input.InputGenerator;
import game.GlobalSettings;
import game.controller.GameController;
import game.model.Direction;
import game.model.Game;
import neuralNetwork.Matrix;
import neuralNetwork.NeuralNetwork;

public class Bot implements GameController{

	private NeuralNetwork nn;
	private InputGenerator inputGenerator;
	private Game game;

	public Bot(NeuralNetwork nn,Game game){
		this.nn = nn;
		this.game = game;
		this.inputGenerator = GlobalSettings.getInstance().getInputGenerator();
	}
	
	@Override
	public Direction getAction() {
		Matrix input = this.inputGenerator.getInput(this.game);
		Matrix output = this.nn.feedFoward(input);
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
