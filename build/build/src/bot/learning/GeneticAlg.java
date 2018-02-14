package bot.learning;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlg {
	
	private static final Random rand = new Random();
	private final int nnLayers = 3;//TODO: layer size variation
	private int generation;
	private int mutationRate;
	private int populationSize;
	private DNA population[];
	
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
	
	private DNA pickRandom(){
		/*double r = rand.nextDouble();
		int index = 0;
		while(r>=0){
			r -= probability[index];
			index++;
		}
		index--;
		return this.population[index];*/
		ArrayList<DNA> genePool = new ArrayList<>(); //Not efficient ,something above dont work TODO: fix it
		for(DNA d : this.population){
			int tmp = d.calculateFitness();
			for(int i=0;i<tmp;i++){
				genePool.add(d);
			}
		}
		return genePool.get(rand.nextInt(genePool.size()));
	}
	
	public void nextGeneration(){
		this.generation++;
		
		//Calculating probability for every DNA
		/*int fitness[] = new int[this.populationSize];
		int sum = 0;
		for(int i=0;i<this.populationSize;i++){
			fitness[i] = this.population[i].calculateFitness();
			sum += fitness[i];
		}
		double probability[] = new double[this.populationSize];
		for(int i=0;i<this.populationSize;i++){
			probability[i] = fitness[i]/sum;
		}*/
		
		//Reproduction
		DNA newGeneration[] = new DNA[this.populationSize];
		for(int i=0;i<this.populationSize;i++){
			DNA parent1 = this.pickRandom();
			DNA parent2 = this.pickRandom();
			DNA child = parent1.combine(parent2);
			child.mutate(this.mutationRate);
			newGeneration[i] = child;
		}
		
		this.population = newGeneration;
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

	public DNA getBest(){
		DNA best = this.population[0];
		int bestFitness = this.population[0].calculateFitness();
		for(int i=1;i<this.populationSize;i++){
			int tmp = this.population[i].calculateFitness();
			if(tmp > bestFitness){
				best = this.population[i];
				bestFitness = tmp;
			}
		}
		return best;
	}
	
}
