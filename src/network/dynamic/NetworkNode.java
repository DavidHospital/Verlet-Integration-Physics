package network.dynamic;

import java.awt.Color;
import java.awt.Graphics;

public class NetworkNode {

	NetworkStick[] inputs;
	double value;
	
	boolean hasDrawn;
	
	// Graphics information
	public static int Radius = 20;
	int x;
	int y;
	
	NetworkNode(NetworkStick[] inputs, int x, int y) {
		this.inputs = inputs;
		this.x = x;
		this.y = y;
	}
	
	double output() {
		double sum = 0.0;
		for (NetworkStick ns : inputs) {
			sum += ns.output();
		}
		this.value = sigmoid(sum);
		return value;
	}
	
	private double sigmoid(double k) {
		return 1.0 / (1.0 + Math.exp(- k));
	}
	
	void updateSticks(double[][][] stickValues, int layer, int index) {
		for (int i = 0; i < inputs.length; i ++) {
			inputs[i].weight = stickValues[layer][i][index];
			inputs[i].input.updateSticks(stickValues, layer - 1, i);
		}
	}
	
	void update() {
		hasDrawn = false;
		if (inputs != null) {
			for (NetworkStick i : inputs) {
				i.update();
			}
		}
	}
	
	void render(Graphics g) {
		if (!hasDrawn) {
			hasDrawn = true;
			
			if (inputs != null) {
				for (NetworkStick i : inputs) {
					i.render(g, this);
				} 
			}
			
			// draw circle
			g.setColor(Color.getHSBColor(0.0f, 0.0f, (float) value));
			int X = x - Radius / 2;
			int Y = y - Radius / 2;
			g.fillOval(X, Y, Radius, Radius);

			g.setColor(Color.WHITE);
			g.drawString((int)(value * 10) / 10.0 + " ", x - 9, y + 7);
		}
	}
}
