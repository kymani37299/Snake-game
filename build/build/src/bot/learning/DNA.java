package bot.learning;

import java.util.Random;

import bot.Bot;
import game.GlobalSettings;
import game.model.Game;
import game.model.GameMode;
import neuralNetwork.Matrix;
import neuralNetwork.NeuralNetwork;

public class DNA {
	private final static int simulationNumber = 5;
	private final static Random rand = new Random();
	private int numNeurons;
	private int numLayers;
	private Matrix weights[];
	private Matrix bias[];
	private double fitness;
	private Game game;
	
	public DNA(int numNeurons,int numLayers){
		this.numLayers = numLayers;
		this.numNeurons = numNeurons;
		this.bias = new Matrix[numLayers+1];
		this.weights = new Matrix[numLayers+1];
		this.weights[0] = new Matrix(GlobalSettings.getInstance().getInputGenerator().getNumInputs(),numNeurons);
		this.bias[0] = new Matrix(1,numNeurons);
		this.weights[numLayers] = new Matrix(numNeurons,4);
		this.bias[numLayers] = new Matrix(1,4);
		for(int i=1;i<numLayers;i++){
			this.weights[i] = new Matrix(numNeurons,numNeurons);
			this.bias[i] = new Matrix(1,numNeurons);
		}
	}
	
	public void randomize(){
		for(int i=0;i<=this.numLayers;i++){
			this.weights[i].randomize(-1, 1);
			this.bias[i].randomize(-1, 1);
		}
	}

	public DNA combine(DNA other){
		DNA child = new DNA(this.numNeurons,this.numLayers); 
		
		for(int i=0;i<=numLayers;i++){
			for(int row=0;row<this.weights[i].getnRows();row++){
				for(int col=0;col<this.weights[i].getnColumns();col++){
					int r = rand.nextInt(3);
					if(r==0){
						child.weights[i].set(row, col, this.weights[i].get(row, col));
					}else if(r==1){
						child.weights[i].set(row, col, other.weights[i].get(row, col));
					}else{
						child.weights[i].set(row, col, (this.weights[i].get(row, col)+other.weights[i].get(row, col))/2);
					}
				}
			}
			for(int col=0;col<this.bias[i].getnColumns();col++){
				int r = rand.nextInt(3);
				if(r==0){
					child.bias[i].set(0, col, this.bias[i].get(0, col));
				}else if(r==1){
					child.bias[i].set(0, col, other.bias[i].get(0, col));
				}else{
					child.bias[i].set(0, col, (this.bias[i].get(0, col)+other.bias[i].get(0, col))/2);
				}
			}
		}
		
		return child;
	}
	
	public void mutate(int mutationRate){
		/*for(int i=0;i<this.inputWeights.getnRows();i++){
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
		}*/
		
		for(int i=0;i<=numLayers;i++){
			for(int row=0;row<this.weights[i].getnRows();row++){
				for(int col=0;col<this.weights[i].getnColumns();col++){
					int r = rand.nextInt(100);
					if(r < mutationRate){
						this.weights[i].randomize(row, col, -1, 1);
					}
				}
			}
			for(int col=0;col<this.bias[i].getnColumns();col++){
				int r = rand.nextInt(100);
				if(r < mutationRate){
					this.bias[i].randomize(0,col, -1, 1);
				}
			}
		}
		
	}
	
	public double calculateFitness(){
		int sum = 0;
		for(int i=0;i<simulationNumber;i++){
			this.game = new Game(GameMode.Simulation);
			this.game.setController(new Bot(new NeuralNetwork(this),this.game));
			this.game.simulateGame();
			sum += this.game.getApples().size();
		}
		this.fitness = (double)Math.pow(sum,3)/simulationNumber;
		return this.fitness;
	}
	
	public Matrix[] getWeights(){
		return weights;
	}
	
	public Matrix[] getBias() {
		return bias;
	}
	
	public int getNumLayers(){
		return numLayers;
	}
	
}