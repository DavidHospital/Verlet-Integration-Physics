package network.genetics;

import java.util.Random;

import main.GameManager;
import object.physicsnew.Muscle;
import object.physicsnew.Node;
import object.physicsnew.model.Model;

public class DNA {

	public double[][][] stickWeights;
	public Node[] nodes;
	public Muscle[] muscles;
	
	public DNA (double[][][] stickWeights, Node[] nodes, Muscle[] muscles) {
		this.stickWeights = stickWeights;
		this.nodes = nodes;
		this.muscles = muscles;
	}
	
	public Model modelFromDNA(GameManager gm) {
		Model m = new Model(nodes, muscles, gm);
		m.updateNetworkSticks(stickWeights);
		return m;
	}
	
	public DNA mutate(double mutationRate, int x, int y, int width, int height) {
		Random r = new Random();
		
		// mutate nodes
		for (int i = 0; i < nodes.length; i ++) {
			if (r.nextDouble() < mutationRate) {
				nodes[i].x += r.nextGaussian();
			}
			if (r.nextDouble() < mutationRate) {
				nodes[i].y += r.nextGaussian();
			}
			if (r.nextDouble() < mutationRate) {
				nodes[i].friction += r.nextGaussian();
				if (nodes[i].friction > 1.0) {
					nodes[i].friction = 1.0;
				} else if (nodes[i].friction < 0.0) {
					nodes[i].friction = 0.0;
				}
			}
		}
		
		// mutate muscles
		for (int i = 0; i < muscles.length; i ++) {
			if (r.nextDouble() < mutationRate) {
				muscles[i].thickness += r.nextGaussian();
				if (muscles[i].thickness > 1.0) {
					muscles[i].thickness = 1.0;
				} else if (muscles[i].thickness < 0.0) {
					muscles[i].thickness = 0.0;
				}
			}
		}
		
//		// modify structure (add/remove node)
//		if (r.nextDouble() < mutationRate * mutationRate) {
//			if (r.nextBoolean()) {
//				// add a node
//				Node nNode = new Node(r.nextInt(width) + x - width / 2,
//						r.nextInt(height) + y - height / 2, r.nextDouble());
//				
//				Node[] newNodes = new Node[nodes.length + 1];
//				for (int i = 0; i < nodes.length; i ++) {
//					newNodes[i] = nodes[i];
//				}
//				newNodes[newNodes.length - 1] = nNode;
//				
//				nodes = newNodes;
//				
//				// add the muscle for the new node
//				Muscle nMuscle = new Muscle(nodes[nodes.length - 1], 
//						nodes[r.nextInt(nodes.length - 1)], r.nextDouble());
//				
//				Muscle[] newMuscles = new Muscle[muscles.length + 1];
//				for (int i = 0; i < muscles.length; i ++) {
//					newMuscles[i] = muscles[i];
//				}
//				newMuscles[newMuscles.length - 1] = nMuscle;
//				muscles = newMuscles;
//				
//			} else {
//				// remove a node
//				int indexR = r.nextInt(nodes.length);
//				double[] newNodesX = new double[nodesX.length - 1];
//				double[] newNodesY = new double[nodesY.length - 1];
//				double[] newNodesF = new double[nodesF.length - 1];
//				for (int i = 0; i < nodesX.length; i ++) {
//					if (i < indexR) {
//						newNodesX[i] = nodesX[i];
//						newNodesY[i] = nodesY[i];
//						newNodesF[i] = nodesF[i];
//					} else if (i > indexR) {
//						newNodesX[i - 1] = nodesX[i];
//						newNodesY[i - 1] = nodesY[i];
//						newNodesF[i - 1] = nodesF[i];
//					}
//				}
//				nodesX = newNodesX;
//				nodesY = newNodesY;
//				nodesF = newNodesF;
//				
//				// remove any muscles connected to the removed node
//				boolean foundMuscle;
//				do {
//				int indexR2 = 0;
//					foundMuscle = false;
//					for (int i = 0; i < musclesT.length; i ++) {
//						if (musclesN1[i] == indexR || musclesN2[i] == indexR) {
//							indexR2 = i;
//							foundMuscle = true;
//							break;
//						}
//					}
//					
//					if (foundMuscle) {
//						int[] newMusclesN1 = new int[musclesN1.length - 1];
//						int[] newMusclesN2 = new int[musclesN2.length - 1];
//						double[] newMusclesT = new double[musclesT.length - 1];
//						
//						for (int i = 0; i < musclesN1.length; i ++) {
//							if (i < indexR2) {
//								newMusclesN1[i] = musclesN1[i];
//								newMusclesN2[i] = musclesN2[i];
//								newMusclesT[i] = musclesT[i];
//							} else if (i > indexR2) {
//								newMusclesN1[i - 1] = musclesN1[i];
//								newMusclesN2[i - 1] = musclesN2[i];
//								newMusclesT[i - 1] = musclesT[i];
//							}
//						}
//						musclesN1 = newMusclesN1;
//						musclesN2 = newMusclesN2;
//						musclesT = newMusclesT;
//					}
//				} while (foundMuscle);
//				
//			}
//		}
//		
		// mutate stick weights
		double[][][] newStickWeights = stickWeights.clone();
		for (int i = 0; i < newStickWeights.length; i ++) {
			for (int k = 0; k < newStickWeights[i].length; k ++) {
				for (int m = 0; m < newStickWeights[i][k].length; m ++) {
					if (r.nextDouble() < mutationRate) {
						newStickWeights[i][k][m] += r.nextGaussian();
					}
				}
			}
		}
		
		return new DNA(stickWeights.clone(), nodes.clone(), muscles.clone());
	}
	
}
