package bot.input;

import game.model.Game;
import neuralNetwork.Matrix;

public abstract class InputGenerator {

	private int numInputs;
	
	public InputGenerator(int numInputs){
		this.numInputs = numInputs;
	}
	
	public abstract Matrix getInput(Game game);
	
	public int getNumInputs() {
		return numInputs;
	}
	
}
