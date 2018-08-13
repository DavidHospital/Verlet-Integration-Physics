package genetic;

import object.physics.model.Model;
import object.scene.Scene;

public class Population {

	public static final int SIMULATE_FRAMES = 600;
	
	private Scene scene;
	
	private DNA[] population;
	private double[] fitness;
	private double mutationRate;
	
	private Model best;
	
	public Population (Scene scene, int size, double mutationRate) {
		this.scene = scene;
		this.mutationRate = mutationRate;
		
		initPopulation(size);
	}
	
	private void initPopulation(int size) {
		population = new DNA[size];
		fitness = new double[size];
		for (int i = 0; i < size; i ++) {
			population[i] = Model.RandomModel(0, 0, 200, 200, 4, scene);
		}
	}
	
	private void simulate() {
		for (int i = 0; i < population.length; i ++) {
			scene.newModel((Model)population[i]);
			for (int j = 0; j < SIMULATE_FRAMES; j ++) {
				scene.update();
			}
		}
	}
	
	private void evaluate() {
		for (int i = 0; i < population.length; i ++) {
			fitness[i] = population[i].fitness();
		}
		
		sort();
	}
	
	public void nextGeneration() {
		
		simulate();
		evaluate();
		
		for (int i = 0; i < population.length; i ++) {
			population[i] = population[i / 2].mutate(mutationRate);
		}
	}
	
	private void sort() {		
		for (int i = 0; i < population.length; i ++) {
			double max = fitness[i];
			int max_index = i;
			
			for (int j = i + 1; j < population.length; j ++) {
				if (fitness[j] > max) {
					max = fitness[j];
					max_index = j;
				}
			}
			
			DNA temp = population[i];
			population[i] = population[max_index];
			population[max_index] = temp;
			
			double temp2 = fitness[i];
			fitness[i] = fitness[max_index];
			fitness[max_index] = temp2;
		}
		
		best = (Model) population[0];
	}
	
	public Model getBest() {
		return best;
	}
}
