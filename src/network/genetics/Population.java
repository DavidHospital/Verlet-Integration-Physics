package network.genetics;

import java.awt.Color;
import java.awt.Graphics;

import main.GameManager;
import object.physicsnew.model.Model;

public class Population {
	
	final long MAX_TIME = 10000L; // 10 seconds
	
	DNA[] population;
	double[] fitness;
	
	// model information;
	Model model;
	int currentDNAIndex;
	
	long startTime;
	double startX;
	double energyLoss;
	
	int generation;
	int populationSize;
	double mutationRate;
	
	GameManager gm;
	
	public Population(int populationSize, double mutationRate, GameManager gm) {
		this.gm = gm;
		
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		
		population = new DNA[populationSize];
		fitness = new double[populationSize];
		
		initialPopulation();
	}
	
	private void newModel() {
		fitness[currentDNAIndex] = fitness();
		currentDNAIndex ++;
		
		if (currentDNAIndex >= populationSize) {
			generation ++;
			currentDNAIndex = 0;
			newPopulation();
		}
		
		model = Model.RandomModel(300, 200, 200, 200, gm);
		
		for (int i = 0; i < 15; i ++) {
			model.update();
		}
		
		startX = model.getX();
		energyLoss = 0;
		
		startTime = System.currentTimeMillis();
	}
	
	private void initialPopulation() {
		
	}
	
	private void newPopulation() {		
		// Sort by fitness high to low
		// Find a better sort, maybe mergesort or quicksort
		
	}
	
	private double fitness() {
		return model.getX() - startX / energyLoss;
	}
	
	public void update() {
		if (System.currentTimeMillis() - startTime >= MAX_TIME) {
			newModel();
		}
		model.update();
		energyLoss += model.getEnergyLoss();
	}
	
	public void render(Graphics g) {
		model.render(g);
		g.setColor(Color.black);
		g.drawString("Generation: " + generation, gm.windowWidth - 280, 15);
		g.drawString("Creature: " + currentDNAIndex, 10, 30);
	}
	
}
