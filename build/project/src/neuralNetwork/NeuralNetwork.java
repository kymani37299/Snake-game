package neuralNetwork;

import bot.learning.DNA;

public class NeuralNetwork {
	private DNA genotype;
	
	public NeuralNetwork(DNA genotype){
		this.genotype = genotype;
	}
	
	public Matrix feedFoward(Matrix input){
		Matrix current = input.multiply(this.genotype.getWeights()[0]);
		current = current.add(this.genotype.getBias()[0]);
		current.applySigmoid();
		for(int i=1;i<=this.genotype.getNumLayers();i++){
			current = current.multiply(this.genotype.getWeights()[i]);
			current = current.add(this.genotype.getBias()[i]);
			current.applySigmoid();
		}
		return current;
	}	
}
