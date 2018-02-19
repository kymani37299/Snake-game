package bot.learning;

import java.util.Random;

import bot.Bot;
import game.model.Game;
import game.model.GameMode;
import javafx.application.Platform;
import neuralNetwork.NeuralNetwork;
import view.MainFrame;
import view.TrainingWindow;

public class GeneticAlg implements Runnable{
	
	private static final Random rand = new Random();
	private final int nnLayers = 10;//TODO: layer size variation
	private int generation;
	private int goalGeneration;
	private int mutationRate;
	private int populationSize;
	private DNA population[];
	private double fitness[];
	private DNA bestDNA;
	private double bestFitness;
	private double avgFitness;
	private TrainingWindow trainingWindow;
	
	public GeneticAlg(int populationSize,int mutationRate){
		this.generation = 0;
		this.mutationRate = mutationRate;
		this.populationSize = populationSize;
		this.population = new DNA[populationSize];
		for(int i=0;i<populationSize;i++){
			this.population[i] = new DNA(this.nnLayers);
			this.population[i].randomize();
		}
	}
	
	private DNA pickRandom(double probability[],int sum){
		double r = rand.nextDouble();
		int index = 0;
		while(r>=0){
			r -= probability[index];
			index++;
		}
		index--;
		return this.population[index];
	}
	
	public void nextGeneration(){
		
		//Calculating probability for every DNA
		this.fitness = new double[this.populationSize];
		int sum = 0;
		for(int i=0;i<this.populationSize;i++){
			fitness[i] = this.population[i].calculateFitness();
			sum += fitness[i];
		}
		double probability[] = new double[this.populationSize];
		for(int i=0;i<this.populationSize;i++){
			probability[i] = (double)fitness[i]/sum;
		}
		
		//Reproduction
		DNA newGeneration[] = new DNA[this.populationSize];
		for(int i=0;i<this.populationSize;i++){
			DNA parent1 = this.pickRandom(probability,sum);
			DNA parent2 = this.pickRandom(probability,sum);
			DNA child = parent1.combine(parent2);
			child.mutate(this.mutationRate);
			newGeneration[i] = child;
		}
		
		this.population = newGeneration;
		this.generation++;
	}
	
	public double getBestFitness() {
		return bestFitness;
	}

	public double getAvgFitness() {
		return avgFitness;
	}

	public DNA getBestDNA() {
		return bestDNA;
	}

	public int getGeneration() {
		return generation;
	}
	
	public int getMutationRate() {
		return mutationRate;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setGoalGeneration(int goalGeneration) {
		if(this.goalGeneration >= this.generation){
			this.goalGeneration = goalGeneration;
		}
	}
	
	public void setTrainingWindow(TrainingWindow trainingWindow) {
		this.trainingWindow = trainingWindow;
	}

	public void calculateResults(){
		this.bestDNA = this.population[0];
		this.bestFitness = this.fitness[0];
		double sumFitness = this.fitness[0];
		for(int i=1;i<this.populationSize;i++){
			double tmp = this.fitness[i];
			if(tmp > bestFitness){
				this.bestDNA = this.population[i];
				this.bestFitness = tmp;
			}
			sumFitness += tmp;
		}
		this.avgFitness = sumFitness/this.populationSize;
	}

	@Override
	public void run() {
		while(this.generation < this.goalGeneration){
			this.nextGeneration();
			this.calculateResults();
			Platform.runLater(()-> {
				this.trainingWindow.updateResults();
				Game newGame = new Game(GameMode.Simulation);
				Bot bot = new Bot(new NeuralNetwork(this.bestDNA),newGame);
				newGame.setController(bot);
				MainFrame.getInstance().playGame(newGame);
				});
		}
	}
	
}
