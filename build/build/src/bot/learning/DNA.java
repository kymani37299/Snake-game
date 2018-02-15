package bot.learning;

import java.util.Random;

import bot.Bot;
import game.model.Game;
import game.model.GameMode;
import neuralNetwork.Matrix;
import neuralNetwork.NeuralNetwork;
import view.MainFrame;

public class DNA {
	//TODO: Different numLayers population
	
	private final static Random rand = new Random();
	private int numLayers;
	private Matrix inputWeights;
	private Matrix outputWeights;
	private Matrix bias[];
	private int fitness;
	private Game game;
	
	public DNA(int numLayers){
		this.numLayers = numLayers;
		this.inputWeights = new Matrix(NeuralNetwork.numInputs,numLayers);
		this.outputWeights = new Matrix(numLayers,NeuralNetwork.numOutputs);
		this.bias = new Matrix[2];
		this.bias[0] = new Matrix(1,numLayers);
		this.bias[1] = new Matrix(1,NeuralNetwork.numOutputs);
	}
	
	public void randomize(){
		this.outputWeights.randomize(-1, 1);
		this.inputWeights.randomize(-1, 1);
		this.bias[0].randomize(-1,1);
		this.bias[1].randomize(-1,1);
	}

	public DNA combine(DNA other){
		DNA child = new DNA(this.numLayers); 
		for(int i=0;i<child.inputWeights.getnRows();i++){
			for(int j=0;j<child.inputWeights.getnColumns();j++){
				int r = rand.nextInt(3);
				if(r==0){
					child.inputWeights.set(i, j, this.inputWeights.get(i, j));
				}else if(r==1){
					child.inputWeights.set(i, j, other.inputWeights.get(i, j));
				}else{
					child.inputWeights.set(i, j, (this.inputWeights.get(i, j)+other.inputWeights.get(i, j))/2);
				}
			}
		}
		for(int i=0;i<child.outputWeights.getnRows();i++){
			for(int j=0;j<child.outputWeights.getnColumns();j++){
				int r = rand.nextInt(3);
				if(r==0){
					child.outputWeights.set(i, j, this.outputWeights.get(i, j));
				}else if(r==1){
					child.outputWeights.set(i, j, other.outputWeights.get(i, j));
				}else{
					child.outputWeights.set(i, j, (this.outputWeights.get(i, j)+other.outputWeights.get(i, j))/2);
				}
			}
		}
		for(int i=0;i<2;i++){
			for(int j=0;j<child.bias[i].getnRows();j++){
				int r = rand.nextInt(3);
				if(r==0){
					child.bias[i].set(j,0, this.bias[i].get(j,0));
				}else if(r==1){
					child.bias[i].set(j,0, other.bias[i].get(j,0));
				}else{
					child.bias[i].set(j,0, (this.bias[i].get(j,0)+other.bias[i].get(j,0))/2);
				}
			}
		}
		
		return child;
	}
	
	public void mutate(int mutationRate){
		for(int i=0;i<this.inputWeights.getnRows();i++){
			for(int j=0;j<this.inputWeights.getnColumns();j++){
				int r = rand.nextInt(100);
				if(r < mutationRate){
					this.inputWeights.randomize(i, j, -1, 1);
				}
			}
		}
		
		for(int i=0;i<this.outputWeights.getnRows();i++){
			for(int j=0;j<this.outputWeights.getnColumns();j++){
				int r = rand.nextInt(100);
				if(r < mutationRate){
					this.outputWeights.randomize(i, j, -1, 1);
				}
			}
		}
		
		for(int i=0;i<2;i++){
			for(int j=0;j<this.bias[i].getnRows();j++){
				int r = rand.nextInt(100);
				if(r < mutationRate){
					this.bias[i].randomize(j,0, -1, 1);
				}
			}
		}
		
	}
	
	public int calculateFitness(){
		this.game = new Game(MainFrame.mapSize , GameMode.Simulation);
		this.game.setController(new Bot(new NeuralNetwork(this),this.game));
		this.game.simulateGame();
		this.fitness = this.game.getApples().size();
		return this.fitness;
	}
	
	public Matrix getInputWeights() {
		return inputWeights;
	}

	public Matrix getOutputWeights() {
		return outputWeights;
	}
	
	public Matrix[] getBias() {
		return bias;
	}
	
}