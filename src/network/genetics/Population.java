package network.genetics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import object.physics.NetworkBody;

public class Population {
	
	final long MAX_NANO_TIME = 10000000000L; // 10 seconds
	
	private int generation;
	
	DNA[] population;
	double[] fitness;
	
	// model information;
	public NetworkBody model;
	int currentDNAIndex;
	double currentFitness;
	
	private long startTime;
	private double startX;
	
	int populationSize;
	int DNASize;
	double mutationRate;
	
	public Population(int populationSize, double mutationRate) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		
		this.model = new NetworkBody(400, 400, 50);
		
		DNASize = (model.network.inputSize + model.network.outputSize) * model.network.hiddenSize;
		
		population = new DNA[populationSize];
		fitness = new double[populationSize];
		
		initialPopulation();
	}
	
	private void initialPopulation() {
		for (int i = 0; i < populationSize; i ++) {
			population[i] = DNA.Random(DNASize);
		}
	}
	
	private void newPopulation() {
		DNA[] newPopulation = new DNA[populationSize];
		for (int i = 0; i < populationSize; i ++) {
			DNA parent1 = getParent();
			DNA parent2;
			while((parent2 = getParent()) == getParent());
			
			newPopulation[i] = parent1.splice(parent2, mutationRate);
		}
		
		population = newPopulation;
	}
	
	private double[] normalizedFitness() {
		double[] ret = new double[populationSize];
		double sum = 0;
		for (int i = 0; i < populationSize; i ++) {
			sum += fitness[i];
		}
		if (sum != 0)
			for (int i = 0; i < populationSize; i ++) {
				ret[i] = fitness[i] / sum;
			}
		return ret;
	}
	
	private DNA getParent() {
		double[] normalized = normalizedFitness();
		Random r = new Random();
		
		double sum = 0;
		double rand = r.nextDouble();
		for (int i = 0; i < populationSize; i ++) {
			sum += normalized[i];
			if (rand <= sum) {
				return population[i];
			}
		}
		return population[0];
		
	}
	
	private void newModel() {
		fitness[currentDNAIndex] = currentFitness;
		currentDNAIndex ++;
		if (currentDNAIndex >= populationSize) {
			generation ++;
			currentDNAIndex = 0;
			newPopulation();
		}
		
		model = new NetworkBody(400, 400, 50, population[currentDNAIndex]);
		startX = model.getX();
		
		startTime = System.nanoTime();
	}
	
	public void update() {
		if (System.nanoTime() - startTime >= MAX_NANO_TIME) {
			newModel();
		}
		model.update();
		currentFitness = model.getX() - startX;
	}
	
	public void render(Graphics g) {
		model.render(g);
		g.setColor(Color.black);
		g.drawString("Generation: " + generation, 10, 15);
		g.drawString("Creature: " + currentDNAIndex, 10, 30);
		g.drawString("Fitness: " + currentFitness, 10, 45);
	}
	
}
