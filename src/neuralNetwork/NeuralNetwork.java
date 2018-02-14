package neuralNetwork;

import bot.learning.DNA;

public class NeuralNetwork {
	public final static int numInputs = 8;
	public final static int numOutputs = 4;
	private DNA genotype;
	
	public NeuralNetwork(DNA genotype){
		this.genotype = genotype;
	}
	
	public Matrix feedFoward(Matrix input){
		Matrix hidden = input.multiply(this.genotype.getInputWeights());
		hidden.applySigmoid();
		Matrix output = hidden.multiply(this.genotype.getOutputWeights());
		output.applySigmoid();
		return output;
	}	
}
