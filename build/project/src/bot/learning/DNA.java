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
	private int fitness;
	private Game game;
	
	public DNA(int numLayers){
		this.numLayers = numLayers;
		this.inputWeights = new Matrix(NeuralNetwork.numInputs,numLayers);
		this.outputWeights = new Matrix(numLayers,NeuralNetwork.numOutputs);
	}
	
	public void randomize(){
		this.outputWeights.randomize(-1, 1);
		this.inputWeights.randomize(-1, 1);
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
	
}