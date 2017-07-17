package network.dynamic;

import java.awt.Graphics;

public class Network {
	
	 NetworkNode[] outputs;
	 InputNode[] inputs;
	 
	 int[] layerSizes;
	 
	 // Graphics information
	 private int x;
	 private int y;
	 private int width;
	 private int height;
	
	public Network (int[] layerSizes, int x, int y, int width, int height) {
		this.layerSizes = layerSizes;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		outputs = generateNetwork(layerSizes.length - 1);
	}
	
	private NetworkNode[] generateNetwork(int layer) {

		if (layer <= 0) {
			NetworkNode[] nodes = new InputNode[layerSizes[0]];
			for (int i = 0; i < nodes.length; i ++) {
				nodes[i] = new InputNode(x + layer * width / layerSizes.length, y + i * height / nodes.length);
			}
			inputs = (InputNode[]) nodes;
			return nodes;
		} else {

			NetworkNode[] nodes = new NetworkNode[layerSizes[layer]];
			
			for (int i = 0; i < nodes.length; i ++) {
				NetworkStick[] sticks = new NetworkStick[layerSizes[layer - 1]];
				
				// Recursion
				NetworkNode[] inputNodes = generateNetwork(layer - 1);
				
				
				for (int k = 0; k < inputNodes.length; k ++) {
					sticks[k] = new NetworkStick(inputNodes[k], 0);
				}
				nodes[i] = new NetworkNode(sticks, x + layer * width / layerSizes.length, y + i * height / nodes.length);
			}
			return nodes;
		}
	}
	
	public void updateSticks(double[][][] stickValues) {
		for (int i = 0; i < outputs.length; i ++) {
			outputs[i].updateSticks(stickValues, layerSizes.length - 1, i);
		}
	}
	
	public double[] forward(double[] values) {
		for (int i = 0; i < inputs.length; i ++) {
			System.out.print(inputs[i].value + "\t");
			System.out.print(values[i] + "\t");
			inputs[i].value = values[i];
			System.out.println(inputs[i].value);
		}
		double[] ret = new double[outputs.length];
		for (int i = 0; i < outputs.length; i ++) {
			ret[i] = outputs[i].output();
		}
		return ret;
	}
	
	public void update() {
		for (NetworkNode o : outputs) {
			o.update();
		}
	}
	
	public void render(Graphics g) {
		for (NetworkNode o : outputs) {
			o.render(g);
		}
	}
}
