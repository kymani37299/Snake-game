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
		hidden = hidden.add(this.genotype.getBias()[0]);
		hidden.applySigmoid();
		Matrix output = hidden.multiply(this.genotype.getOutputWeights());
		output = output.add(this.genotype.getBias()[1]);
		output.applySigmoid();
		return output;
	}	
}
